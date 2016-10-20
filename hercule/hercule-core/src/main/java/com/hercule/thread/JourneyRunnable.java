package com.hercule.thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.navitia.Arrivals;
import com.hercule.commun.navitia.Departures;
import com.hercule.commun.navitia.Journey;
import com.hercule.commun.navitia.Journeys;
import com.hercule.commun.navitia.Section;
import com.hercule.commun.navitia.StopDateTime;
import com.hercule.commun.navitia.factories.JourneyFactory;
import com.hercule.manager.RestManager;
import com.hercule.utils.exception.HerculeTechnicalException;

public class JourneyRunnable implements Runnable{

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(JourneyRunnable.class.getName());

	private Thread t;
	private String threadName;
	private StopAreaModel stopAreaModelFrom;
	private List<StopAreaModel> listStations;
	private static String PUBLIC_TRANSPORT = "public_transport";
	private String DATE_JOURNEY;
	private String DATE_PREMIER_DEPART_SEMAINE;
	private String DATE_DERNIER_DEPART_SEMAINE;
	private String DATE_PREMIER_DEPART_WE;
	private String DATE_DERNIER_DEPART_WE;
	private String outputDirectory;

	private static Map<String, String> mapType = new HashMap<String, String>();

	static {
		mapType.put("street_network", "s_t");
		mapType.put("public_transport", "p_t");
		mapType.put("transfer", "trs");
		mapType.put("waiting", "wt");
	}

	public JourneyRunnable(String outputDirectory, String threadName, StopAreaModel stopAreaModelFrom, String datetime, String datePremierDepartSemaine, String dateDernierDepartSemaine, String datePremierDepartWE, String dateDernierDepartWE) {
		this.outputDirectory = outputDirectory;
		this.threadName = threadName;
		this.stopAreaModelFrom = stopAreaModelFrom;
		this.DATE_JOURNEY = datetime;
		this.DATE_PREMIER_DEPART_SEMAINE = datePremierDepartSemaine;
		this.DATE_DERNIER_DEPART_SEMAINE = dateDernierDepartSemaine;
		this.DATE_PREMIER_DEPART_WE = datePremierDepartWE;
		this.DATE_DERNIER_DEPART_WE = dateDernierDepartWE;
	}

	public void run() {
		logger.info("Running " +  threadName );

		try {
			
			listStations = HerculeDao.getAllStopAreaNoFlag();

			RestTemplate restTemplate = RestManager.createRestTemplate();
			JourneyFactory journeyFactory = new JourneyFactory();

			BufferedWriter fichier = new BufferedWriter(new FileWriter(outputDirectory + "itineraires_" + stopAreaModelFrom.getName() + ".json"));
			fichier.write("[");
			boolean debut = true;
			
			for(StopAreaModel stopAreaModelTo : listStations) {
				if(!debut) {
					fichier.write(",");
				}
				
				if(stopAreaModelFrom.getIdStopArea() != stopAreaModelTo.getIdStopArea()) {
					logger.info("calcul itinéraire de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());

					JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
					Journeys journeys = RestManager.callJourney(restTemplate, stopAreaModelFrom, stopAreaModelTo, "1", DATE_JOURNEY);

					if (journeys == null || journeys.getJourneys() == null ){
						logger.info("Pas d'itineraire trouvé de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());
					} else {


						JsonArrayBuilder jsonArray = Json.createArrayBuilder();
						
						for(Journey myJourney : journeys.getJourneys()) {

							if(myJourney.getSections() != null) {
								
								for(int i = 0; i < myJourney.getSections().size(); i++) {
									Section section = myJourney.getSections().get(i);
									
									JsonObjectBuilder jsonBuilderSection = Json.createObjectBuilder();
									
									jsonBuilderSection.add("type", section.getType());

									//PREMIER/DERNIER DEPART - SEMAINE - WE 
									if (section.getType().equals(PUBLIC_TRANSPORT) /*& isPremiereStation*/){
										String routeID = journeyFactory.getRouteId(section.getLinks());
										String fromID = section.getFrom().getStop_point().getStop_area().getId();

										Departures departures_Semaine = RestManager.callPremierDepart(restTemplate, routeID, fromID , DATE_PREMIER_DEPART_SEMAINE);
										Departures departures_WE = RestManager.callPremierDepart(restTemplate, routeID, fromID, DATE_PREMIER_DEPART_WE);
										Arrivals arrivals_Semaine = RestManager.callDernierDepart(restTemplate, routeID, fromID, DATE_DERNIER_DEPART_SEMAINE);
										Arrivals arrivals_WE = RestManager.callDernierDepart(restTemplate, routeID, fromID, DATE_DERNIER_DEPART_WE);

										//Si il y a une horaire disponible alors l'insérer, sinon ne rien faire
										if (departures_Semaine.getDepartures().size()>0){
											jsonBuilderSection.add("PDS", departures_Semaine.getDepartures().get(0).getStop_date_time().getDeparture_date_time().substring(9,13));
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											//itineraireOutput.append("#");
										}
										if (departures_WE.getDepartures().size()>0){
											jsonBuilderSection.add("PDW", departures_WE.getDepartures().get(0).getStop_date_time().getDeparture_date_time().substring(9,13));
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											//itineraireOutput.append("#");
										}
										if (arrivals_Semaine.getArrivals().size()>0){
											jsonBuilderSection.add("DDS", arrivals_Semaine.getArrivals().get(0).getStop_date_time().getDeparture_date_time().substring(9,13));
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											//itineraireOutput.append("#");
										}
										if (arrivals_WE.getArrivals().size()>0){
											jsonBuilderSection.add("DDW", arrivals_WE.getArrivals().get(0).getStop_date_time().getDeparture_date_time().substring(9,13));
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											//itineraireOutput.append("#");
										}
									} else {
										//Pas de premier/dernier départ sur un itinéraire à pied
										//itineraireOutput.append("####");
									}

									if(section.getDisplay_informations() != null && section.getDisplay_informations().getCode() != null && !"".equals(section.getDisplay_informations().getCode())) {
										jsonBuilderSection.add("ligne", section.getDisplay_informations().getCode());
									}
									jsonBuilderSection.add("tps", section.getDuration());

									if(section.getStop_date_times() != null) {

										JsonArrayBuilder jsonArrayStops = Json.createArrayBuilder();
										
										for(int j = 0; j < section.getStop_date_times().size() ; j++) {
											StopDateTime sdt = section.getStop_date_times().get(j);
											String name = sdt.getStop_point().getName();
											String lat = sdt.getStop_point().getCoord().getLat();
											String lon = sdt.getStop_point().getCoord().getLon();
											int idStopArea = HerculeDao.getStopAreaFromTSTOPPOINT(name, lat, lon);

											jsonArrayStops.add(idStopArea);
										}
										jsonBuilderSection.add("stops", jsonArrayStops);
									}

									if(i != myJourney.getSections().size() - 1) {
										jsonArray.add(jsonBuilderSection);
									}
								}
							}
							//String purge = replaceString.replaceAll("street_network", "s_n").replaceAll("public_transport", "p_t").replaceAll("transfer", "t_r").replaceAll("waiting", "w_t");

							jsonBuilder
								.add("depart", stopAreaModelFrom.getIdStopArea())
								.add("arrivee", stopAreaModelTo.getIdStopArea())
								.add("sections", jsonArray);
							
							logger.info(jsonBuilder.build().toString());
							fichier.write(jsonBuilder.build().toString());
						}
					}
				}
				debut = false;
			}
			fichier.write("]");
			fichier.close();
			logger.info("flag de " + stopAreaModelFrom.getIdStopArea());
			HerculeDao.flagToCalculated(stopAreaModelFrom.getIdStopArea());

		} catch (IOException e) {
			logger.error("Impossible de créer le fichierD:/logs/" + stopAreaModelFrom.getName() + ".csv");
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		} 
		catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
		}

	}

	public void start () {
		logger.info("Starting " +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}

}

package com.hercule.thread;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static String DATE_JOURNEY = "20160613T121128";
	private static String DATE_PREMIER_DEPART_SEMAINE = "20160613T050000";
	private static String DATE_DERNIER_DEPART_SEMAINE = "20160613T015000";
	private static String DATE_PREMIER_DEPART_WE = "20160618T050000";
	private static String DATE_DERNIER_DEPART_WE = "20160618T015000";

	private static Map<String, String> mapType = new HashMap<String, String>();

	static {
		mapType.put("street_network", "s_t");
		mapType.put("public_transport", "p_t");
		mapType.put("transfer", "trs");
		mapType.put("waiting", "wt");
	}

	public JourneyRunnable(String threadName, StopAreaModel stopAreaModelFrom) {
		this.threadName = threadName;
		this.stopAreaModelFrom = stopAreaModelFrom;
	}

	public void run() {
		logger.info("Running " +  threadName );

		try {
			
			listStations = HerculeDao.getAllStopAreaNoFlag();

			List<String> itineraires = new ArrayList<String>();
			RestTemplate restTemplate = RestManager.createRestTemplate();
			JourneyFactory journeyFactory = new JourneyFactory();

			for(StopAreaModel stopAreaModelTo : listStations) {

				if(stopAreaModelFrom.getIdStopArea() != stopAreaModelTo.getIdStopArea()) {
					logger.info("calcul itinéraire de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());

					StringBuilder itineraireOutput = new StringBuilder();
					//itineraireOutput.append(stopAreaModelFrom.getIdStopArea() + ";");
					itineraireOutput.append(stopAreaModelTo.getIdStopArea() + ";");


					Journeys journeys = RestManager.callJourney(restTemplate, stopAreaModelFrom, stopAreaModelTo, "1", DATE_JOURNEY);
					//TODO : Si on veut afficher que pour la premiere station, l'intégrer
					boolean isPremiereStation = true;

					if (journeys == null || journeys.getJourneys() == null ){
						logger.info("Pas d'itineraire trouvé de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());
					} else {

						for(Journey myJourney : journeys.getJourneys()) {

							if(myJourney.getSections() != null) {

								for(int i = 0; i < myJourney.getSections().size(); i++) {
									Section section = myJourney.getSections().get(i);

									itineraireOutput.append(section.getType() + "#");


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
											itineraireOutput.append(departures_Semaine.getDepartures().get(0).getStop_date_time().getDeparture_date_time().substring(9,13) + "#");
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											itineraireOutput.append("#");
										}
										if (departures_WE.getDepartures().size()>0){
											itineraireOutput.append(departures_WE.getDepartures().get(0).getStop_date_time().getDeparture_date_time().substring(9,13) + "#");
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											itineraireOutput.append("#");
										}
										if (arrivals_Semaine.getArrivals().size()>0){
											itineraireOutput.append(arrivals_Semaine.getArrivals().get(0).getStop_date_time().getDeparture_date_time().substring(9,13) + "#");
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											itineraireOutput.append("#");
										}
										if (arrivals_WE.getArrivals().size()>0){
											itineraireOutput.append(arrivals_WE.getArrivals().get(0).getStop_date_time().getDeparture_date_time().substring(9,13) + "#");
										} else { // C'est possible qu'il y ait des travaux et donc pas d'horaire, donc ne rien prendre
											itineraireOutput.append("#");
										}
									} else {
										//Pas de premier/dernier départ sur un itinéraire à pied
										itineraireOutput.append("####");
									}

									itineraireOutput.append(section.getDisplay_informations() != null ? section.getDisplay_informations().getCode() + "#" : "#" );
									itineraireOutput.append(section.getDuration() + "#");

									if(section.getStop_date_times() != null) {

										for(int j = 0; j < section.getStop_date_times().size() ; j++) {
											StopDateTime sdt = section.getStop_date_times().get(j);

											int idStopArea = HerculeDao.getStopAreaFromTSTOPPOINT(sdt.getStop_point().getId());
											itineraireOutput.append(idStopArea);

											if(j != section.getStop_date_times().size() - 1) {
												itineraireOutput.append("%");
											}
										}
									}

									if(i != myJourney.getSections().size() - 1) {
										itineraireOutput.append("|");
									}
								}
							}
							String replaceString = itineraireOutput.toString();
							String purge = replaceString.replaceAll("street_network", "s_n").replaceAll("public_transport", "p_t").replaceAll("transfer", "t_r").replaceAll("waiting", "w_t");
							itineraires.add(purge);	
						}
					}
				}
				break;
			}

			//				fichier.close();
			logger.info("insert itineraires pour " + stopAreaModelFrom.getIdStopArea() + " avec taille " + itineraires.size());
			HerculeDao.insertItineraires(stopAreaModelFrom.getIdStopArea(), itineraires);
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

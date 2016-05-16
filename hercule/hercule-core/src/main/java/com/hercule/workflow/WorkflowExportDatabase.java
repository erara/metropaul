package com.hercule.workflow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.beans.LineModel;
import com.hercule.commun.beans.NetworkModel;
import com.hercule.commun.beans.RouteModel;
import com.hercule.commun.beans.StopAreaLine;
import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.beans.StopAreaRoute;
import com.hercule.commun.beans.StopPointModel;
import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.db.DatabaseConnection;
import com.hercule.commun.interfaces.IWorkflow;
import com.hercule.commun.navitia.Arrivals;
import com.hercule.commun.navitia.Departures;
import com.hercule.commun.navitia.Journey;
import com.hercule.commun.navitia.Journeys;
import com.hercule.commun.navitia.Section;
import com.hercule.commun.navitia.StopDateTime;
import com.hercule.commun.navitia.factories.JourneyFactory;
import com.hercule.manager.RestManager;
import com.hercule.utils.exception.HerculeTechnicalException;

public class WorkflowExportDatabase implements IWorkflow {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(WorkflowExportDatabase.class.getName());
	private static String propertiesFilename;
	private static String PUBLIC_TRANSPORT = "public_transport";
	private static String DATE_JOURNEY = "20160410T121128";
	private static String DATE_PREMIER_DEPART_SEMAINE = "20160411T050000";
	private static String DATE_DERNIER_DEPART_SEMAINE = "20160410T015000";
	private static String DATE_PREMIER_DEPART_WE = "20160416T050000";
	private static String DATE_DERNIER_DEPART_WE = "20160416T015000";

	public WorkflowExportDatabase() {

	}

	public void executer(String fileName, String[] args) {



		logger.info("Traitement d'import de la BDD");

		long startTime = System.currentTimeMillis();
		try {
			propertiesFilename = fileName;
			DatabaseConnection.initConnection(propertiesFilename);
			exportItineraires();
			DatabaseConnection.closeConnection();
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info("Fin du traitement. Temps d'exécution :"+ (endTime-startTime) / 1000 + "s");

	}

	
	private void exportItineraires() {
		try {
			HerculeDao.getItineraires();
		} catch (HerculeTechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void calculItineraires()  throws HerculeTechnicalException {

		try {

			/** Création du template Rest pour l'appel à Navitia */

			RestTemplate restTemplate = RestManager.createRestTemplate();
			List<StopAreaModel> listFrom = HerculeDao.getAllStopAreas();

			JourneyFactory journeyFactory = new JourneyFactory();


			for(StopAreaModel stopAreaModelFrom : listFrom) {

				/** On itère sur toutes les stations exceptées celle en cours */
				List<StopAreaModel> listTo = new ArrayList<StopAreaModel>();
				listTo.addAll(listFrom);
				listTo.remove(stopAreaModelFrom);

				logger.info("Création du fichier d'export D:/logs/" + stopAreaModelFrom.getName() + ".csv");
				BufferedWriter fichier = new BufferedWriter(new FileWriter("D:/logs/itineraires/" + stopAreaModelFrom.getName().replaceAll("/", " ") + ".csv", false));

				for(StopAreaModel stopAreaModelTo : listTo) {
					logger.info("calcul itinéraire de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());

					StringBuilder itineraireOutput = new StringBuilder();
					itineraireOutput.append(stopAreaModelFrom.getIdStopArea() + ";");
					itineraireOutput.append(stopAreaModelTo.getIdStopArea() + ";");
					//					itineraireOutput.append("PREMIER_DEPART_S" + ";");
					//					itineraireOutput.append("DERNIER_DEPART_S" + ";");
					//					itineraireOutput.append("PREMIER_DEPART_WD" + ";");
					//					itineraireOutput.append("DERNIER_DEPART_WS" + ";");


					Journeys journeys = RestManager.callJourney(restTemplate, stopAreaModelFrom, stopAreaModelTo, "1", DATE_JOURNEY);
					//TODO : Si on veut afficher que pour la premiere station, l'intégrer
					boolean isPremiereStation = true;

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
					}
					fichier.write(itineraireOutput.toString());
					fichier.newLine();
				}
				fichier.close();
				HerculeDao.flagToCalculated(stopAreaModelFrom.getIdStopArea());
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier networks.sql");
		}

	}


	private void exportNetworks() throws HerculeTechnicalException {
		try {

			List<NetworkModel> listModel = HerculeDao.getAllNetwork();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/networks.sql"));

				for(NetworkModel network : listModel) {

					fichier.write("insert into `T_NETWORK` values (");
					fichier.write(network.getIdNetwork() + ",");
					fichier.write("'" + network.getType() + "'" + ",");
					fichier.write("'" + network.getGeneriqueType() + "'" + ",");
					fichier.write("'" + network.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier networks.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}

	}

	private void exportLines() throws HerculeTechnicalException {
		try {

			List<LineModel> listModel = HerculeDao.getAllLines();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/lines.sql")
						);

				for(LineModel line : listModel) {

					fichier.write("insert into `T_LINE` values (");
					fichier.write(line.getIdLine() + ",");
					fichier.write("'" + line.getCode() + "'" + ",");
					fichier.write("'" + line.getName().replace("'", "''") + "'" + ",");
					fichier.write(line.getIdNetwork() + ",");
					fichier.write("'" + line.getOpeningTime() + "'" + ",");
					fichier.write("'" + line.getClosingTime() + "'" + ",");
					fichier.write("'" + line.getColor() + "'" + ",");
					fichier.write("'" + line.getTransport_type() + "'" + ",");
					fichier.write("'" + line.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier lines.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportRoutes() throws HerculeTechnicalException {
		try {

			List<RouteModel> listModel = HerculeDao.getAllRoutes();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/routes.sql")
						);

				for(RouteModel route : listModel) {

					fichier.write("insert into `T_ROUTE` values (");
					fichier.write(route.getIdRoute() + ",");
					fichier.write(route.getIdLine() + ",");
					fichier.write("'" + route.getName().replace("'", "''") + "'" + ",");
					fichier.write("'" + route.getDestination().replace("'", "''") + "'" + ",");
					fichier.write("'" + route.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier routes.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreas() throws HerculeTechnicalException {
		try {

			List<StopAreaModel> listModel = HerculeDao.getAllStopAreas();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/stopAreas.sql")
						);

				for(StopAreaModel stopArea : listModel) {

					fichier.write("insert into `T_STOP_AREA` values (");
					fichier.write(stopArea.getIdStopArea() + ",");
					fichier.write("'" + stopArea.getName().replace("'",  "''") + "'" + ",");
					fichier.write("'" + stopArea.getIdNavitia() + "'" + ",");
					fichier.write("'" + stopArea.getLongitude() + "'" + ",");
					fichier.write("'" + stopArea.getLatitude() + "'" + ",");
					fichier.write("'" + stopArea.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreas.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreaLine() throws HerculeTechnicalException {
		try {

			List<StopAreaLine> listModel = HerculeDao.getAllStopAreaLines();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/stopAreasLine.sql")
						);

				for(StopAreaLine stopArea : listModel) {

					fichier.write("insert into `T_STOP_AREA_LINE` values (");
					fichier.write(stopArea.getIdStopArea() + ",");
					fichier.write(stopArea.getIdLine() + ",");
					fichier.write("'" + stopArea.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreaLine.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreaRoute() throws HerculeTechnicalException {
		try {

			List<StopAreaRoute> listModel = HerculeDao.getAllStopAreaRoutes();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/stopAreasRoute.sql")
						);

				for(StopAreaRoute stopArea : listModel) {

					fichier.write("insert into `T_STOP_AREA_ROUTE` values (");
					fichier.write(stopArea.getIdStopArea() + ",");
					fichier.write(stopArea.getIdRoute() + ",");
					fichier.write("'" + stopArea.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreaLine.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopPoints() throws HerculeTechnicalException {
		try {

			List<StopPointModel> listModel = HerculeDao.getAllStopPoints();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter("D:/logs/stopPoints.sql")
						);

				for(StopPointModel stopPoint : listModel) {

					fichier.write("insert into `T_STOP_POINT` values (");
					fichier.write(stopPoint.getIdStopPoint() + ",");
					fichier.write("'" + stopPoint.getName().replace("'", "''") + "'" + ",");
					fichier.write(stopPoint.getIdStopArea() + ",");
					fichier.write(stopPoint.getIdRoute() + ",");
					fichier.write("'" + stopPoint.getLongitude() + "'" + ",");
					fichier.write("'" + stopPoint.getLatitude() + "'" + ",");
					fichier.write("'" + stopPoint.getLastUpdate() + "'");
					fichier.write(");");

					fichier.newLine();
				}
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopPoints.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}


}

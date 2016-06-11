package com.hercule.workflow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.db.DatabaseConnection;
import com.hercule.commun.interfaces.IWorkflow;
import com.hercule.commun.navitia.Line;
import com.hercule.commun.navitia.Lines;
import com.hercule.commun.navitia.Link;
import com.hercule.commun.navitia.Network;
import com.hercule.commun.navitia.Networks;
import com.hercule.commun.navitia.Route;
import com.hercule.commun.navitia.Routes;
import com.hercule.commun.navitia.StopArea;
import com.hercule.commun.navitia.StopAreas;
import com.hercule.commun.navitia.StopPoint;
import com.hercule.commun.navitia.StopPoints;
import com.hercule.manager.RestManager;
import com.hercule.utils.exception.HerculeFunctionalException;
import com.hercule.utils.exception.HerculeTechnicalException;

public class WorkflowImportDatabase implements IWorkflow {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(WorkflowImportDatabase.class.getName());
	private static String propertiesFilename;

	public WorkflowImportDatabase() {

	}


	public void executer(String fileName, String args[]) {

		logger.info("Traitement d'import de la BDD");

		long startTime = System.currentTimeMillis();
		try {
			propertiesFilename = fileName;
			DatabaseConnection.initConnection(propertiesFilename);
			RestManager.initRestManager(propertiesFilename);
			importDataFromNavitia();
			DatabaseConnection.closeConnection();
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info("Fin du traitement. Temps d'ex�cution :"+ (endTime-startTime) / 1000 + "s");

	}


	public void importDataFromNavitia() throws HerculeTechnicalException {

		StopPoints tmpStopPoints;
		StopAreas tmpStopAreas;
		int idNetwork = -1;
		int idLine = -1;
		int idRoute = -1;
		int idStopArea = 1;

		try {

			/** R�cup�ration des networks autoris�s par Metropaul */
			Map<String, String> authNetworks = HerculeDao.getAuthorizedNetworks();

			if(authNetworks == null) {
				logger.error("Aucun network autoris� trouv�");
				return;
			}

			/** Cr�ation du template Rest pour l'appel � Navitia */
			RestTemplate restTemplate = RestManager.createRestTemplate();

			for (String networkName : authNetworks.keySet()) {
				
				/** R�cup�ration du network */
				String realNetworkId = authNetworks.get(networkName);
				Networks networks = RestManager.callNavitiaAuthNetwork(restTemplate, realNetworkId);
				
				/** Insertion du network en BDD */
				Network network = networks.getNetworks().get(0);
				idNetwork = HerculeDao.insertNetwork(network);

				/** Appel WS pour r�cup�ration des Lines */
				Lines tmpLines = RestManager.callNavitiaLines(restTemplate, network.getId());
				int pageLines = 0;

				do {

					/** Appel WS pour r�cup�ration des Lines pagin�es */
					if(pageLines != 0) {
						tmpLines = RestManager.callNavitiaLinesPagination(restTemplate, network.getId(), pageLines);
					}
					
					for (Line line : tmpLines.getLines()) {
						/** Insertion de la ligne en BDD */
						idLine = HerculeDao.insertLine(line, idNetwork);
						
						/** Appel WS pour r�cup�ration des Routes */
						Routes tmpRoutes = RestManager.callNavitiaRoutes(restTemplate, network.getId(), line.getId());
						int pageRoutes = 0;
						
						do {
							/** Appel WS pour r�cup�ration des Routes pagin�es */
							if(pageRoutes != 0) {
								tmpRoutes = RestManager.callNavitiaRoutesPagination(restTemplate, network.getId(), line.getId(), pageRoutes);
							}
							
							for (Route route : tmpRoutes.getRoutes()) {

								/** Insertion de la Route en BDD */
								idRoute = HerculeDao.insertRoute(route, idLine);

								/** Appel WS pour r�cup�ration des StopArea */
								tmpStopAreas = RestManager.callNavitiaStopAreas(restTemplate, network.getId(), line.getId(), route.getId());
								int pageStopAreas = 0;
								
								do {
									if(pageStopAreas != 0) {
										tmpStopAreas = RestManager.callNavitiaStopAreasPagination(restTemplate, network.getId(), line.getId(), route.getId(), pageStopAreas);
									}
									
									for (StopArea stopArea : tmpStopAreas.getStop_areas()) {

										/** Insertion de la StopArea en BDD */
										idStopArea = HerculeDao.insertStopArea(stopArea);

										HerculeDao.insertStopAreaLine(idStopArea, idLine);
										HerculeDao.insertStopAreaRoute(idStopArea, idRoute);

										/** Appel WS pour r�cup�ration des StopPoints */
										tmpStopPoints = RestManager.callNavitiaStopPoints(restTemplate, stopArea.getId());

										for (StopPoint stopPoint : tmpStopPoints.getStop_points()){
											/** Insertion d'un StopPoint en BDD */
											HerculeDao.insertStopPoint(stopPoint, idStopArea, idRoute);
										}
									
									}
									
									pageStopAreas++;
								} while(hasNextPage(tmpStopAreas.getLinks()));
							}
							
							pageRoutes ++;
						} while(hasNextPage(tmpRoutes.getLinks()));
					}
					
					pageLines++;
				} while(hasNextPage(tmpLines.getLinks()));
			}
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			DatabaseConnection.rollBack();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			DatabaseConnection.rollBack();
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			DatabaseConnection.rollBack();
		} catch (HerculeFunctionalException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			DatabaseConnection.rollBack();
		}
	}
	
	private boolean hasNextPage(List<Link> links) {
		for(Link link : links) {
			if(link.getType() != null && link.getType().equals("next")) {
				return true;
			}
		}
		
		return false;
	}
	


}

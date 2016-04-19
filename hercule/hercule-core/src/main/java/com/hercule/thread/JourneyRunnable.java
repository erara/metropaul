package com.hercule.thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.navitia.Journey;
import com.hercule.commun.navitia.Journeys;
import com.hercule.commun.navitia.Section;
import com.hercule.commun.navitia.StopDateTime;
import com.hercule.manager.RestManager;
import com.hercule.utils.exception.HerculeTechnicalException;

public class JourneyRunnable implements Runnable{
	
	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(JourneyRunnable.class.getName());
	
	private Thread t;
	private String threadName;
	private StopAreaModel stopAreaModelFrom;
	private List<StopAreaModel> listTo;
	
	private static Map<String, String> mapType = new HashMap<String, String>();

	static {
		mapType.put("street_network", "s_t");
		mapType.put("public_transport", "p_t");
		mapType.put("transfer", "trs");
		mapType.put("waiting", "wt");
	}
	
	public JourneyRunnable(String threadName, StopAreaModel stopAreaModelFrom, List<StopAreaModel> listTo) {
		this.threadName = threadName;
		this.stopAreaModelFrom = stopAreaModelFrom;
		this.listTo = listTo;
	}
	
	public void run() {
		logger.info("Running " +  threadName );

		try {
			
			logger.info("Création du fichier d'export D:/logs/" + stopAreaModelFrom.getName() + ".csv");
			BufferedWriter fichier = new BufferedWriter(new FileWriter("D:/logs/itineraires/" + stopAreaModelFrom.getName().replaceAll("/", " ") + ".csv", false));
			RestTemplate restTemplate = RestManager.createRestTemplate();

			for(StopAreaModel stopAreaModelTo : listTo) {
				logger.info("calcul itinéraire de " + stopAreaModelFrom.getName() + " vers " + stopAreaModelTo.getName());

				StringBuilder itineraireOutput = new StringBuilder();
				itineraireOutput.append(stopAreaModelFrom.getIdStopArea() + ";");
				itineraireOutput.append(stopAreaModelTo.getIdStopArea() + ";");
				
				Journeys journeys = RestManager.callJourney(restTemplate, stopAreaModelFrom, stopAreaModelTo, "1", "20160404T121128");
				
				for(Journey myJourney : journeys.getJourneys()) {
					
					if(myJourney.getSections() != null) {

						for(int i = 0; i < myJourney.getSections().size(); i++) {
							Section section = myJourney.getSections().get(i);
							
							itineraireOutput.append(mapType.get(section.getType()) + "#");
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
		
			
			
			
		} catch (IOException e) {
			logger.error("Impossible de créer le fichierD:/logs/" + stopAreaModelFrom.getName() + ".csv");
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		} catch (HerculeTechnicalException e) {
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

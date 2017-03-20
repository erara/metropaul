package com.hercule.workflow;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.constantes.WSConstantes;
import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.db.DatabaseConnection;
import com.hercule.commun.interfaces.IWorkflow;
import com.hercule.manager.RestManager;
import com.hercule.thread.JourneyRunnable;
import com.hercule.utils.exception.HerculeTechnicalException;
import com.hercule.utils.file.ReadPropertiesFile;

public class WorkflowItineraires implements IWorkflow{
	
	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(WorkflowItineraires.class.getName());
	private static String propertiesFilename;
	private ThreadPoolExecutor threadPool = null;
	private static int CORE_POOL_SIZE;
	private static String datetime;
	private static String datePremierDepartSemaine;
	private static String dateDernierDepartSemaine;
	private static String datePremierDepartWE;
	private static String dateDernierDepartWE;
	
	public WorkflowItineraires() {

	}
	
	public void executer(String propertiesFileName, String[] args) {
		logger.info("Traitement de calcul des itinéraires");

		long startTime = System.currentTimeMillis();
		try {
			propertiesFilename = propertiesFileName;
			DatabaseConnection.initConnection(propertiesFilename);
			RestManager.initRestManager(propertiesFilename);
			Properties documentProperties = ReadPropertiesFile.loadPropsFromFile(propertiesFilename);
			datetime = documentProperties.getProperty(WSConstantes.PROP_WS_DATETIME);
			datePremierDepartSemaine = documentProperties.getProperty(WSConstantes.DATE_PREMIER_DEPART_SEMAINE);
			dateDernierDepartSemaine = documentProperties.getProperty(WSConstantes.DATE_DERNIER_DEPART_SEMAINE);
			datePremierDepartWE = documentProperties.getProperty(WSConstantes.DATE_PREMIER_DEPART_WE);
			dateDernierDepartWE = documentProperties.getProperty(WSConstantes.DATE_DERNIER_DEPART_WE);
			CORE_POOL_SIZE = Integer.parseInt(documentProperties.getProperty(WSConstantes.PROP_NB_THREAD));
			calculItineraires();
			DatabaseConnection.closeConnection();
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info("Fin du traitement. Temps d'exécution :"+ (endTime-startTime) / 1000 + "s");
	}

	
	private void calculItineraires()  throws HerculeTechnicalException {

		/** Création du template Rest pour l'appel à Navitia */
		List<StopAreaModel> listFrom = HerculeDao.getAllStopAreasNotCalculated();

		if(listFrom != null) {

			try {
				int maximumPoolSize = CORE_POOL_SIZE;
				long keepAliveTime = 1;
				TimeUnit timeUnit = TimeUnit.SECONDS;
				
				
				
				this.threadPool = new ThreadPoolExecutor(
						maximumPoolSize, 
						maximumPoolSize, 
						keepAliveTime, 
						timeUnit, 
						new ArrayBlockingQueue<Runnable>(300),
						new RejectedExecutionHandler() {
				            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				                try {
				                    executor.getQueue().put(r);
				                } catch (InterruptedException e) {
				                    throw new RuntimeException("Interrupted while submitting task", e);
				                }
				            }
				        }
					);

			} 
			catch (Exception e) {
				throw new HerculeTechnicalException("Echec de l'initiatlisation du threadPool: " + e);
			}

			ExecutorService executor = Executors.newFixedThreadPool(CORE_POOL_SIZE);
			
			for(StopAreaModel stopAreaModel : listFrom) {
				
				JourneyRunnable journeyRunnable = new JourneyRunnable(stopAreaModel.getName(), stopAreaModel, datetime, datePremierDepartSemaine, dateDernierDepartSemaine, datePremierDepartWE, dateDernierDepartWE);
				logger.info("Thread pour la station " + stopAreaModel.getName());
				executor.execute(journeyRunnable);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
	        }
		}
	}
}

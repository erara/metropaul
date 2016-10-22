package com.hercule.workflow;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.constantes.FileConstantes;
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
	private static int CORE_POOL_SIZE = 10;
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
		RestTemplate restTemplate = RestManager.createRestTemplate();
		List<StopAreaModel> listFrom = HerculeDao.getAllStopAreasNotCalculated();

		if(listFrom != null) {

			try {
				BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
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
				
//				boolean isThreadRunning = false;
//
//				do {
//					try {
//						logger.info("Recherche d'un thread disponible pour la station " + stopAreaModel.getName());
//
//
//						this.threadPool.execute(journeyRunnable);
//
//						//Execution du PaySrunnable instancié : on indique qu'un thread est occupé
//						isThreadRunning = true;
//					} catch(RejectedExecutionException e) {
//						throw new HerculeTechnicalException(e.getMessage());
//					} catch (Exception e) {
//						throw new HerculeTechnicalException("Impossible d'ordonnancer un nouveau thread pour la station " + stopAreaModel.getName());
//					}
//				} while (!isThreadRunning);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
	        }
//			
//			while (this.threadPool.getActiveCount() > 0) {
//				//logger.info("Attente de l'execution des " + this.threadPool.getActiveCount() + " derniers threads en cours...");
//
//			}
//			this.threadPool.shutdown();
		}
	}
}

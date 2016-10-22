package com.hercule.workflow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.hercule.commun.beans.LineModel;
import com.hercule.commun.beans.NetworkModel;
import com.hercule.commun.beans.RouteModel;
import com.hercule.commun.beans.StopAreaLine;
import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.beans.StopAreaRoute;
import com.hercule.commun.beans.StopPointModel;
import com.hercule.commun.constantes.FileConstantes;
import com.hercule.commun.dao.HerculeDao;
import com.hercule.commun.db.DatabaseConnection;
import com.hercule.commun.interfaces.IWorkflow;
import com.hercule.utils.exception.HerculeTechnicalException;
import com.hercule.utils.file.ReadPropertiesFile;

public class WorkflowExportDatabase implements IWorkflow {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(WorkflowExportDatabase.class.getName());
	private static String propertiesFilename;
	private static String outputDirectory;

	public WorkflowExportDatabase() {

	}

	public void executer(String fileName, String[] args) {



		logger.info("Traitement d'import de la BDD");

		long startTime = System.currentTimeMillis();
		try {
			propertiesFilename = fileName;
			DatabaseConnection.initConnection(propertiesFilename);
			Properties documentProperties = ReadPropertiesFile.loadPropsFromFile(propertiesFilename);
			outputDirectory = documentProperties.getProperty(FileConstantes.OUT_DIRECTORY);
			exportNetworks(outputDirectory + documentProperties.getProperty(FileConstantes.NETWORKS_FILE));
			exportLines(outputDirectory + documentProperties.getProperty(FileConstantes.LINES_FILE));
			exportRoutes(outputDirectory + documentProperties.getProperty(FileConstantes.ROUTES_FILE));
			exportStopAreas(outputDirectory + documentProperties.getProperty(FileConstantes.STOP_AREAS_FILE));
			exportStopAreaLine(outputDirectory + documentProperties.getProperty(FileConstantes.STOP_AREAS_LINE_FILE));
			exportStopAreaRoute(outputDirectory + documentProperties.getProperty(FileConstantes.STOP_AREAS_ROUTE_FILE));
			exportStopPoints(outputDirectory + documentProperties.getProperty(FileConstantes.STOP_POINTS_FILE));
			exportItineraires(outputDirectory + documentProperties.getProperty(FileConstantes.ITINERAIRES_FILE));
			DatabaseConnection.closeConnection();
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		long endTime = System.currentTimeMillis();
		logger.info("Fin du traitement. Temps d'exécution :"+ (endTime-startTime) / 1000 + "s");

	}

	private void exportNetworks(String file) throws HerculeTechnicalException {
		try {

			List<NetworkModel> listModel = HerculeDao.getAllNetwork();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(NetworkModel network : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(network.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier networks.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}

	}

	private void exportLines(String file) throws HerculeTechnicalException {
		try {

			List<LineModel> listModel = HerculeDao.getAllLines();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(LineModel line : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(line.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier lines.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportRoutes(String file) throws HerculeTechnicalException {
		try {

			List<RouteModel> listModel = HerculeDao.getAllRoutes();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(RouteModel route : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(route.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier routes.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreas(String file) throws HerculeTechnicalException {
		try {

			List<StopAreaModel> listModel = HerculeDao.getAllStopAreas();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));
				
				fichier.write("[");
				boolean debut = true;
				for(StopAreaModel stopArea : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(stopArea.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();
			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreas.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreaLine(String file) throws HerculeTechnicalException {
		try {

			List<StopAreaLine> listModel = HerculeDao.getAllStopAreaLines();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(StopAreaLine stopArea : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(stopArea.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreaLine.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopAreaRoute(String file) throws HerculeTechnicalException {
		try {

			List<StopAreaRoute> listModel = HerculeDao.getAllStopAreaRoutes();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(StopAreaRoute stopArea : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(stopArea.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopAreaLine.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}

	private void exportStopPoints(String file) throws HerculeTechnicalException {
		try {

			List<StopPointModel> listModel = HerculeDao.getAllStopPoints();

			if(listModel != null) {
				BufferedWriter fichier = new BufferedWriter(
						new FileWriter(file));

				fichier.write("[");
				boolean debut = true;
				for(StopPointModel stopPoint : listModel) {
					if(!debut) {
						fichier.write(",");
					}
					fichier.write(stopPoint.transformToJson());
					debut = false;
				}
				fichier.write("]");
				fichier.close();

			}

		} catch (IOException e) {
			throw new HerculeTechnicalException("Erreur création du fichier stopPoints.sql");
		} catch (HerculeTechnicalException e) {
			throw e;
		}
	}
	
	private void exportItineraires(String file) {
		try {
			HerculeDao.getItineraires(file);
		} catch (HerculeTechnicalException e) {
			logger.error(e.getMessage());
		}
	}


}

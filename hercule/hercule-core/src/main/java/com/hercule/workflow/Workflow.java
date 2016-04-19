package com.hercule.workflow;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hercule.commun.interfaces.IWorkflow;

public class Workflow {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(Workflow.class.getName());
	private static String propertiesFile = "hercule.properties";

	private static Map<String, IWorkflow> CU = new HashMap<String, IWorkflow>();

	static {
		CU.put("USE_CASE_01", new WorkflowImportDatabase());
		CU.put("USE_CASE_02", new WorkflowCalculDeltaDatabase());
		CU.put("USE_CASE_03", new WorkflowExportDatabase());
		CU.put("USE_CASE_04", new WorkflowItineraires());
	}



	public static void main(String[] args) {

		logger.info("Début du traitement");
		IWorkflow w = null;
		
		String operation = args[0];
		
		if(operation != null) {
			if(operation.equals("import")) {
				w = CU.get("USE_CASE_01");
			} else if(operation.equals("delta")) {
				w = CU.get("USE_CASE_03");
			} else if(operation.equals("export")) {
				w = CU.get("USE_CASE_03");
			} else if(operation.equals("itineraires")){
				w = CU.get("USE_CASE_04");
			}else {
				logger.error("Opération " + operation + " inconnue");
				System.exit(1);
			}
			
			w.executer(propertiesFile, args);

			logger.info("Fin du traitement");
		}
	}
}

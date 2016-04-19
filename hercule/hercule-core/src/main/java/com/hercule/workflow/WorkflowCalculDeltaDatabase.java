package com.hercule.workflow;

import org.apache.log4j.Logger;

import com.hercule.commun.interfaces.IWorkflow;

public class WorkflowCalculDeltaDatabase implements IWorkflow {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(WorkflowCalculDeltaDatabase.class.getName());
	
	public WorkflowCalculDeltaDatabase() {
		
	}

	public void executer(String propertiesFilename, String[] args) {
		logger.info("Calcul du delta de la BDD");
		logger.info("Fin du traitement");
	}
	
}

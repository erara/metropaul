package com.hercule.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hercule.utils.exception.HerculeTechnicalException;

public class ReadPropertiesFile {
	
	/** Log4j logger for this class */
	private static Logger logger = Logger.getLogger(ReadPropertiesFile.class);

	
	public static Properties loadPropsFromFile(String iPropertyFilename) throws IOException, HerculeTechnicalException {

    	logger.info("Récupération des paramètres de la BDD dans le fichier de configuration");
    	
    	ClassLoader loader = ReadPropertiesFile.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(iPropertyFilename);

		if (in == null) {
			String message = "Impossible de trouver le fichier de configuration ["
					+ iPropertyFilename + "] dans le classpath.";
			throw new IOException(message);
		}
		
		Properties props = new Properties();
		
		try {
			props.load(in);
		} catch (IOException ex) {
			String message = "Erreur lors de la lecture dans le fichier de configuration";
			throw new IOException(message);
		}
		
		return props;
	}	
}

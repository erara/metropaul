package com.hercule.commun.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hercule.utils.exception.HerculeTechnicalException;
import com.hercule.utils.file.ReadPropertiesFile;


public class DatabaseConnection {
	
	/** Log4j logger for this class */
	private static Logger logger = Logger.getLogger(DatabaseConnection.class);
	
	private static Properties documentProperties = null;
	private static final String PROP_DB_LOGIN = "dbLogin";
	private static final String PROP_DB_PASSWD = "dbPasswd";
	private static final String PROP_DB_URL = "dbUrl";
    private static Connection conn;
    
    public static void initConnection(String fileName) throws HerculeTechnicalException {
    	try {
    		documentProperties = ReadPropertiesFile.loadPropsFromFile(fileName);
    		createDBConnection();
    	} catch (IOException e) {
    		throw new HerculeTechnicalException(e.getMessage());
    	} catch (SQLException e) {
    		throw new HerculeTechnicalException(e.getMessage());
    	}
    }
    
    public static void createDBConnection() throws SQLException {
    	
    	logger.info("Création de la connexion au serveur MySQL");

    	try {
    		conn = DriverManager.getConnection(
					documentProperties.getProperty(PROP_DB_URL),
					documentProperties.getProperty(PROP_DB_LOGIN),
					documentProperties.getProperty(PROP_DB_PASSWD));
    		conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new SQLException("Impossible de se connecter au serveur MySQL");
		}

    	logger.info("Connexion effectuée avec succès");
    }
    
    public static Connection getConnection() throws HerculeTechnicalException {
    	return conn;
    }
    
    public static void closeConnection() throws HerculeTechnicalException {

    	logger.info("Fermeture de la connexion au serveur MySQL");

    	if(conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur lors de la fermeture de la connexion à la BDD");
			}
    	}
    }
    
    public static void rollBack() throws HerculeTechnicalException {
    	logger.info("Rollback des modifications en BDD");

    	if(conn != null) {
    		try {
				conn.rollback();
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur lors du rollback de la BDD");
			}
    	}
    }
    
    public static void commit() throws HerculeTechnicalException {
    	logger.info("Commit des modifications en BDD");

    	if(conn != null) {
    		try {
				conn.commit();
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur lors du commit de la BDD");
			}
    	}
    }
}

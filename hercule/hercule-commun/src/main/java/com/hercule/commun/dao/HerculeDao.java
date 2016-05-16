package com.hercule.commun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hercule.commun.beans.LineModel;
import com.hercule.commun.beans.NetworkModel;
import com.hercule.commun.beans.RouteModel;
import com.hercule.commun.beans.StopAreaLine;
import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.beans.StopAreaRoute;
import com.hercule.commun.beans.StopPointModel;
import com.hercule.commun.constantes.DBConstantes;
import com.hercule.commun.db.DatabaseConnection;
import com.hercule.commun.navitia.Line;
import com.hercule.commun.navitia.Network;
import com.hercule.commun.navitia.Route;
import com.hercule.commun.navitia.StopArea;
import com.hercule.commun.navitia.StopPoint;
import com.hercule.utils.exception.HerculeFunctionalException;
import com.hercule.utils.exception.HerculeTechnicalException;

public class HerculeDao {

	/** Private Logger logger */
	private static final Logger logger = Logger.getLogger(HerculeDao.class.getName());
	
	
	public static Map<String, String> getAuthorizedNetworks() throws HerculeTechnicalException {

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_AUTHORIZED_NETWORKS);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		Map<String, String> authNetworks = null;

		try {

			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();

			while (res.next()) {
				if(authNetworks == null) {
					authNetworks = new HashMap<String, String>();
				}
				
				authNetworks.put(res.getString(DBConstantes.T_AUTH_NETWORKS_NAME), res.getString(DBConstantes.T_AUTH_NETWORKS_ID));
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAuthorizedNetworks, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return authNetworks;
	}

	/**
	 * Création d'une Ligne en BDD
	 * @param line
	 * @param idNetwork
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static int insertLine(Line line, int idNetwork) throws HerculeTechnicalException {
		int lastId = -1;

		if(!existLine(line)) {

//			logger.info("insertLine " + line.getId());

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_LINE);
			query.append(" (");
			query.append(DBConstantes.T_LINE_CODE);
			query.append(",");
			query.append(DBConstantes.T_LINE_NAME);
			query.append(",");
			query.append(DBConstantes.T_LINE_NETWORK_ID);
			query.append(",");
			query.append(DBConstantes.T_LINE_OPENING_TIME);
			query.append(",");
			query.append(DBConstantes.T_LINE_CLOSING_TIME);
			query.append(",");
			query.append(DBConstantes.T_LINE_COLOR);
			query.append(",");
			query.append(DBConstantes.T_LINE_TRANSPORT_TYPE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?,?,?,?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;
			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				pstmt.setString (1, line.getCode());
				pstmt.setString (2, line.getName());
				pstmt.setInt (3, idNetwork);
				pstmt.setString (4, line.getOpening_time());
				pstmt.setString (5, line.getClosing_time());
				pstmt.setString (6, line.getColor());
				pstmt.setString (7, line.getCommercial_mode().getName());
				pstmt.setDate (8, new java.sql.Date(today.getTime()));
				pstmt.execute();

				res = pstmt.getGeneratedKeys();
				if (res.next()) {
					lastId = res.getInt(1);
				}
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertNetwork, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.warn("La ligne  " + line.getName() + " existe déjà");
		}

		return lastId;
	}

	/**
	 * Création d'un réseau en BDD
	 * @param s
	 * @return
	 * @throws HerculeTechnicalException
	 * @throws HerculeFunctionalException
	 */
	public static int insertNetwork(Network network) throws HerculeTechnicalException, HerculeFunctionalException {
		int lastId = -1;

		if(!existNetwork(network)) {

//			logger.info("insertNetwork " + network.getId());

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_NETWORK);
			query.append(" (");
			query.append(DBConstantes.T_NETWORK_TYPE);
			query.append(",");
			query.append(DBConstantes.T_NETWORK_GENERIQUE_TYPE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				pstmt.setString (1, network.getId());
				pstmt.setString (2, "ToDo");
				pstmt.setDate (3, new java.sql.Date(today.getTime()));
				pstmt.execute();

				res = pstmt.getGeneratedKeys();
				if (res.next()) {
					lastId = res.getInt(1);
				}
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertNetwork, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.warn("Le réseau  " + network.getId() + " existe déjà");
		}

		return lastId;
	}

	/**
	 * Création d'une Route en BDD
	 * @param route
	 * @return
	 * @throws HerculeTechnicalException
	 * @throws HerculeFunctionalException
	 */
	public static int insertRoute(Route route, int idLine) throws HerculeTechnicalException, HerculeFunctionalException {
		int lastId = -1;

		if(!existRoute(route)) {

//			logger.info("insertRoute " + route.getId());

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_ROUTE);
			query.append(" (");
			query.append(DBConstantes.T_ROUTE_LINE_ID);
			query.append(",");
			query.append(DBConstantes.T_ROUTE_NAME);
			query.append(",");
			query.append(DBConstantes.T_ROUTE_DESTINATION);
			query.append(",");
			query.append(DBConstantes.T_ROUTE_OPENING_TIME);
			query.append(",");
			query.append(DBConstantes.T_ROUTE_CLOSING_TIME);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?,?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;
			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt (1, idLine);
				pstmt.setString (2, route.getId());
				pstmt.setString(3,  route.getDirection().getName());
				pstmt.setString(4, route.getLine().getOpening_time());
				pstmt.setString(5, route.getLine().getClosing_time());
				pstmt.setDate (6, new java.sql.Date(today.getTime()));
				pstmt.execute();

				res = pstmt.getGeneratedKeys();
				if (res.next()) {
					lastId = res.getInt(1);
				}
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertRoute, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.warn("La route  " + route.getId() + " existe déjà");
		}

		return lastId;
	}

	/**
	 * Création d'un StopArea en BDD
	 * @param stopArea
	 * @param idLigne
	 * @return
	 * @throws HerculeTechnicalException 
	 */
	public static int insertStopArea(StopArea stopArea) throws HerculeTechnicalException {
		int lastId = -1;

		if(!existStopArea(stopArea)) {

//			logger.info("insertStopArea " + stopArea.getId());

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_STOP_AREA);
			query.append(" (");
			query.append(DBConstantes.T_STOP_AREA_NAME);
			query.append(",");
			query.append(DBConstantes.T_STOP_AREA_ID_NAVITIA);
			query.append(",");
			query.append(DBConstantes.T_STOP_AREA_LONGITUDE);
			query.append(",");
			query.append(DBConstantes.T_STOP_AREA_LATITUDE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
				pstmt.setString (1, stopArea.getName());
				pstmt.setString (2, stopArea.getId());
				pstmt.setString (3, stopArea.getCoord().getLon());
				pstmt.setString (4, stopArea.getCoord().getLat());
				pstmt.setDate (5, new java.sql.Date(today.getTime()));
				pstmt.execute();

				res = pstmt.getGeneratedKeys();
				if (res.next()) {
					lastId = res.getInt(1);
				}
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopArea, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
//			logger.warn("Le stopArea  " + stopArea.getName() + " existe déjà");

			StringBuilder query = new StringBuilder("select id_stop_area from ");
			query.append(DBConstantes.T_STOP_AREA);
			query.append(" where name = ?");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString (1, stopArea.getName());
				res = pstmt.executeQuery();

				if (res.next()) {
					lastId = res.getInt(1);
				}
				logger.info("Stop area id récupéré : " + lastId);
			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopArea, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		}

		return lastId;
	}

	/**
	 * Création d'un StopAreaLine en BDD
	 * @param idStopArea
	 * @param idLine
	 * @throws HerculeTechnicalException
	 */
	public static void insertStopAreaLine(int idStopArea, int idLine) throws HerculeTechnicalException {


		if(!existStopAreaLine(idStopArea, idLine)) {
//			logger.info("insertStopAreaLine " + idStopArea + "/"+ idLine);

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_STOP_AREA_LINE);
			query.append(" (");
			query.append(DBConstantes.T_STOP_AREA_LINE_ID_STOP_AREA);
			query.append(",");
			query.append(DBConstantes.T_STOP_AREA_LINE_ID_LINE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setInt (1, idStopArea);
				pstmt.setInt (2, idLine);
				pstmt.setDate (3, new java.sql.Date(today.getTime()));
				pstmt.execute();

			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopAreaLine, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.warn("insertStopAreaLine " + idStopArea + "/"+ idLine + " existe déjà");
		}


	}

	/**
	 * Création d'un StopAreaLine en BDD
	 * @param idStopArea
	 * @param idLine
	 * @throws HerculeTechnicalException
	 */
	public static void insertStopAreaRoute(int idStopArea, int idRoute) throws HerculeTechnicalException {


		if(!existStopAreaRoute(idStopArea, idRoute)) {


//			logger.info("insertStopAreaRoute " + idStopArea + "/"+ idRoute);

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_STOP_AREA_ROUTE);
			query.append(" (");
			query.append(DBConstantes.T_STOP_AREA_ROUTE_ID_STOP_AREA);
			query.append(",");
			query.append(DBConstantes.T_STOP_AREA_ROUTE_ID_ROUTE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				Date today = new Date();

				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setInt (1, idStopArea);
				pstmt.setInt (2, idRoute);
				pstmt.setDate (3, new java.sql.Date(today.getTime()));
				pstmt.execute();

			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopAreaRoute, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.warn("insertStopAreaRoute " + idStopArea + "/"+ idRoute + " existe déjà");
		}
	}

	public static void insertStopPoint(StopPoint stopPoint, int idStopArea, int idRoute) throws HerculeTechnicalException {

		if(!existStopPoint(stopPoint, idStopArea, idRoute)) {


//			logger.info("insertStopPoint " + stopPoint.getId() + "/"+ idStopArea + "/" + idRoute);

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_STOP_POINT);
			query.append(" (");
			query.append(DBConstantes.T_STOP_POINT_NAVITIA_ID);
			query.append(",");
			query.append(DBConstantes.T_STOP_POINT_NAME);
			query.append(",");
			query.append(DBConstantes.T_STOP_POINT_ID_STOP_AREA);
			query.append(",");
			query.append(DBConstantes.T_STOP_POINT_ID_ROUTE);
			query.append(",");
			query.append(DBConstantes.T_STOP_POINT_LONGITUDE);
			query.append(",");
			query.append(DBConstantes.T_STOP_POINT_LATITUDE);
			query.append(",");
			query.append(DBConstantes.T_LAST_UPDATE);
			query.append(") values (?,?,?,?,?,?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				Date today = new Date();
				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString (1, stopPoint.getId());
				pstmt.setString (2, stopPoint.getName());
				pstmt.setInt (3, idStopArea);
				pstmt.setInt (4, idRoute);
				pstmt.setString (5, stopPoint.getCoord().getLon());
				pstmt.setString (6, stopPoint.getCoord().getLat());
				pstmt.setDate (7, new java.sql.Date(today.getTime()));
				pstmt.execute();

			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopPoint, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		} else {
			logger.info("insertStopPoint " + stopPoint.getId() + "/"+ idStopArea + "/" + idRoute +" existe déjà");

		}
	}
	
	public synchronized static void insertItineraires(int id_stop_area_from, List<StringBuilder> itineraires) throws HerculeTechnicalException {

		for(StringBuilder builder : itineraires) {
//			logger.info("insertItineraire " + id_stop_area_from);

			StringBuilder query = new StringBuilder("insert into ");
			query.append(DBConstantes.T_ITINERAIRES);
			query.append(" (");
			query.append(DBConstantes.T_ITINERAIRES_STOP_AREA_FROM);
			query.append(",");
			query.append(DBConstantes.T_ITINERAIRES_ITINERAIRE);
			query.append(") values (?,?)");

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;

			try {
				conn = DatabaseConnection.getConnection();
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setInt (1, id_stop_area_from);
				pstmt.setString(2, builder.toString());
				pstmt.execute();

			} catch (SQLException e) {
				throw new HerculeTechnicalException("Erreur insertStopPoint, " + e.getMessage());
			} finally {
				close(null, pstmt, res);
			}
		}
		
	}

	/**
	 * Fonction de vérification d'existence d'une ligne
	 * @param line
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existLine(Line line) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_LINE);
		query.append(" where ");
		query.append(DBConstantes.T_LINE_NAME);
		query.append(" = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString (1, line.getId());
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existLine, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	/**
	 * Fonction de vérification d'existence d'une ligne
	 * @param line
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existStopPoint(StopPoint stopPoint, int idStopArea, int idRoute) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_POINT);
		query.append(" where ");
		query.append(DBConstantes.T_STOP_POINT_NAME);
		query.append(" = ?");
		query.append(" and id_route ");
		query.append(" = ?");
		query.append(" and id_stop_area");
		query.append(" = ?");
		query.append(" and longitude ");
		query.append(" = ?");
		query.append(" and latitude ");
		query.append(" = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString (1, stopPoint.getName());
			pstmt.setInt (2, idRoute);
			pstmt.setInt (3, idStopArea);
			pstmt.setString (4, stopPoint.getCoord().getLon());
			pstmt.setString (5, stopPoint.getCoord().getLat());
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existLine, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	/**
	 * Fonction de vérification d'existence d'une ligne
	 * @param line
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existStopAreaLine(int idStopArea, int idLine) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA_LINE);
		query.append(" where ");
		query.append(DBConstantes.T_STOP_AREA_LINE_ID_STOP_AREA);
		query.append(" = ? and ");
		query.append(DBConstantes.T_STOP_AREA_LINE_ID_LINE);
		query.append(" = ?");


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt (1, idStopArea);
			pstmt.setInt (2, idLine);
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existStopAreaLine, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	/**
	 * Fonction de vérification d'existence d'une ligne
	 * @param line
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existStopAreaRoute(int idStopArea, int idRoute) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA_ROUTE);
		query.append(" where ");
		query.append(DBConstantes.T_STOP_AREA_ROUTE_ID_STOP_AREA);
		query.append(" = ? and ");
		query.append(DBConstantes.T_STOP_AREA_ROUTE_ID_ROUTE);
		query.append(" = ?");


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt (1, idStopArea);
			pstmt.setInt (2, idRoute);
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existStopAreaRoute, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	/**
	 * Fonction de vérification d'existence d'un Network
	 * @param network
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existNetwork(Network network) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_NETWORK);
		query.append(" where ");
		query.append(DBConstantes.T_NETWORK_ID);
		query.append(" = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString (1, network.getId());
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existNetwork, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	/**
	 * Fonction de vérification d'existence d'un stopArea
	 * @param route
	 * @return
	 * @throws HerculeTechnicalException
	 */
	public static boolean existRoute(Route route) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_ROUTE);
		query.append(" where ");
		query.append(DBConstantes.T_ROUTE_NAME);
		query.append(" = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString (1, route.getId());
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existRoute, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}

	public static boolean existStopArea(StopArea stopArea) throws HerculeTechnicalException {
		boolean exist = false;

		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA);
		query.append(" where ");
		query.append(DBConstantes.T_STOP_AREA_ID_NAVITIA);
		query.append(" = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString (1, stopArea.getId());
			res = pstmt.executeQuery();

			while (res.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur existStopArea, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}

		return exist;
	}
	
	
	
	public static List<NetworkModel> getAllNetwork() throws HerculeTechnicalException {
		
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_NETWORK);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<NetworkModel> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<NetworkModel>();
				}
				
				NetworkModel model = new NetworkModel();
				model.setIdNetwork(res.getInt(DBConstantes.T_NETWORK_ID));
				model.setGeneriqueType(res.getString(DBConstantes.T_NETWORK_GENERIQUE_TYPE));
				model.setType(res.getString(DBConstantes.T_NETWORK_TYPE));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllNetwork, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	
	public static List<LineModel> getAllLines() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_LINE);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<LineModel> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<LineModel>();
				}
				
				LineModel model = new LineModel();
				model.setIdLine(res.getInt(DBConstantes.T_LINE_ID));
				model.setCode(res.getString(DBConstantes.T_LINE_CODE));
				model.setName(res.getString(DBConstantes.T_LINE_NAME));
				model.setIdNetwork(res.getInt(DBConstantes.T_LINE_NETWORK_ID));
				model.setOpeningTime(res.getString(DBConstantes.T_LINE_OPENING_TIME));
				model.setClosingTime(res.getString(DBConstantes.T_LINE_CLOSING_TIME));
				model.setColor(res.getString(DBConstantes.T_LINE_COLOR));
				model.setTransport_type(res.getString(DBConstantes.T_LINE_TRANSPORT_TYPE));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllLines, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	
	public static List<RouteModel> getAllRoutes() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_ROUTE);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<RouteModel> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<RouteModel>();
				}
				
				RouteModel model = new RouteModel();
				model.setIdRoute(res.getInt(DBConstantes.T_ROUTE_ID));
				model.setDestination(res.getString(DBConstantes.T_ROUTE_DESTINATION));
				model.setIdLine(res.getInt(DBConstantes.T_ROUTE_LINE_ID));
				model.setName(res.getString(DBConstantes.T_ROUTE_NAME));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllRoutes, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	
	public synchronized static int getStopAreaFromTSTOPPOINT(String idStopPoint) throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select id_stop_area from ");
		query.append(DBConstantes.T_STOP_POINT);
		query.append(" where id_stop_point_navitia = '" + idStopPoint + "'");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int idStopArea = -1;
		

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				idStopArea = res.getInt(DBConstantes.T_STOP_AREA_ID);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getStopArea, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return idStopArea;
	}
	
	public static List<StopAreaModel> getAllStopAreas() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA);
		query.append(" where calculated = 0");
		query.append(" order by 1");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<StopAreaModel> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<StopAreaModel>();
				}
				
				StopAreaModel model = new StopAreaModel();
				model.setIdNavitia(res.getString(DBConstantes.T_STOP_AREA_ID_NAVITIA));
				model.setIdStopArea(res.getInt(DBConstantes.T_STOP_AREA_ID));
				model.setName(res.getString(DBConstantes.T_STOP_AREA_NAME));
				model.setLongitude(res.getString(DBConstantes.T_STOP_AREA_LONGITUDE));
				model.setLatitude(res.getString(DBConstantes.T_STOP_AREA_LATITUDE));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllStopAreas, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	public static List<StopAreaLine> getAllStopAreaLines() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA_LINE);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<StopAreaLine> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<StopAreaLine>();
				}
				
				StopAreaLine model = new StopAreaLine();
				model.setIdLine(res.getInt(DBConstantes.T_STOP_AREA_LINE_ID_LINE));
				model.setIdStopArea(res.getInt(DBConstantes.T_STOP_AREA_LINE_ID_STOP_AREA));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllStopAreaLines, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	public static List<StopAreaRoute> getAllStopAreaRoutes() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_AREA_ROUTE);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<StopAreaRoute> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<StopAreaRoute>();
				}
				
				StopAreaRoute model = new StopAreaRoute();
				model.setIdRoute(res.getInt(DBConstantes.T_STOP_AREA_ROUTE_ID_ROUTE));
				model.setIdStopArea(res.getInt(DBConstantes.T_STOP_AREA_ROUTE_ID_STOP_AREA));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllStopAreaRoutes, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	public static List<StopPointModel> getAllStopPoints() throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("select * from ");
		query.append(DBConstantes.T_STOP_POINT);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<StopPointModel> listModel = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			res = pstmt.executeQuery();
			
			while (res.next()) {
				if(listModel == null) {
					listModel = new ArrayList<StopPointModel>();
				}
				
				StopPointModel model = new StopPointModel();
				model.setIdStopPoint(res.getInt(DBConstantes.T_STOP_POINT_ID));
				model.setIdRoute(res.getInt(DBConstantes.T_STOP_POINT_ID_ROUTE));
				model.setIdStopArea(res.getInt(DBConstantes.T_STOP_POINT_ID_STOP_AREA));
				model.setLatitude(res.getString(DBConstantes.T_STOP_POINT_LATITUDE));
				model.setLongitude(res.getString(DBConstantes.T_STOP_POINT_LONGITUDE));
				model.setName(res.getString(DBConstantes.T_STOP_POINT_NAME));
				model.setLastUpdate(res.getDate(DBConstantes.T_LAST_UPDATE));
				listModel.add(model);
			}
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur getAllStopPoints, " + e.getMessage());
		} finally {
			close(null, pstmt, res);
		}
		return listModel;
	}
	
	public synchronized static void flagToCalculated(int idStopArea) throws HerculeTechnicalException {
		StringBuilder query = new StringBuilder("update ");
		query.append(DBConstantes.T_STOP_AREA);
		query.append(" set calculated = 1");
		query.append(" where id_stop_area = ?");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, idStopArea);
			pstmt.execute();
		} catch (SQLException e) {
			throw new HerculeTechnicalException("Erreur flagToCalculated, " + e.getMessage());
		} finally {
			close(null, pstmt, null);
		}
	}
	

	/**
	 * Fermeture de la connexion
	 * @param conn
	 * @param stmt
	 * @param res
	 */
	private static void close(Statement stmt, PreparedStatement pstmt, ResultSet res) {

		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("erreur fermeture stmt");
			}
		}

		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("erreur fermeture pstmt");
			}
		}

		if(res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				System.out.println("erreur fermeture res");
			}
		}
	}

}

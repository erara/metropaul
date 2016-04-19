package com.hercule.commun.constantes;

public class DBConstantes {

	public static final String PROP_DB_LOGIN = "dbLogin";
	public static final String PROP_DB_PASSWD = "dbPasswd";
	public static final String PROP_DB_URL = "dbUrl";
	
	/** Tables */
	public static String T_AUTHORIZED_NETWORKS = "T_AUTH_NETWORKS";
	public static String T_STATION = "T_STATION";
	public static String T_NETWORK = "T_NETWORK";
	public static String T_LINE = "T_LINE";
	public static String T_ROUTE = "T_ROUTE";
	public static String T_STOP_AREA = "T_STOP_AREA";
	public static String T_STOP_AREA_LINE = "T_STOP_AREA_LINE";
	public static String T_STOP_AREA_ROUTE = "T_STOP_AREA_ROUTE";
	public static String T_STOP_POINT = "T_STOP_POINT";
	
	/** colonne identiques à toutes les tables */
	public static String T_ID = "id";
	public static String T_LAST_UPDATE = "last_update";
	
	/** T_AUTH_NETWORKS colonnes */
	public static String T_AUTH_NETWORKS_NAME = "name";
	public static String T_AUTH_NETWORKS_ID = "id";
	
	/** T_STATION colonnes */
	public static String T_STATION_NAME = "name";
	public static String T_STATION_TYPE = "type";
	
	/** T_NETWORK colonnes */
	public static String T_NETWORK_ID = "id_network";
	public static String T_NETWORK_TYPE = "type";
	public static String T_NETWORK_GENERIQUE_TYPE = "generique_type";
	
	/** T_LINE colonnes */
	public static String T_LINE_ID = "id_line";
	public static String T_LINE_CODE = "code";
	public static String T_LINE_NAME = "name";
	public static String T_LINE_NETWORK_ID = "id_network";
	public static String T_LINE_OPENING_TIME = "opening_time";
	public static String T_LINE_CLOSING_TIME = "closing_time";
	public static String T_LINE_COLOR = "color";
	public static String T_LINE_TRANSPORT_TYPE = "transport_type";
	
	/** T_ROUTE colonnes */
	public static String T_ROUTE_ID = "id_route";
	public static String T_ROUTE_LINE_ID = "id_line";
	public static String T_ROUTE_NAME = "name";
	public static String T_ROUTE_DESTINATION = "destination";
	public static String T_ROUTE_OPENING_TIME = "opening_time";
	public static String T_ROUTE_CLOSING_TIME = "closing_time";
	
	/** T_STOP_AREA colonnes */
	public static String T_STOP_AREA_ID = "id_stop_area";
	public static String T_STOP_AREA_NAME = "name";
	public static String T_STOP_AREA_ID_NAVITIA = "id_navitia";
	public static String T_STOP_AREA_LONGITUDE = "longitude";
	public static String T_STOP_AREA_LATITUDE = "latitude";
	
	/** T_STOP_AREA_LINE colonnes */
	public static String T_STOP_AREA_LINE_ID_STOP_AREA = "id_stop_area";
	public static String T_STOP_AREA_LINE_ID_LINE = "id_line";
	
	/** T_STOP_AREA_ROUTE colonnes */
	public static String T_STOP_AREA_ROUTE_ID_STOP_AREA = "id_stop_area";
	public static String T_STOP_AREA_ROUTE_ID_ROUTE = "id_route";
	
	/** T_STOP_POINT colonnes */
	public static String T_STOP_POINT_ID = "id_stop_point";
	public static String T_STOP_POINT_NAVITIA_ID = "id_stop_point_navitia";
	public static String T_STOP_POINT_NAME = "name";
	public static String T_STOP_POINT_ID_STOP_AREA = "id_stop_area";
	public static String T_STOP_POINT_ID_ROUTE = "id_route";
	public static String T_STOP_POINT_LONGITUDE = "longitude";
	public static String T_STOP_POINT_LATITUDE = "latitude";
	
	
}

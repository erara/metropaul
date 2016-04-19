package com.hercule.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.hercule.commun.beans.StopAreaModel;
import com.hercule.commun.constantes.WSConstantes;
import com.hercule.commun.navitia.Arrivals;
import com.hercule.commun.navitia.Departures;
import com.hercule.commun.navitia.Journeys;
import com.hercule.commun.navitia.Lines;
import com.hercule.commun.navitia.Networks;
import com.hercule.commun.navitia.Routes;
import com.hercule.commun.navitia.StopAreas;
import com.hercule.commun.navitia.StopPoints;
import com.hercule.utils.exception.HerculeTechnicalException;
import com.hercule.utils.file.ReadPropertiesFile;

@SuppressWarnings("deprecation")
public class RestManager {
	
	/** Log4j logger for this class */
	private static Logger logger = Logger.getLogger(RestManager.class);
	
	private static Properties documentProperties = null;
	private static String urlWSNavitia;
	private static String userNavitia;
	private static String passwordNavitia;
	private static String ADD_PARAM_URL = "&";
	private static String SLASH_PARAM_URL = "/";
	
	/**
	 * Initialisation des properties spécifiques aux appels WS
	 * @param fileName
	 * @throws HerculeTechnicalException
	 * @throws IOException
	 */
	public static void initRestManager(String fileName) throws HerculeTechnicalException, IOException {		
		documentProperties = ReadPropertiesFile.loadPropsFromFile(fileName);
		urlWSNavitia = documentProperties.getProperty(WSConstantes.PROP_WS_NAVITIA_URL);
		userNavitia = documentProperties.getProperty(WSConstantes.PROP_WS_NAVITIA_USERNAME);
		passwordNavitia = documentProperties.getProperty(WSConstantes.PROP_WS_NAVITIA_PWD);
	}
	
	/**
	 * Création d'un template REST pour appel WS
	 * @param username
	 * @param password
	 * @return
	 */
	public synchronized static RestTemplate createRestTemplate() {

        UsernamePasswordCredentials cred = new UsernamePasswordCredentials(userNavitia, passwordNavitia);
        BasicCredentialsProvider cp = new BasicCredentialsProvider();
        cp.setCredentials(AuthScope.ANY, cred);
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCredentialsProvider(cp);
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);

        RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        // set the media types properly
        return restTemplate;
    }
	
	
	public static Networks callNavitiaNetworks(RestTemplate restTemplate) throws MalformedURLException, URISyntaxException {
		String url_networks = urlWSNavitia + "/networks";
		
		logger.info("Appel WS Navitia Network : " + url_networks);
		
		URL url_navitia_networks = new URL(url_networks);
		URI url_uri_navitia_networks = url_navitia_networks.toURI();
		return restTemplate.getForObject(url_uri_navitia_networks, Networks.class);
	}
	
	
	public static Networks callNavitiaAuthNetwork(RestTemplate restTemplate, String idNetwork) throws MalformedURLException, URISyntaxException {
		String url_networks = urlWSNavitia + "/networks/" + idNetwork;
		
		logger.info("Appel WS Navitia Network : " + url_networks);
		
		URL url_navitia_networks = new URL(url_networks);
		URI url_uri_navitia_networks = url_navitia_networks.toURI();
		return restTemplate.getForObject(url_uri_navitia_networks, Networks.class);
	}
	
	
	
	public static Lines callNavitiaLines(RestTemplate restTemplate, String idNetwork) throws MalformedURLException, URISyntaxException {
		String url_lines = urlWSNavitia + "/networks/" + idNetwork + "/lines";
		
		logger.info("Appel WS Navitia Lines : " + url_lines);
		
		URL url_navitia_lines = new URL(url_lines);
		URI url_uri_navitia_lines = url_navitia_lines.toURI();
		return restTemplate.getForObject(url_uri_navitia_lines, Lines.class);
	}
	
	public static Lines callNavitiaLinesPagination(RestTemplate restTemplate, String idNetwork, int itLigne) throws MalformedURLException, URISyntaxException {
		String url_lines = urlWSNavitia + "/networks/" + idNetwork + "/lines";
		String url_lines_pagination = setJSONPage(url_lines, itLigne);
		logger.info("Appel WS Navitia Lines paginées : " + url_lines_pagination);
		URL url_navitia_lines = new URL(url_lines_pagination);
		URI url_uri_navitia_lines = url_navitia_lines.toURI();
		return restTemplate.getForObject(url_uri_navitia_lines, Lines.class);
	}
	
	public static Routes callNavitiaRoutes(RestTemplate restTemplate, String idNetwork, String idLine) throws MalformedURLException, URISyntaxException {
		String url_routes = urlWSNavitia + "/networks/" + idNetwork + "/lines" + "/" + idLine + "/routes";
		logger.info("Appel WS Navitia Routes : " + url_routes);
		URL url_navitia_routes = new URL(url_routes);
		URI url_uri_navitia_routes = url_navitia_routes.toURI();
		return restTemplate.getForObject(url_uri_navitia_routes, Routes.class);
	}
	
	public static Routes callNavitiaRoutesPagination(RestTemplate restTemplate, String idNetwork, String idLine, int itRoutes) throws MalformedURLException, URISyntaxException {
		String url_routes = urlWSNavitia + "/networks/" + idNetwork + "/lines" + "/" + idLine + "/routes";
		String url_routes_pagination=setJSONPage(url_routes, itRoutes);
		logger.info("Appel WS Navitia Routes paginées : " + url_routes_pagination.toString());
		URL url_navitia_routes = new URL(url_routes_pagination);
		URI url_uri_navitia_routes = url_navitia_routes.toURI();
		return restTemplate.getForObject(url_uri_navitia_routes, Routes.class);
	}
	
	public static StopAreas callNavitiaStopAreas(RestTemplate restTemplate, String idNetwork, String idLine, String idRoute) throws MalformedURLException, URISyntaxException {
		String url_stop_areas = urlWSNavitia + "/networks/" + idNetwork + "/lines" + "/" + idLine + "/routes" + "/"+ idRoute +"/stop_areas";
		URL url_navitia_stop_areas = new URL(url_stop_areas);
		logger.info("Appel WS Navitia StopAreas : " + url_navitia_stop_areas.toString());
		URI url_uri_navitia_stop_areas = url_navitia_stop_areas.toURI();
		return restTemplate.getForObject(url_uri_navitia_stop_areas, StopAreas.class);
	}
	
	public static StopAreas callNavitiaStopAreasPagination(RestTemplate restTemplate, String idNetwork, String idLine, String idRoute, int itStopAreas) throws MalformedURLException, URISyntaxException {
		String url_stop_areas = urlWSNavitia + "/networks/" + idNetwork + "/lines" + "/" + idLine + "/routes" + "/"+ idRoute +"/stop_areas";
		String url_stop_areas_pagination=setJSONPage(url_stop_areas, itStopAreas);
		logger.info("Appel WS Navitia StopAreasPagination : " + url_stop_areas_pagination);
		URL url_navitia_stop_areas = new URL(url_stop_areas_pagination);
		URI url_uri_navitia_stop_areas = url_navitia_stop_areas.toURI();
		return restTemplate.getForObject(url_uri_navitia_stop_areas, StopAreas.class);
	}
	
	public static StopPoints callNavitiaStopPoints(RestTemplate restTemplate, String idStopArea) throws MalformedURLException, URISyntaxException {
		String url_stop_points = urlWSNavitia + "/stop_areas/" + idStopArea + "/stop_points";

		logger.info("Appel WS Navitia StopPoints : " + url_stop_points);
		
		URL url_navitia_stop_points = new URL(url_stop_points);
		URI url_uri_navitia_stop_points = url_navitia_stop_points.toURI();
		return restTemplate.getForObject(url_uri_navitia_stop_points, StopPoints.class);
	}
	
	public static String setJSONPage(String url, int page){
		url= url + "?start_page=" + page;
		return url;
	}
	
	
	public synchronized static Journeys callJourney(RestTemplate restTemplate, StopAreaModel from, StopAreaModel to, String countJourney, String dateTime) throws MalformedURLException, URISyntaxException {
		StringBuilder url_Journey = new StringBuilder();
		url_Journey.append(urlWSNavitia + "/journeys?");
		url_Journey.append("from=" + from.getLongitude() + "%3B" + from.getLatitude());
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("to=" + to.getLongitude() + "%3B" + to.getLatitude());
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("datetime=" + dateTime); // A MODIFIER A CHAQUE EXECUTION
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("datetime_represents=arrival");
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("forbidden_uris%5B%5D=network%3ARTP");
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("forbidden_uris%5B%5D=network%3AOIF%3A56");
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("last_section_mode=walking");
		url_Journey.append(ADD_PARAM_URL);
		url_Journey.append("type=rapid&count=" + countJourney);
		
		logger.info("Appel WS Navitia Journey : " + url_Journey);
		
		URL url_navitia_journey = new URL(url_Journey.toString());
		URI url_uri_navitia_journey = url_navitia_journey.toURI();
		return restTemplate.getForObject(url_uri_navitia_journey, Journeys.class);
	}
		
	
	public synchronized static Departures callPremierDepart(RestTemplate restTemplate, String route, String stopArea, String opening_hour) throws MalformedURLException, URISyntaxException {
		StringBuilder url_PremierDepart = new StringBuilder();
		url_PremierDepart.append(urlWSNavitia + "/");
		url_PremierDepart.append("routes/" + route);
		url_PremierDepart.append(SLASH_PARAM_URL);
		url_PremierDepart.append("stop_areas/" + stopArea);
		url_PremierDepart.append(SLASH_PARAM_URL);
		url_PremierDepart.append("departures?"); 
		url_PremierDepart.append("from_datetime="+ opening_hour);
		url_PremierDepart.append(ADD_PARAM_URL);
		url_PremierDepart.append("count=1");
		
		logger.info("Appel WS Navitia Premier Depart : " + url_PremierDepart);
		
		URL url_navitia_premierDepart = new URL(url_PremierDepart.toString());
		URI url_uri_navitia_premierDepart = url_navitia_premierDepart.toURI();
		return restTemplate.getForObject(url_uri_navitia_premierDepart, Departures.class);
	}
	
	public synchronized static Arrivals callDernierDepart(RestTemplate restTemplate, String route, String stopArea, String closing_hour) throws MalformedURLException, URISyntaxException {
		StringBuilder url_Dernierdepart = new StringBuilder();
		url_Dernierdepart.append(urlWSNavitia + "/");
		url_Dernierdepart.append("routes/" + route);
		url_Dernierdepart.append(SLASH_PARAM_URL);
		url_Dernierdepart.append("stop_areas/" + stopArea);
		url_Dernierdepart.append(SLASH_PARAM_URL);
		url_Dernierdepart.append("arrivals?"); 
		url_Dernierdepart.append("until_datetime="+ closing_hour);
		url_Dernierdepart.append(ADD_PARAM_URL);
		url_Dernierdepart.append("count=1");
		
		logger.info("Appel WS Navitia Dernier Depart : " + url_Dernierdepart);
		
		URL url_navitia_dernierDepart = new URL(url_Dernierdepart.toString());
		URI url_uri_navitia_dernierDepart = url_navitia_dernierDepart.toURI();
		return restTemplate.getForObject(url_uri_navitia_dernierDepart, Arrivals.class);
	}
	
		
//		http://api.navitia.io/v1/journeys?from=2.256945%3B48.838117&to=2.389422%3B48.852587
//			&datetime=20160319T031128
//			&datetime_represents=arrival
//			&forbidden_uris%5B%5D=network%3ARTP
//			&forbidden_uris%5B%5D=network%3AOIF%3A56
//			&last_section_mode=walking
//			&type=rapid&count=1	}
	
}

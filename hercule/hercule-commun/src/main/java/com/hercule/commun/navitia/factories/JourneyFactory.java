package com.hercule.commun.navitia.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.hercule.commun.navitia.JourneysRequest;
import com.hercule.commun.navitia.LinkSection;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
public class JourneyFactory {

    public JourneyFactory() {
    }

    public HashMap<String,String> construireParams(JourneysRequest journeysRequest){
        //Itinialisation de tous les paramètres à insérer à la requête
        HashMap<String,Object> allParams = journeysRequest.toMap();
        Set<String> keys = allParams.keySet();
        Iterator<String> it = keys.iterator();
        //Est la clé itérée dont nous vérifions si elle a une valeur
        String itKey;
        //La Map des paramètres non nuls à ajouter à la requête
        HashMap<String,String> paramsToProceed = new HashMap<String,String>();
        while (it.hasNext()){
            itKey=it.next().toString();
            if (allParams.get(itKey)!=null){
                paramsToProceed.put(itKey, "" + allParams.get(itKey));
            }
        }
        return paramsToProceed;
    }
    
    public String getRouteId(ArrayList<LinkSection> links){
    	for (int i=0; i<links.size(); i++){
    		if (links.get(i).getType().equals("route"))
    			return links.get(i).getId();
    	}
    	return "";
    }
        

    //Ajouter méthode qui récupère tous les attributs à l'écran et construit une JourneyRequest





}

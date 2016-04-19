package com.hercule.commun.navitia.factories;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Antoine Lafuente on 12/01/2015.
 */
public class UrlFactory {

    public String addUrlParams(HashMap<String,String> params, String url){
        Set<String> keySet = params.keySet();
        Iterator<String> itParams = keySet.iterator();

        if (!params.isEmpty()) {
            String itKey = itParams.next();
            // On insère le premier manuellement pour le ? au début de l'url
            url = url + "?" + itKey + "=" + params.get(itKey);
            while (itParams.hasNext()) {
                itKey = itParams.next();
                url = url + "&" + itKey + "=" + params.get(itKey);
            }
        }
        return url;
    }
}

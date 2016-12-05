package org.betsev.acp.business.contact.entity;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Created by sevburmaka on 12/4/16.
 */
public class TypeMapping {
    private static HashMap<String,String> typeMapping = new HashMap<>();

    @PostConstruct
    public void init(){
        typeMapping.put("house","legislatorLowerBody");
        typeMapping.put("senate","legislatorUpperBody");
        typeMapping.put("all","legislatorUpperBody,legislatorLowerBody");
    }

    public static String get(String type){
        if (type == null) return typeMapping.get("all");
        if (type.contains(",")){
            String[] types = type.split(",");
            String typesString = "";
            for (int x = 0; x < types.length ; x++){
                if (x > 0)
                    typesString+=",";
                typesString+=typeMapping.get(types[x]);
            }
            return typesString;
        }
        else
            return typeMapping.get(type);
    }
}

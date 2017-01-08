package org.betsev.acp.business.contact.entity.gcivic;

import org.betsev.acp.business.contact.entity.ContactType;

import java.util.HashMap;

/**
 * Created by sevburmaka on 12/4/16.
 */
public class TypeMapping {
    private static HashMap<ContactType,String> typeMapping = new HashMap<>();

    public static void init(){
        typeMapping.put(ContactType.HOUSE,"legislatorLowerBody");
        typeMapping.put(ContactType.SENATE,"legislatorUpperBody");
        typeMapping.put(ContactType.STATE,"administrativeArea1");

    }

    public static String get(ContactType type){
        if (typeMapping.isEmpty())
            init();

        return typeMapping.get(type);
    }

    public static String get(String type){
        if (typeMapping.isEmpty())
            init();

        return typeMapping.get(ContactType.getByValue(type));
    }
}

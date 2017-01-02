package org.betsev.acp.business.contact.entity;

import lombok.Getter;

/**
 * Created by sevburmaka on 1/1/17.
 */
@Getter
public enum ContactType {
    SENATE("senate"),
    HOUSE("house"),
    STATE("state"),
    COMMITTEE("committee");

    private String displayValue;

    ContactType(String displayValue) {
        this.displayValue=displayValue;
    }

    public static ContactType getByValue(String displayValue){
        for (ContactType ct : values()){
            if (ct.getDisplayValue().equals(displayValue))
                return ct;
        }
        return null;
    }
}

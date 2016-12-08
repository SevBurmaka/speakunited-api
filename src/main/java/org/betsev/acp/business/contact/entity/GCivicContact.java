package org.betsev.acp.business.contact.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by sevburmaka on 12/4/16.
 */
@Getter
@Setter
public class GCivicContact {
    String name;
    List<Address> address;
    String party;
    List<String> phones;
    List<String> urls;
    String photoUrl;
    List<Map<String,String>> channels;

}

package org.betsev.acp.business.issue.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Getter
@Setter
public class Issue {
    Integer priority;
    String title;
    String description;
    List<Script> scripts;
    List<String> types;
    String specificContact;
    String notes;
}

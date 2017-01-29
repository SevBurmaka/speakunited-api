package org.betsev.acp.business.issue.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Script {
    String type;
    String text;
    String title;
    List<String> hashTags;
}

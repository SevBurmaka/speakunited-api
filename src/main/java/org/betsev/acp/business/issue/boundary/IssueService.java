package org.betsev.acp.business.issue.boundary;

import org.betsev.acp.business.issue.entity.Issue;

import java.io.IOException;
import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
public interface IssueService {
    List<Issue> getTopIssues() throws IOException;
    List<Issue> replaceIssueText(List<Issue> issues,String fieldName, String value);

}

package org.betsev.acp.business.issue.boundary;

import org.betsev.acp.business.issue.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@RestController
@RequestMapping("/issues")
public class IssueRES {

    @Autowired
    IssueService issueService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Issue> get(@RequestParam(required = false) String name, @RequestParam(required = false) String repName) throws Exception {
        List<Issue> issues = issueService.getTopIssues();
        if (!StringUtils.isEmpty(name))
            issues = issueService.replaceIssueText(issues,"name",name);
        if (!StringUtils.isEmpty(repName))
            issues = issueService.replaceIssueText(issues,"repName",repName);

        return issues;
    }
}

package org.betsev.acp.business.issue.boundary;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.betsev.acp.business.issue.entity.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Service
@ConfigurationProperties
@Getter
@Setter
public class IssueServiceImpl implements IssueService{
    private static final Logger LOG = LoggerFactory.getLogger(IssueServiceImpl.class);

    String issuesLocation;

    @Override
    public List<Issue> getTopIssues() throws IOException {
       Issue[] issues;

        Gson gson = new Gson();
        try {
            if (issuesLocation == null ){
                InputStream issuesStream = new ClassPathResource("issues/issues.json").getInputStream();
                BufferedReader issueReader = new BufferedReader(new InputStreamReader(issuesStream));
                issues = gson.fromJson(issueReader, Issue[].class);
            }
            else {
                InputStream issuesStream = new FileInputStream(issuesLocation);
                BufferedReader issueReader = new BufferedReader(new InputStreamReader(issuesStream));
                issues = gson.fromJson(issueReader, Issue[].class);
            }
        }catch(IOException e){
            LOG.error("Error while trying to read issues file: {}",e);
            throw(e);
        }

        return CollectionUtils.arrayToList(issues);
    }

    @Override
    public List<Issue> replaceIssueText(List<Issue> issues,String fieldName, String value) {
        issues.forEach(issue ->{
            issue.getScripts().forEach(script ->{
                script.setText(script.getText().replace("{"+fieldName+"}",value));
            });
        });
        return issues;
    }
}

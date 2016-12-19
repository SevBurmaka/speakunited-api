package org.betsev.acp.support;

import info.debatty.java.stringsimilarity.JaroWinkler;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sevburmaka on 12/17/16.
 */
@Service
public class JaroWinklerNameMatcher implements NameMatcher {

    @Override
    public int getBestMatchIndex(String input, List<String> candidates, double matchThreshold) {
        JaroWinkler jw = new JaroWinkler();
        int bestMatchIndex = 0;
        double bestDistance = 0;

        for (int x = 0; x < candidates.size() ; x++){
            double curMatch = jw.similarity(input.toUpperCase(),candidates.get(x).toUpperCase());
            if (curMatch > bestDistance){
                bestMatchIndex = x;
                bestDistance = curMatch;
            }
        }
        if (bestDistance < matchThreshold)
            return -1;
        return bestMatchIndex;
    }
}


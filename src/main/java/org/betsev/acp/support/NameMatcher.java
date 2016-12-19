package org.betsev.acp.support;

import java.util.List;

/**
 * Created by sevburmaka on 12/17/16.
 */
public interface NameMatcher {
    int getBestMatchIndex(String input, List<String> candidates, double matchThreshold);
}

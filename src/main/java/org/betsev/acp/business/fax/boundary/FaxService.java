package org.betsev.acp.business.fax.boundary;

import org.betsev.acp.business.fax.entity.FaxRequest;

/**
 * Created by sevburmaka on 1/14/17.
 */
public interface FaxService {
    String sendFax(FaxRequest request);
}

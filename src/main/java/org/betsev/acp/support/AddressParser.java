package org.betsev.acp.support;

import org.betsev.acp.business.contact.entity.Address;

/**
 * Created by sevburmaka on 1/29/17.
 */
public interface AddressParser {
    Address parseAddress(String address);
}

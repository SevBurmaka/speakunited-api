package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.control.uscl.USCLContactRepository;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.betsev.acp.business.contact.entity.IdInfo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 1/14/17.
 */
@Service
@Qualifier("uscl")
public class USCLContactService implements ContactService{
    @Autowired
    USCLContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.getAll().stream().map(it ->
                beanMapper.map(it,Contact.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Contact> findByAddressAndType(String address, ContactType type) {
       throw new UnsupportedOperationException("this method not implemented for this service");
    }

    @Override
    public List<Contact> findByDistrictAndType(String district, ContactType type) {
        throw new UnsupportedOperationException("this method not implemented for this service");
    }

    @Override
    public Contact getByBioguideId(String bioguideId) {
        Contact toFind = new Contact();
        IdInfo idInfo = new IdInfo();
        idInfo.setBioguide(bioguideId);
        toFind.setIdInfo(idInfo);
        return beanMapper.map(contactRepository.getCorrespondingContact(toFind),Contact.class);
    }
}

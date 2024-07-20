package com.gamestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.Contact;
import com.gamestore.repository.ContactServiceRepository;
import com.gamestore.services.ContactService;


@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactServiceRepository contactServiceRepository;

	 @Override
	    public Contact addNewContactInfo(String fullName, String email, String msg) {
	        Contact contact = new Contact();
	        contact.setFullName(fullName);
	        contact.setEmail(email);
	        contact.setUserQuerys(msg);
	        return contact;
	    }

	    @Override
	    public Contact save(Contact contact) {
	        return contactServiceRepository.save(contact);
	    }
	
}

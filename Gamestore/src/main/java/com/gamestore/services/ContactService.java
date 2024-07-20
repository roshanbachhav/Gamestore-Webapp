package com.gamestore.services;

import com.gamestore.domain.Contact;

public interface ContactService {
	Contact addNewContactInfo(String fullName , String email , String msg);
	Contact save(Contact contact);
}

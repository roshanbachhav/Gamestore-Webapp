package com.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamestore.domain.Contact;

public interface ContactServiceRepository extends JpaRepository<Contact, Long> {

}

package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContactRepository extends JpaRepository<Contact, Long> {


}

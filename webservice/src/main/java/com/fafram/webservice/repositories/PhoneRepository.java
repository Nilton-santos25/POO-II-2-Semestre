package com.fafram.webservice.repositories;

import com.fafram.webservice.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}

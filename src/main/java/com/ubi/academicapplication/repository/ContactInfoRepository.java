package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.ContactInfo;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

}

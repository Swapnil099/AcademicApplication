package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubi.academicapplication.entity.ContactInfo;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

}

package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.TransferCertificate;


@Repository
public interface TransferCertificateRepository extends JpaRepository<TransferCertificate,Integer>{
	
	
	
}

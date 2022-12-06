package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

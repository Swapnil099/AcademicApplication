package com.ubi.academicapplication.repository;

import com.ubi.academicapplication.dto.student.StudentPromoteDemoteDto;
import com.ubi.academicapplication.entity.StudentPromoteDemote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPromoteDemoteRepository extends JpaRepository<StudentPromoteDemote,Long> {

//    StudentPromoteDemote save(StudentPromoteDemoteDto studentPromoteDemoteCreationDto);
}

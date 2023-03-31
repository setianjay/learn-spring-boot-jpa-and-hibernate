package com.setianjay.springboot.learnspringbootjpaandhibernate.repository;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
}

package com.setianjay.springboot.learnspringbootjpaandhibernate.repository;

import com.setianjay.springboot.learnspringbootjpaandhibernate.entity.Rector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RectorRepository extends JpaRepository<Rector, Long> {
}

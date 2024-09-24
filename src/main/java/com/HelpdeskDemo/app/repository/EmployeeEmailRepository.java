package com.HelpdeskDemo.app.repository;

import com.HelpdeskDemo.app.entity.EmployeeEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeEmailRepository extends JpaRepository<EmployeeEmail, Long > {
    Optional<EmployeeEmail> findByEmail(String email);
}

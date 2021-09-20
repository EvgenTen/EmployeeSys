package com.evgen.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evgen.springproj.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

package com.anbu.reactiveytdemo.repository;

import com.anbu.reactiveytdemo.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {
}

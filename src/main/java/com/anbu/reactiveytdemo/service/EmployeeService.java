package com.anbu.reactiveytdemo.service;

import com.anbu.reactiveytdemo.entity.Employee;
import com.anbu.reactiveytdemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Flux<Employee> getAllEmployee(){
        return this.employeeRepository.findAll();
    }

    public Mono<Employee> saveEmployee(Employee employee){
        return this.employeeRepository.save(employee);
    }
    public Mono<Employee> findById(Integer id){
        return this.employeeRepository.findById(id);
    }

    public Mono<Employee> updateEmployee(Employee employee){
        return this.employeeRepository.findById(employee.getEmpId())
                .flatMap(emp->{
                    emp.setName(employee.getName());
                    emp.setDepartment(employee.getDepartment());
                    return this.employeeRepository.save(emp);
                });
              //  .switchIfEmpty(Mono<Void>);
    }

    public Mono<Void> deleteEmployee(Integer id){
        return this.employeeRepository.deleteById(id);
    }
}

package com.anbu.reactiveytdemo.controller;

import com.anbu.reactiveytdemo.entity.Employee;
import com.anbu.reactiveytdemo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Flux<Employee> getAll(){
        return this.employeeService.getAllEmployee();
    }

    @PostMapping
    public Mono<ResponseEntity<Employee>> saveEmployee(@RequestBody Mono<Employee> employeeMono){
        return employeeMono.flatMap(this.employeeService::saveEmployee)
                .map(employee -> ResponseEntity.ok(employee));
    }
    @PutMapping
    public Mono<Employee> updateEmployee(@RequestBody Mono<Employee> employeeMono){
        return employeeMono.flatMap(this.employeeService::updateEmployee);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteEmployee(@PathVariable("id") Integer empId){
        return this.employeeService.findById(empId)
                .flatMap(employee -> this.employeeService.deleteEmployee(empId)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

package com.anbu.reactiveytdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Employee {
    @Id
    private Integer empId;
    private String name;
    private String department;
}

package com.example.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Model.Employee;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

}

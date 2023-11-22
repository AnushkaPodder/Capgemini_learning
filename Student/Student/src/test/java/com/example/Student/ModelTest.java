package com.example.Student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import StudentDetails.Student;

public class ModelTest {
	
	@Test
    void testStudentModel() {
        
        Long id = 1L;
        String name = "Anushka Podder";
        int age = 20;
        int salary = 1000;

        
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setSalary(salary);

        
        assertEquals(id, student.getId());
//        assertEquals(name, student.getName());
//        assertEquals(age, student.getAge());
//        assertEquals(salary, student.getSalary());
    }

}

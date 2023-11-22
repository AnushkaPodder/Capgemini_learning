package com.example.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import StudentDetails.Student;
import StudentRepo.StudentRepository;
import StudentService.StudentServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceImplTest {
	
	@Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        
        Student student1 = new Student(1L, "Anushka Podder", 20, 1000);
        Student student2 = new Student(2L, "Amie Pearson", 22, 2000);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        
        Iterable<Student> students = studentService.getAllStudents();

        
        assertNotNull(students);
        assertEquals(2, ((List<Student>) students).size());
        verify(studentRepository, times(1)).findAll();
        verifyNoMoreInteractions(studentRepository);
    }
    
    @Test
    void getStudentById_shouldReturnNullWhenNotExists() {
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        
        Student result = studentService.getStudentById(studentId);

        
        assertNull(result);

        verify(studentRepository, times(1)).findById(studentId);
        verifyNoMoreInteractions(studentRepository);
    }

    
    @Test
    void createStudent_shouldCallSaveMethod() {
        
        Student newStudent = new Student(null, "New Student", 25, 3000);

        
        studentService.createStudent(newStudent);

       
        verify(studentRepository, times(1)).save(newStudent);
        verifyNoMoreInteractions(studentRepository);
    }
    
    @Test
    void updateStudent_shouldCallSaveMethod() {
        
        Long studentId = 1L;
        Student updatedStudent = new Student(studentId, "Updated Student", 30, 3000);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(new Student(studentId, "Existing Student", 25, 3000)));

        studentService.updateStudent(studentId, updatedStudent);

        verify(studentRepository, times(1)).save(updatedStudent);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void deleteStudent_shouldCallDeleteMethod() {
        Long studentId = 1L;

        when(studentRepository.existsById(studentId)).thenReturn(true);

        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
        verifyNoMoreInteractions(studentRepository);
    }


}

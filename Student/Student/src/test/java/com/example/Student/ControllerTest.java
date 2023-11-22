package com.example.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import StudentController.Controller;
import StudentDetails.Student;
import StudentService.StudentService;

public class ControllerTest {
	
	@Mock
    private StudentService studentService;

    @InjectMocks
    private Controller studentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void getAllStudents_shouldReturnListOfStudents() throws Exception {
        
        Student student1 = new Student(1L, "Anushka Podder", 20, 1000);
        Student student2 = new Student(2L, "Amie Pearson", 22, 2000);
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        
        ResultActions result = mockMvc.perform(get("/api/students"));

        
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Anushka Podder"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Amie Pearson"))
                .andExpect(jsonPath("$[1].age").value(22))
        		.andExpect(jsonPath("$[1].salary").value(2000));
        		

        verify(studentService, times(1)).getAllStudents();
        verifyNoMoreInteractions(studentService);
    }
    
    @Test
    void getStudent_shouldReturnStudentById() throws Exception {
        Long studentId = 1L;
        Student student = new Student(studentId, "Anushka Podder", 20, 1000);
        when(studentService.getStudentById(studentId)).thenReturn(student);

        ResultActions result = mockMvc.perform(get("/api/students/{id}", studentId));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.name").value("Anushka Podder"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.salary").value(1000));

        verify(studentService, times(1)).getStudentById(studentId);
        verifyNoMoreInteractions(studentService);
    }
    
    @Test
    void createStudent_shouldReturnSuccessMessage() throws Exception {
        Student newStudent = new Student(null, "New Student", 25, 3000);
        String requestBody = "{\"name\":\"New Student\",\"age\":25}";

        ResultActions result = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        result.andExpect(status().isOk())
                .andExpect(content().string("Student created successfully"));

        verify(studentService, times(1)).createStudent(any(Student.class));
        verifyNoMoreInteractions(studentService);
    }
    
    @Test
    void updateStudent_shouldReturnSuccessMessage() throws Exception {
        Long studentId = 1L;
        Student updatedStudent = new Student(studentId, "Updated Student", 30, 3000);
        String requestBody = "{\"name\":\"Updated Student\",\"age\":30}";

        when(studentService.getStudentById(studentId)).thenReturn(updatedStudent);

        ResultActions result = mockMvc.perform(put("/api/students/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        result.andExpect(status().isOk())
                .andExpect(content().string("Student updated successfully"));

        verify(studentService, times(1)).updateStudent(eq(studentId), any(Student.class));
        verifyNoMoreInteractions(studentService);
    }

    @Test
    void deleteStudent_shouldReturnSuccessMessage() throws Exception {
        Long studentId = 1L;
        when(studentService.getStudentById(studentId)).thenReturn(new Student(studentId, "ToDelete", 25, 3000));

        ResultActions result = mockMvc.perform(delete("/api/students/{id}", studentId));

        result.andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));

        verify(studentService, times(1)).deleteStudent(studentId);
        verifyNoMoreInteractions(studentService);
    }



}

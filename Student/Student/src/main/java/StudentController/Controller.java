package StudentController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import StudentDetails.Student;
import StudentRepo.StudentRepository;
import StudentService.StudentService;

@RestController
@RequestMapping("/api/students")

public class Controller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
    	LOGGER.info("Get all students.");
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
    	LOGGER.info("Get student with ID: {}", id);
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        LOGGER.info("Student created successfully.");
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        studentService.updateStudent(id, updatedStudent);
        LOGGER.info("Student updated successfully.");
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        LOGGER.info("Student deleted successfully.");
    }
}
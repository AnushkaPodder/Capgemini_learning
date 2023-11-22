package StudentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import StudentDetails.Student;
import StudentRepo.StudentRepository;

@Service
public class StudentServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
    	LOGGER.info("Fetching all students from the database.");
        return studentRepository.findAll();
    }

   
    public Student getStudentById(Long id) {
    	 LOGGER.info("Fetching student with ID: {} from the database.", id);
        return studentRepository.findById(id).orElse(null);
    }

    
    public void createStudent(Student student) {
        studentRepository.save(student);
        LOGGER.info("Student saved successfully.");
    }

    
    public void updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            updatedStudent.setId(id);
            studentRepository.save(updatedStudent);
            LOGGER.info("Student updated successfully.");
        } else {
            LOGGER.warn("Student with ID {} not found. Update operation aborted.", id);
        }
        
    }

    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        LOGGER.info("Student deleted successfully.");
    }
}



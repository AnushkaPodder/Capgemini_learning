package StudentService;

import java.util.List;
import StudentDetails.Student;

public interface StudentService {
	
	 List<Student> getAllStudents();

	    Student getStudentById(Long id);

	    void createStudent(Student student);

	    void updateStudent(Long id, Student updatedStudent);

	    void deleteStudent(Long id);

}

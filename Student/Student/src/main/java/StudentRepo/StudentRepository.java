package StudentRepo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import StudentDetails.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {
	
}
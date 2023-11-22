package StudentDetails;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="studentDetails")
public class Student {
	
	private Long id;
	private String name;
    private int age;
    private int salary;
    
    public Student(Long id, String name, int age, int salary){
		super();
		
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
    }
    
    public Student() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String lastName) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int age) {
		this.salary = salary;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    
	}

}

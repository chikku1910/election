package org.mgm.elector.model;

import java.util.List;
import java.util.Set;

public interface StudentDAO {

	public List<Department> getDepartments();
	public void persistStudent(Student student);
	public Set<Student> getAllStudents();
	public void deleteStudent(String id);
	//public Student getStudent()
}

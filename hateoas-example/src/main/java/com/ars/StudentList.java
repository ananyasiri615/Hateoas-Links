package com.ars;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class StudentList extends RepresentationModel<StudentList> {

	private List<Student> students = new ArrayList<Student>();

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}

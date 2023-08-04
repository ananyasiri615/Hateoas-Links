package com.ars;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;

	@GetMapping("/students")
	public StudentList getAllStudents() {
		StudentList studentList = new StudentList();

		for (Student student : repository.findAll()) {

			addLinkToStudent(student);
			studentList.getStudents().add(student);
		}

		// Adding self link student collection resource
		Link selfLink = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();
		studentList.add(selfLink);

		return studentList;
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {

		Optional<Student> studentOpt = repository.findById(id);

		if (studentOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Student student = studentOpt.get();
		//addLinkToStudent(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/students/{id}/report")
	public ResponseEntity<StudentReportResult> getReportByStudentById(@PathVariable("id") int id) {
		Optional<Student> studentOpt = repository.findById(id);
		Student student = studentOpt.get();
		StudentReportResult report = new StudentReportResult();
		report.getOverallReport();
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	private void addLinkToStudent(Student student) {

		// Adding self link student 'singular' resource
		Link link = linkTo(StudentController.class).slash("students").slash(student.getId()).withSelfRel();
		student.add(link);
		
		
		// Adding method link student 'singular' resource
		ResponseEntity<StudentReportResult> methodLinkBuilder1 = methodOn(StudentController.class).getReportByStudentById(student.getId());
		Link reportLink = linkTo(methodLinkBuilder1).withRel("student-report");
		student.add(reportLink);
	}
}
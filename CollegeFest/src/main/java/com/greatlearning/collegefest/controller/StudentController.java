package com.greatlearning.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.collegefest.entity.Student;
import com.greatlearning.collegefest.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model model) {
		//data from student table
		List<Student> students = studentService.findAll();
		//add to the spring model
		model.addAttribute("Students", students);  //"Student"
		//return "students-list";
		return "student-details";
	}
	
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		//model attribute to bind data
		Student student = new Student();
		model.addAttribute("Student", student);
		return "student-form";
	}
	
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		//student from Service
		Student student = new Student();
		//show student details in the updation form
		student = studentService.findById(id);
		model.addAttribute("Student",student);
		return "student-form";
	}
	
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course, @RequestParam("country") String country) {
		System.out.println(id);
		Student student;
		
		if(id != 0) {
			student = studentService.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}
		else {
			student = new Student(firstName, lastName, course, country);
		}
		studentService.save(student);
		
		return "redirect:/student/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/student/list";
	}
	
}

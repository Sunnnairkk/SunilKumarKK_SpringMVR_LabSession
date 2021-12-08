package com.greatLearning.collegeFest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.collegeFest.entity.Student;
import com.greatLearning.collegeFest.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();
		model.addAttribute("Students", students);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = new Student();
		student = studentService.findById(id);
		model.addAttribute("Student", student);
		return "Student-form";

	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String fName,
			@RequestParam("lastName") String lName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student student = null;

		if (id != 0) {
			student.setFirstName(fName);
			student.setLastName(lName);
			student.setCourse(course);
			student.setCountry(country);
		} 
		else {
			student = new Student(fName, lName, course, country);
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

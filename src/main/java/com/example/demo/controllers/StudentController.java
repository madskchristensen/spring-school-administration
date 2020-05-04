package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.repositories.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController{

    private IStudentRepository studentRepository;

    public StudentController() {
        studentRepository = new StudentRepositoryImpl();
    }

    @GetMapping("/")
    public String index(){
        // Student student = new Student(1,"hejehej","Lotte", 1533, 10, 10,1234567890);
        // studentRepository.update(student);
        // studentRepository.create(new Student(50,"dadadada","rqrqrq", 2010, 10, 10,1234567890));
        return "index";
    }

    //Very simple prototype of GET-request with parameter
    //https://www.baeldung.com/spring-request-param
    //TODO Direct to detailed view of student
    @GetMapping("/student")
    @ResponseBody
    public String getStudentByParameter(@RequestParam int id) {
        Student student = studentRepository.read(id);
        return student.toString();
    }

    @GetMapping("/courses")
    public String courses() {

        return "courses";
    }

    @GetMapping("/students")
    public String studentOverview(Model model) {
        model.addAttribute("students", studentRepository.readAll());
        return "students";
    }

    // create student method
    @GetMapping("/student/create")
    public String createStudentShow(){
        return "/student/create";
    }

    @PostMapping("/student/createDo")
    public String studentInput(@ModelAttribute Student student) {
        studentRepository.create(student);

        return "redirect:/students";
    }

    // delete student method

    // edit student method
}
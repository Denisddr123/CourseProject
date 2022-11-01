package com.students.webappwithsecurity.controller;

import com.students.webappwithsecurity.entity.Student;
import com.students.webappwithsecurity.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public ModelAndView getAllStudents() {
        log.info("/list -> connection");
        ModelAndView modelAndView = new ModelAndView("list-students");
        modelAndView.addObject("students", studentRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        ModelAndView modelAndView = new ModelAndView("add-student-form");
        Student student = new Student();
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId) {
        ModelAndView modelAndView = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if (optionalStudent.isPresent()) {
            student = optionalStudent.get();
        }
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}

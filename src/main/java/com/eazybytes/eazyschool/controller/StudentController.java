package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Request;
import com.eazybytes.eazyschool.repository.RequestRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person",person);
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String courseId, @RequestParam String studentId) {
        try {
            Request request = new Request();
            request.setStudentId(studentId);
            request.setCourseId(courseId);
            request.setRequestType(Request.RequestType.REGISTER);
            request.setStatus(Request.Status.PENDING);
            requestRepository.save(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leave(@RequestParam String courseId, @RequestParam String studentId) {
        try {
            Request request = new Request();
            request.setStudentId(studentId);
            request.setCourseId(courseId);
            request.setRequestType(Request.RequestType.LEAVE);
            request.setStatus(Request.Status.PENDING);
            requestRepository.save(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

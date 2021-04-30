package com.kloia.multithread.controller;

import com.kloia.multithread.dto.StudentFindByClassroomIdDto;
import com.kloia.multithread.exception.StudentNotFoundException;
import com.kloia.multithread.model.Student;
import com.kloia.multithread.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public List<Student> getAllStudent() {
        UUID requestId = UUID.randomUUID();
        return studentService.getAllStudent(requestId);
    }

    @PostMapping("/find-by-classroom-ids")
    public List<Student> getStudentByClassroomIds(@RequestBody StudentFindByClassroomIdDto dto) {
        return studentService.getStudentByClassroomIds(dto);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) throws StudentNotFoundException {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable("id") int id) {
        return studentService.delete(id);
    }

    @PostMapping()
    private Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Integer id) throws StudentNotFoundException {
        return studentService.update(student, id);
    }

}

package com.kloia.multithread.service;


import com.kloia.multithread.configuration.RequestContext;
import com.kloia.multithread.converter.StudentConverter;
import com.kloia.multithread.dto.StudentFindByClassroomIdDto;
import com.kloia.multithread.exception.StudentNotFoundException;
import com.kloia.multithread.model.Student;
import com.kloia.multithread.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConverter studentConverter;
    @Autowired
    private ClassroomService classroomService;

    @Resource(name = "requestContext")
    private RequestContext requestContext;

    public List<Student> getAllStudent() {
        log.info(requestContext.toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(requestContext.toString());
        return Collections.emptyList();
    }

    public List<Student> getAllStudent(UUID requestId) {
        log.info(this.requestContext.toString());
        log.info(requestId.toString());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(this.requestContext.toString());
        log.info(requestId.toString());

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Date date = this.requestContext.getDate();

        executorService.execute(() -> {
            System.out.println(date);
            classroomService.getAllClassroom(requestId);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        return Collections.emptyList();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public List<Student> getStudentByClassroomIds(StudentFindByClassroomIdDto dto) {
        return studentRepository.findStudentsByClassroomIds(dto.getClassroomIds());
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public int delete(int id) {
        studentRepository.deleteById(id);
        return id;
    }

    public Student update(Student newStudent, Integer id) throws StudentNotFoundException {
        Optional<Student> opt = studentRepository.findById(id);
        Student student = studentConverter.convert(newStudent,
                opt.orElseThrow(() -> new StudentNotFoundException("Stundet not found")));
        return studentRepository.save(student);
    }
}

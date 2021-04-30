package com.kloia.multithread.service;

import com.kloia.multithread.configuration.RequestContext;
import com.kloia.multithread.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ClassroomService {

    public List<Student> getAllClassroom(UUID requestId) {
        RequestContext requestContext = ContextUtils.getRequestContext();
        log.info(requestContext.toString());
        log.info(requestId.toString());
        return Collections.emptyList();
    }
}

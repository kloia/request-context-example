package com.kloia.multithread.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentFindByClassroomIdDto {
    List<Integer> classroomIds;
}

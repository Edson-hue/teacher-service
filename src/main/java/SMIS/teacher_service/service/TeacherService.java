package SMIS.teacher_service.service;

import SMIS.teacher_service.dto.TeacherCourseResponse;
import SMIS.teacher_service.dto.TeacherDto;
import SMIS.teacher_service.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher addTeacher(TeacherDto teacherDto);
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    Optional<Teacher> getTeacherByStaffId(String staffId);

    // Inter service communication with Course
    TeacherCourseResponse getTeacherWithCourses(Long id);
}

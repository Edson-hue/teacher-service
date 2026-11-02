package SMIS.teacher_service.controller;

import SMIS.teacher_service.dto.TeacherCourseResponse;
import SMIS.teacher_service.dto.TeacherDto;
import SMIS.teacher_service.entity.Teacher;
import SMIS.teacher_service.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.addTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/staff-id")
    public ResponseEntity<Teacher>  getTeacherByStaffId(@PathVariable("staffId") String staffId) {
        return teacherService.getTeacherByStaffId(staffId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<TeacherCourseResponse> getTeacherWithCourses(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teacherService.getTeacherWithCourses(id));
    }
}

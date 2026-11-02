package SMIS.teacher_service.FeignClient;

import SMIS.teacher_service.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-service")
public interface CourseClient {
    @GetMapping("/courses/teacher/{teacher-id}")
    List<CourseDto> getCoursesByTeacher(@PathVariable("teacher-id") Long teacherId);
}

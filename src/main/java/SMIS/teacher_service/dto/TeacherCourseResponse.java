package SMIS.teacher_service.dto;

import SMIS.teacher_service.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourseResponse {
    private Teacher teacher;
    private List<CourseDto> courses;
    private boolean coursesAvailable;
}

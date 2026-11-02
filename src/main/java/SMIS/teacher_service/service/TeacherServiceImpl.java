package SMIS.teacher_service.service;

import SMIS.teacher_service.dto.CourseDto;
import SMIS.teacher_service.dto.TeacherCourseResponse;
import SMIS.teacher_service.dto.TeacherDto;
import SMIS.teacher_service.entity.Teacher;
import SMIS.teacher_service.exceptions.DuplicateResourceException;
import SMIS.teacher_service.exceptions.ResourceNotFoundException;
import SMIS.teacher_service.remoteService.RemoteCourseService;
import SMIS.teacher_service.repostory.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final RemoteCourseService remoteCourseService;

    @Override
    public Teacher addTeacher(TeacherDto teacherDto) {
        if (teacherRepository.existsByStaffId(teacherDto.getStaffId())){
            throw new DuplicateResourceException("Teacher with the Staff id " + teacherDto.getStaffId() + " already exists");
        }
        if (teacherRepository.existsByEmail(teacherDto.getEmail())) {
            throw new DuplicateResourceException("Teacher with email " + teacherDto.getEmail() + " already exists");
        }
        Teacher teacher = new Teacher();
        teacher.setStaffId(teacherDto.getStaffId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhone(teacherDto.getPhone());
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> getTeacherByStaffId(String staffId) {
        return teacherRepository.findByStaffId(staffId);
    }

    @Override
    public TeacherCourseResponse getTeacherWithCourses(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + id));
        List<CourseDto> courses = remoteCourseService.getCoursesByTeacher(id);
        if (courses == null) {
            return new TeacherCourseResponse(teacher, List.of(), false);
        }
        return new TeacherCourseResponse(teacher, courses, true);
    }
}

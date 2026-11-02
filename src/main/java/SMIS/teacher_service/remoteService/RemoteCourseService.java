package SMIS.teacher_service.remoteService;

import SMIS.teacher_service.FeignClient.CourseClient;
import SMIS.teacher_service.dto.CourseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoteCourseService {
    private final CourseClient courseClient;

    @Retry(name = "course-service", fallbackMethod = "coursesFallback")
    @CircuitBreaker(name = "course-service")
    @RateLimiter(name = "course-service")
    public List<CourseDto> getCoursesByTeacher(Long teacherId) {
        return courseClient.getCoursesByTeacher(teacherId);
    }

    /** Course Service is down: return null so the caller can show the teacher without courses. */
    public List<CourseDto> coursesFallback(Long teacherId, Throwable ex) {
        return null;
    }
}

package SMIS.teacher_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.DateType;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String first_name;
    private String last_name;
    private String middle_name;
    private DateType birth_date;
    private String address;
    private String faculty;
    private String speciality;
    private int course;
}

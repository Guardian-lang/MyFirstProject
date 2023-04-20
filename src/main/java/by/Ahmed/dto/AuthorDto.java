package by.Ahmed.dto;

import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import lombok.*;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AuthorDto {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    Integer age;
    String occupation;
    String jobTitle;
    CheckStatus checkStatus;
    File about;
}



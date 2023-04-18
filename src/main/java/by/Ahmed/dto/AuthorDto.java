package by.Ahmed.dto;

import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private String occupation;
    private String jobTitle;
    private CheckStatus checkStatus;
    private File about;
}

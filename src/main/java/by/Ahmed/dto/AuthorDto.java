package by.Ahmed.dto;

import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AuthorDto {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDateTime birthDate;
    String occupation;
    String jobTitle;
    CheckStatus checkStatus;
    String about;
    String email;
    String password;
}



package by.Ahmed.dto;

import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateAuthorDto {
    String id;
    String firstName;
    String lastName;
    String gender;
    String birthDate;
    String occupation;
    String jobTitle;
    String checkStatus;
    String about;
    String authorizationId;
}
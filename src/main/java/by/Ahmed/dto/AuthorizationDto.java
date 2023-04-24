package by.Ahmed.dto;

import by.Ahmed.entity.CheckStatus;
import by.Ahmed.entity.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorizationDto {
    Long id;
    String email;
    String password;
}

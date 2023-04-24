package by.Ahmed.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Admin {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDateTime date;
    private AdminRules adminRules;
    private Long authorizationId;
}

package by.Ahmed.entity;

import lombok.*;

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
    private int age;
    private AdminRules adminRules;
}

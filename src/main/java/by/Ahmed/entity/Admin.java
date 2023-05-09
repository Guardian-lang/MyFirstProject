package by.Ahmed.entity;

import lombok.*;

import java.sql.Date;

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
    private Date date;
    private AdminRules adminRules;
}

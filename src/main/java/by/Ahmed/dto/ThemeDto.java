package by.Ahmed.dto;

import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ThemeDto {
    private Long id;
    private String name;
    private File description;
}

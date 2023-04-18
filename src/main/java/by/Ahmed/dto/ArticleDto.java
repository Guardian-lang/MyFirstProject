package by.Ahmed.dto;


import by.Ahmed.entity.Author;
import by.Ahmed.entity.Theme;
import lombok.*;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleDto {
    private Long id;
    private Theme theme;
    private Author author;
    private String title;
    private LocalDateTime date;
    private File text;
}

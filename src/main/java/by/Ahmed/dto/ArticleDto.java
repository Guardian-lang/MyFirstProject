package by.Ahmed.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDto {
    Long id;
    Long themeId;
    Long authorId;
    String title;
    LocalDateTime date;
    String text;
}

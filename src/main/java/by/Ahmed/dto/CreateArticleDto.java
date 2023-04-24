package by.Ahmed.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateArticleDto {
    String id;
    String themeId;
    String authorId;
    String title;
    String date;
    String text;
}

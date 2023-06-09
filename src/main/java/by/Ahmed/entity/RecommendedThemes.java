package by.Ahmed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recommended_themes")
public class RecommendedThemes {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "theme_id")
    private Long themeId;
}

package by.Ahmed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "trash_can")
public class TrashCan {
    @Id
    private Long id;
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "admin_id")
    private Long adminId;
}

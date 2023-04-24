package by.Ahmed.entity;

import by.Ahmed.dao.AuthorizationDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authorization {
    private Long id;
    private String email;
    private String password;

    public Authorization(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package by.Ahmed.service;

import by.Ahmed.dao.AuthorizationDao;
import by.Ahmed.dto.ArticleDto;
import by.Ahmed.dto.AuthorizationDto;
import by.Ahmed.entity.Authorization;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationService {
    private static final AuthorizationService INSTANCE = new AuthorizationService();
    public static AuthorizationService getInstance() {return INSTANCE;}
    private static final AuthorizationDao authorizationDao = AuthorizationDao.getInstance();

    public AuthorizationService() {

    }

    public static List<AuthorizationDto> findAll() {
        return authorizationDao.readAll().stream().map(
                authorization -> new AuthorizationDto(
                        authorization.getId(),
                        authorization.getEmail(),
                        authorization.getPassword()
                )
        ).collect(Collectors.toList());
    }

    public Authorization setAuthorization(String email, String password) {
        return authorizationDao.create(new Authorization(email, password));
    }
}

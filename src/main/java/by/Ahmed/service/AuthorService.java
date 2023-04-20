package by.Ahmed.service;

import by.Ahmed.dao.AuthorDao;
import by.Ahmed.dto.AuthorDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AuthorService {
    private static final AuthorService INSTANCE = new AuthorService();
    private static final AuthorDao authorDao = AuthorDao.getInstance();

    public static AuthorService getInstance() {
        return INSTANCE;
    }

}

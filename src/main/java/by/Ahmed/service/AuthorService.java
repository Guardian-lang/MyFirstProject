package by.Ahmed.service;

import by.Ahmed.dao.AuthorDao;
import by.Ahmed.dto.ArticleDto;
import by.Ahmed.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorService {
    private static final AuthorService INSTANCE = new AuthorService();
    private static final AuthorDao authorDao = AuthorDao.getInstance();

    public AuthorService() {

    }

    private static AuthorService getInstance() {
        return INSTANCE;
    }

    public List<AuthorDto> findAll() {
        return authorDao.readAll().stream().map(
                author -> new ArticleDto(
                        author.getId(),
                        """
                           %s - %s - %s - %s - %s - %s - %s
                        """.formatted(
                                author.getFirstName(),
                                author.getLastName(),
                                author.getGender(),
                                author.getAge(),
                                author.getOccupation(),
                                author.getJobTitle(),
                                author.getCheckStatus()
                        ),
                        author.getAbout()
                )
        ).collect(Collectors.toList());
    }
}

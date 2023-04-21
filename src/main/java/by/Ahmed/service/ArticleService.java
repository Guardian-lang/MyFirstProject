package by.Ahmed.service;

import by.Ahmed.dao.ArticleDao;
import by.Ahmed.dto.ArticleDto;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    private static final ArticleService INSTANCE = new ArticleService();
    private static final ArticleDao articleDao = ArticleDao.getInstance();

    public ArticleService() {

    }

    public static ArticleService getInstance() {
        return INSTANCE;
    }

    public static List<ArticleDto> findAll() {
        return articleDao.readAll().stream().map(
                article -> new ArticleDto(
                        article.getId(),
                        article.getThemeId(),
                        article.getAuthorId(),
                        article.getTitle(),
                        article.getDate(),
                        article.getText()
                )
        ).collect(Collectors.toList());
    }
}

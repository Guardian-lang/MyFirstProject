package by.Ahmed.service;

import by.Ahmed.dao.ArticleDao;
import by.Ahmed.dto.ArticleDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    private static final ArticleService INSTANCE = new ArticleService();
    private static final ArticleDao articleDao = ArticleDao.getInstance();

    public ArticleService() {

    }

    private static ArticleService getInstance() {
        return INSTANCE;
    }

    public List<ArticleDto> findAll() {
        return articleDao.readAll().stream().map(
                article -> new ArticleDto(
                        article.getId(),
                        """
                           %s - %s - %s
                        """.formatted(
                                article.getTheme().getId(),
                                article.getAuthor().getId(),
                                article.getTitle()
                        ),
                        article.getText()
                )
        ).collect(Collectors.toList());
    }
}

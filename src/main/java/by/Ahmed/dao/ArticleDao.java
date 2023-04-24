package by.Ahmed.dao;

import by.Ahmed.exceptions.DaoException;
import by.Ahmed.entity.Article;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDao implements Dao<Article> {
    private static final ArticleDao INSTANCE = new ArticleDao();
    public static ArticleDao getInstance() {
        return INSTANCE;
    }
    private static final ThemeDao themeDao = ThemeDao.getInstance();
    private static final AuthorDao authorDao = AuthorDao.getInstance();

    private static final String CREATE_SQL = """
            INSERT INTO article (theme_id, author_id, title, date, text) VALUES
            (?, ?, ?, ?, ?);""";

    private static final String READ_SQL = """
            SELECT t.id, au.id, a.title, a.date, a.text FROM article a
            JOIN theme t on a.theme_id = t.id
            JOIN author au on au.id = a.author_id
            """;

    private static final String READ_SQL_BY_ARTICLE_ID = READ_SQL + """
            WHERE a.id = ?;""";

    private static final String READ_SQL_BY_THEME_ID = READ_SQL + """
            WHERE t.id = ?;""";

    private static final String READ_SQL_BY_AUTHOR_ID = READ_SQL + """
            WHERE au.id = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE article SET
            theme_id = ?,
            author_id = ?,
            title = ?,
            date = ?,
            text = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM article
            WHERE id = ?""";

    @Override
    public Article create(Article article) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(article, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                article.setId(generatedKeys.getLong("id"));
            }
            return article;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setStatement(Article article, PreparedStatement statement) throws SQLException {
        statement.setLong(1, article.getThemeId());
        statement.setLong(2, article.getAuthorId());
        statement.setString(3, article.getTitle());
        statement.setTimestamp(4, Timestamp.valueOf(article.getDate()));
        statement.setString(5, article.getText());
    }

    @Override
    public boolean update(Article article) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(article, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Article> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<Article> articles = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                articles.add(build(result));
            }
            return articles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Article build(ResultSet result) throws SQLException {
        return new Article(
                result.getLong("id"),
                themeDao.findById(
                        result.getLong("theme_id"),
                        result.getStatement().getConnection()
                ).orElse(null).getId(),
                authorDao.findById(
                        result.getLong("author_id"),
                        result.getStatement().getConnection()
                ).orElse(null).getId(),
                result.getString("title"),
                result.getTimestamp("date").toLocalDateTime(),
                result.getString("text")
        );
    }

    @Override
    public Optional<Article> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_ARTICLE_ID);
    }

    public Optional<Article> findByThemeId(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_THEME_ID);
    }

    public Optional<Article> findByAuthorId(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_AUTHOR_ID);
    }

    @Override
    public Optional<Article> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            Article article = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                article = build(result);
            return Optional.ofNullable(article);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

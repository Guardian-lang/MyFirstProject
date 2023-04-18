package by.Ahmed.dao;

import by.Ahmed.entity.Author;
import by.Ahmed.entity.AuthorThemes;
import by.Ahmed.exceptions.DaoException;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorThemesDao implements Dao<AuthorThemes> {

    private static final AuthorThemesDao INSTANCE = new AuthorThemesDao();

    private static final AuthorDao authorDao = AuthorDao.getInstance();

    private static final ThemeDao themeDao = ThemeDao.getInstance();

    private static final String CREATE_SQL = """
            INSERT INTO author_themes (author_id, theme_id) VALUES
            (?, ?);""";

    private static final String READ_SQL = """
            SELECT au.first_name ||' '|| au.last_name, t.name FROM author au
            JOIN author_themes at on au.id = at.author_id
            JOIN theme t on t.id = at.theme_id
            """;

    private static final String READ_SQL_BY_THEME_ID = READ_SQL + """
            WHERE t.id = ?;""";

    private static final String READ_SQL_BY_AUTHOR_ID = READ_SQL + """
            WHERE au.id = ?;""";

    private static final String UPDATE_SQL_BY_AUTHOR_ID = """
            UPDATE author_themes SET
            theme_id = ?
            WHERE author_id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM author_themes
            WHERE author_id = ?""";

    @Override
    public AuthorThemes create(AuthorThemes authorThemes) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(authorThemes, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                authorThemes.setAuthor(authorDao.findById(result -> {
                            result.getLong("author_id"); result.getStatement().getConnection();
                        }
                ).orElse(null)).setId(generatedKeys.getLong("id"));
            }
            return authorThemes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(AuthorThemes authorThemes) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL_BY_AUTHOR_ID)) {
            setStatement(authorThemes, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AuthorThemes> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<AuthorThemes> authorThemes = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                authorThemes.add(build(result));
            }
            return authorThemes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<AuthorThemes> findById(Long id, Connection connection) {
        return Optional.empty();
    }

    @Override
    public AuthorThemes build(ResultSet result) throws SQLException {
        return new AuthorThemes(
                authorDao.findById(
                        result.getLong("author_id"),
                        result.getStatement().getConnection()
                ).orElse(null),
                themeDao.findById(
                        result.getLong("theme_id"),
                        result.getStatement().getConnection()
                ).orElse(null)
        );
    }

    @Override
    public void setStatement(AuthorThemes authorThemes, PreparedStatement statement) throws SQLException {
        statement.setLong(1, authorThemes.getAuthor().getId());
        statement.setLong(2, authorThemes.getTheme().getId());
    }

    @Override
    public Optional<AuthorThemes> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            AuthorThemes authorThemes = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                authorThemes = build(result);
            return Optional.ofNullable(authorThemes);
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

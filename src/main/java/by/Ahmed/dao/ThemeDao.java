package by.Ahmed.dao;

import by.Ahmed.exceptions.DaoException;
import by.Ahmed.entity.Theme;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThemeDao implements Dao<Theme>{

    private static final ThemeDao INSTANCE = new ThemeDao();

    public static ThemeDao getInstance() {
        return INSTANCE;
    }

    public ThemeDao() {
    }

    private static final String CREATE_SQL = """
            INSERT INTO theme (name, description) VALUES
            (?, ?);""";

    private static final String READ_SQL = """
            SELECT id, name,
            description,
            FROM theme
            """;

    private static final String READ_SQL_BY_THEME_ID = READ_SQL + """
            WHERE id = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE theme SET
            name = ?,
            description = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM theme
            WHERE id = ?""";

    @Override
    public Theme create(Theme theme) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(theme, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                theme.setId(generatedKeys.getLong("id"));
            }
            return theme;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Theme theme) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(theme, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Theme> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<Theme> themes = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                themes.add(build(result));
            }
            return themes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Theme build(ResultSet result) throws SQLException {
        return new Theme(
                result.getLong("id"),
                result.getString("name"),
                result.getString("description")
        );
    }

    @Override
    public Optional<Theme> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_THEME_ID);
    }

    @Override
    public void setStatement(Theme theme, PreparedStatement statement) throws SQLException {
        statement.setString(1, theme.getName());
        statement.setString(2, theme.getDescription());
    }

    @Override
    public Optional<Theme> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            Theme theme = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                theme = build(result);
            return Optional.ofNullable(theme);
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

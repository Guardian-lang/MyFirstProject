package by.Ahmed.dao;

import by.Ahmed.exceptions.DaoException;
import by.Ahmed.entity.*;
import by.Ahmed.utils.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDao implements Dao<Author> {

    private static final AuthorDao INSTANCE = new AuthorDao();

    public static AuthorDao getInstance() {
        return INSTANCE;
    }

    public AuthorDao() {
    }

    private static final String CREATE_SQL = """
            INSERT INTO author (first_name, last_name, gender, birth_date, occupation, job_title, check_status, about) VALUES
            (?, ?, ?, ?, ?, ?, ?);""";

    private static final String READ_SQL = """
            SELECT au.id,
            au.first_name ||' '||
            au.last_name fio,
            au.gender,
            au.birth_date,
            au.occupation,
            au.job_title,
            au.check_status,
            au.about
            FROM author au
            """;

    private static final String READ_SQL_BY_AUTHOR_ID = READ_SQL + """
            WHERE au.id = ?;""";

    private static final String READ_SQL_BY_EMAIL = READ_SQL + """
            WHERE au.email = ?""";

    private static final String READ_SQL_BY_EMAIL_AND_PASSWORD = READ_SQL + """
            WHERE au.email = ? AND au.password = ?""";

    private static final String UPDATE_SQL = """
            UPDATE author SET
            first_name = ?,
            last_name = ?,
            gender = ?,
            birth_date = ?,
            occupation = ?,
            job_title = ?,
            check_status = ?,
            about = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM author
            WHERE id = ?""";

    @Override
    public Author create(Author author) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(author, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getLong("id"));
            }
            return author;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Author author) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(author, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void setStatement(Author author, PreparedStatement statement) throws SQLException {
        statement.setString(1, author.getFirstName());
        statement.setString(2, author.getLastName());
        statement.setObject(3, author.getGender());
        statement.setTimestamp(4, Timestamp.valueOf(author.getBirthDate()));
        statement.setString(5, author.getOccupation());
        statement.setString(6, author.getJobTitle());
        statement.setObject(7, author.getCheckStatus());
        statement.setString(8, author.getAbout());
    }

    @Override
    public List<Author> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<Author> authors = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                authors.add(build(result));
            }
            return authors;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Author build(ResultSet result) throws SQLException {
        return new Author(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("last_name"),
                (Gender) result.getObject("gender"),
                result.getTimestamp("birth_date").toLocalDateTime(),
                result.getString("occupation"),
                result.getString("job_title"),
                (CheckStatus) result.getObject("check_status"),
                result.getString("about"),
                result.getString("email"),
                result.getString("password")
        );
    }

    @Override
    public Optional<Author> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_AUTHOR_ID);
    }

    @SneakyThrows
    public Optional<Author> findByEmail(String email) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(READ_SQL_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = build(resultSet);
            }

            return Optional.ofNullable(author);
        }
    }

    @SneakyThrows
    public Optional<Author> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(READ_SQL_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = build(resultSet);
            }

            return Optional.ofNullable(author);
        }
    }

    @Override
    public Optional<Author> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            Author author = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                author = build(result);
            return Optional.ofNullable(author);
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

package by.Ahmed.dao;

import by.Ahmed.exceptions.DaoException;
import by.Ahmed.entity.*;
import by.Ahmed.utils.ConnectionManager;

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
            INSERT INTO author (first_name, last_name, gender, age, occupation, job_title, check_status, about) VALUES
            (?, ?, ?, ?, ?, ?, ?, ?);""";

    private static final String READ_SQL = """
            SELECT au.id,
            au.first_name ||' '||
            au.last_name fio,
            au.gender,
            au.age,
            au.occupation,
            au.job_title,
            au.check_status,
            au.about,
            a.id FROM author au
            JOIN article a on au.id = a.author_id
            """;

    private static final String READ_SQL_BY_ARTICLE_ID = READ_SQL + """
            WHERE a.id = ?;""";

    private static final String READ_SQL_BY_AUTHOR_ID = READ_SQL + """
            WHERE au.id = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE author SET
            first_name = ?,
            last_name = ?,
            gender = ?,
            age = ?,
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
        statement.setInt(4, author.getAge());
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
                result.getInt("age"),
                result.getString("occupation"),
                result.getString("job_title"),
                (CheckStatus) result.getObject("check_status"),
                result.getString("about")
        );
    }

    @Override
    public Optional<Author> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_AUTHOR_ID);
    }

    public Optional<Author> findByArticleId(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_ARTICLE_ID);
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

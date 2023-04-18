package by.Ahmed.dao;

import by.Ahmed.entity.Author;
import by.Ahmed.entity.AuthorIps;
import by.Ahmed.exceptions.DaoException;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorIpsDao implements Dao<AuthorIps> {

    private static final AuthorIpsDao INSTANCE = new AuthorIpsDao();

    private static final AuthorDao authorDao = AuthorDao.getInstance();

    public static AuthorIpsDao getInstance() {
        return INSTANCE;
    }

    private static final String CREATE_SQL = """
            INSERT INTO author_ips (author_id, author_ip) VALUES
            (?, ?);""";

    private static final String READ_SQL = """
            SELECT ai.author_ip, au.first_name ||' '|| au.last_name FROM author_ips ai
            JOIN author au on ai.author_id = au.id
            """;

    private static final String READ_SQL_BY_AUTHOR_IP = READ_SQL + """
            WHERE ai.author_ip = ?;""";

    private static final String READ_SQL_BY_AUTHOR_ID = READ_SQL + """
            WHERE au.id = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE author_ips SET
            author_ip = ?
            WHERE author_id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM article
            WHERE author_id = ?""";

    @Override
    public AuthorIps create(AuthorIps authorIps) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(authorIps, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                authorIps.setId(generatedKeys.getLong("author_id"));
            }
            return authorIps;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(AuthorIps authorIps) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(authorIps, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<AuthorIps> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<AuthorIps> authorIps = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                authorIps.add(build(result));
            }
            return authorIps;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<AuthorIps> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_AUTHOR_ID);
    }

    @Override
    public AuthorIps build(ResultSet result) throws SQLException {
        return new AuthorIps(
                (Author) authorDao.findById(
                        result.getLong("author_id"),
                        result.getStatement().getConnection()
                ).orElse(null),
                result.getString("author_ip")
        );
    }

    @Override
    public void setStatement(AuthorIps authorIps, PreparedStatement statement) throws SQLException {
        statement.setLong(1, authorIps.getAuthor().getId());
        statement.setString(3, authorIps.getAuthorIp());
    }

    @Override
    public Optional<AuthorIps> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            AuthorIps authorIps = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                authorIps = build(result);
            return Optional.ofNullable(authorIps);
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

package by.Ahmed.dao;

import by.Ahmed.entity.Author;
import by.Ahmed.entity.User;
import by.Ahmed.exceptions.DaoException;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static final String CREATE_SQL = """
            INSERT INTO user (user_ip) VALUES
            (?);""";

    private static final String READ_SQL = """
            SELECT id, user_ip FROM user
            """;

    private static final String READ_SQL_BY_ID = READ_SQL + """
            WHERE id = ?""";

    private static final String READ_SQL_BY_USER_IP = READ_SQL + """
            WHERE user_ip = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE user SET
            user_ip = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM user
            WHERE id = ?""";

    @Override
    public User create(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(user, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(user, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<User> users = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                users.add(build(result));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User build(ResultSet result) throws SQLException {
        return new User(
                result.getLong("id"),
                result.getString("user_ip")
        );
    }

    @Override
    public Optional<User> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_ID);
    }

    public Optional<User> findByUserIp(String userIp, Connection connection) {
        return tryConnection(userIp, connection, READ_SQL_BY_ID);
    }

    @Override
    public void setStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getIp());
    }

    @Override
    public Optional<User> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            User user = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                user = build(result);
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<User> tryConnection(String userIp, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            User user = null;
            statement.setString(2, userIp);
            var result = statement.executeQuery();
            if (result.next())
                user = build(result);
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

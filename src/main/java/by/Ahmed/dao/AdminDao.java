package by.Ahmed.dao;

import by.Ahmed.entity.*;
import by.Ahmed.exceptions.DaoException;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminDao implements Dao<Admin> {

    private static final AdminDao INSTANCE = new AdminDao();

    private static final String CREATE_SQL = """
            INSERT INTO admin (first_name, last_name, gender, birth_date, admin_rules, authorization_id) VALUES
            (?, ?, ?, ?, ?, ?);""";

    private static final String READ_SQL = """
            SELECT
            ad.first_name ||' '||
            ad.last_name fio,
            ad.gender,
            ad.birth_date,
            ad.admin_rules,
            ad.authorization_id
            FROM admin ad
            """;

    private static final String READ_SQL_BY_ADMIN_ID = READ_SQL + """
            WHERE ad.id = ?;""";

    private static final String UPDATE_SQL = """
            UPDATE admin SET
            first_name = ?,
            last_name = ?,
            gender = ?,
            birth_date = ?,
            admin_rules = ?,
            authorization_id = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM admin
            WHERE id = ?""";

    private static AdminDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Admin create(Admin admin) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(admin, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                admin.setId(generatedKeys.getLong("id"));
            }
            return admin;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Admin admin) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(admin, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Admin> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<Admin> admins = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                admins.add(build(result));
            }
            return admins;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Admin> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_ADMIN_ID);
    }

    @Override
    public Admin build(ResultSet result) throws SQLException {
        return new Admin(
                result.getLong("id"),
                result.getString("first_name"),
                result.getString("last_name"),
                (Gender) result.getObject("gender"),
                result.getTimestamp("birth_date").toLocalDateTime(),
                (AdminRules) result.getObject("admin_rules"),
                result.getLong("authorization_id")
        );
    }

    @Override
    public void setStatement(Admin admin, PreparedStatement statement) throws SQLException {
        statement.setString(1, admin.getFirstName());
        statement.setString(2, admin.getLastName());
        statement.setObject(3, admin.getGender());
        statement.setTimestamp(4, Timestamp.valueOf(admin.getDate()));
        statement.setObject(5, admin.getAdminRules());
        statement.setLong(6, admin.getAuthorizationId());
    }

    @Override
    public Optional<Admin> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            Admin admin = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                admin = build(result);
            return Optional.ofNullable(admin);
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

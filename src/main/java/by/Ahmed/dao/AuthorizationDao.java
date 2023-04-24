package by.Ahmed.dao;

import by.Ahmed.entity.Article;
import by.Ahmed.entity.Author;
import by.Ahmed.entity.Authorization;
import by.Ahmed.exceptions.DaoException;
import by.Ahmed.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorizationDao implements Dao<Authorization> {

    private static final AuthorizationDao INSTANCE = new AuthorizationDao();
    public static AuthorizationDao getInstance() {return INSTANCE;}
    public static AuthorDao authorDao = AuthorDao.getInstance();

    private static final String CREATE_SQL = """
            INSERT INTO public.authorization (email, password) VALUES
            (?, ?);""";

    private static final String READ_SQL = """
            SELECT email, password FROM public.authorization
            """;

    private static final String READ_SQL_BY_ID = READ_SQL + """
            WHERE id = ?;""";

    private static final String READ_SQL_BY_EMAIL_AND_PASSWORD =  """
            SELECT au.id,
            au.first_name,
            au.last_name,
            au.gender,
            au.birth_date,
            au.occupation,
            au.job_title,
            au.check_status,
            au.about,
            au.authorization_id FROM author au
            JOIN public.authorization az on au.authorization_id = az.id
            WHERE az.email = ? AND az.password = ?""";

    private static final String UPDATE_SQL = """
            UPDATE public.authorization SET
            email = ?,
            password = ?
            WHERE id = ?;""";

    private static final String DELETE_SQL = """
            DELETE FROM authorization
            WHERE id = ?""";

    @Override
    public Authorization create(Authorization authorization) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(authorization, statement);
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                authorization.setId(generatedKeys.getLong("id"));
            }
            return authorization;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Authorization authorization) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            setStatement(authorization, statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Authorization> readAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(READ_SQL)) {
            List<Authorization> authorizations = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                authorizations.add(build(result));
            }
            return authorizations;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Authorization> findById(Long id, Connection connection) {
        return tryConnection(id, connection, READ_SQL_BY_ID);
    }

    public  Optional<Author> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(READ_SQL_BY_EMAIL_AND_PASSWORD)) {
            Authorization authorization = null;
            statement.setString(1, email);
            statement.setString(2, password);
            var result = statement.executeQuery();
            if (result.next())
                authorization = build(result);
            return authorDao.findByAuthorizationId(authorization.getId(), connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Authorization build(ResultSet result) throws SQLException {
        return new Authorization(
                result.getLong("id"),
                result.getString("email"),
                result.getString("password")
        );
    }

    @Override
    public void setStatement(Authorization authorization, PreparedStatement statement) throws SQLException {
        statement.setString(1, authorization.getEmail());
        statement.setString(2, authorization.getPassword());
    }

    @Override
    public Optional<Authorization> tryConnection(Long id, Connection connection, String sql) {
        try (var statement = connection.prepareStatement(sql)) {
            Authorization authorization = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                authorization = build(result);
            return Optional.ofNullable(authorization);
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

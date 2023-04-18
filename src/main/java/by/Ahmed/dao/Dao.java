package by.Ahmed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T>{

    public T create(T t);

    public boolean update(T t);

    public List<T> readAll();

    public Optional<T> findById(Long id, Connection connection);

    public T build(ResultSet result) throws SQLException;

    public void setStatement(T t, PreparedStatement statement) throws SQLException;

    public Optional<T> tryConnection(Long id, Connection connection, String sql);

    public boolean delete(Long id);
}

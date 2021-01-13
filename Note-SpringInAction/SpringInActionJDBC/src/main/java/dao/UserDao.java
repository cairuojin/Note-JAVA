package dao;

import domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> findAll() throws ClassNotFoundException, SQLException;
}

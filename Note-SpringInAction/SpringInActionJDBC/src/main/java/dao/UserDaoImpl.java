package dao;

import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cairuojin
 * @create 2019-03-19 22:27
 */
public class UserDaoImpl implements UserDao {

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public List<User> findAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver"); //注册驱动
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gjsyoung?useSSL=false", "root", "ch595200");//获得连接
        preparedStatement = conn.prepareStatement("SELECT * FROM USER ");   //获得预执行对象
        resultSet = preparedStatement.executeQuery();       //执行查询

        List<User> users = new ArrayList<User>();           //处理结果集
        User user = null;
        while (resultSet.next()){
            user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;
    }
}
package com.gjsyoung.jdbcTemplate;

import com.gjsyoung.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding.Use;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cairuojin
 * @create 2019-05-06 22:53
 */
@Repository
public class JdbcUserRepository {
    private JdbcOperations jdbcOperations;
    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final String QUERY_STRING = "SELECT * FROM USER ";
    private final String INSERT_STRING = "insert into USER (username, password) values( ? , ? )";
    private final String UPDATE_STRING = "update user set username = :username , password = :password where id = :id";
    @Autowired
    public JdbcUserRepository(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    //操作方法
    public List<User> selectAll(){
        List<Map<String, Object>> maps = jdbcOperations.queryForList(QUERY_STRING);
        List<User> query = jdbcOperations.query(QUERY_STRING, new UserRowMapper());
        return query;
    }
    public int addUser(User user){
        int update = jdbcOperations.update(INSERT_STRING, user.getUsername(), user.getPassword());
        return update;
    }
    public int update(User user){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("username",user.getUsername());
        paramMap.put("password",user.getPassword());
        paramMap.put("id",user.getId());
        int update = namedParameterJdbcOperations.update(UPDATE_STRING, paramMap);
        return update;
    }

    //手动映射对象
    private static final class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setPassword(resultSet.getString("password"));
            user.setUsername(resultSet.getString("username"));
            return user;
        }
    }
}
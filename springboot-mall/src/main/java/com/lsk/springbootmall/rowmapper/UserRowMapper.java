package com.lsk.springbootmall.rowmapper;

import com.lsk.springbootmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user=new User();
        user.setUserid(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getDate("created_date"));
        user.setLastModifiedDate(rs.getDate("last_modified_date"));
        return user;
    }

}

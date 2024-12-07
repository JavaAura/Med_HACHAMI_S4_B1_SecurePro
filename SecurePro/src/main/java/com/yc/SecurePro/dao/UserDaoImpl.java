package com.yc.SecurePro.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yc.SecurePro.dao.interfaces.IUserDao;
import com.yc.SecurePro.dto.res.UserDto;


@Repository
public class UserDaoImpl implements IUserDao {
    
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UserDto> findAllUsers() {
        String sql = """
            SELECT u.username, a.authority
            FROM users u
            LEFT JOIN authorities a ON u.username = a.username
        """;

        Map<String, List<String>> userRolesMap = new HashMap<>();

        jdbcTemplate.query(sql, rs -> {
            String username = rs.getString("username");
            String authority = rs.getString("authority");

            userRolesMap.computeIfAbsent(username, k -> new ArrayList<>()).add(authority);
        });

        return userRolesMap.entrySet().stream()
            .map(entry -> new UserDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    @Override
    public void updateUserRoles(String username, List<String> roles) {
        String deleteRolesSql = "DELETE FROM authorities WHERE username = ?";
        String insertRoleSql = "INSERT INTO authorities (username, authority) VALUES (?, ?)";

        jdbcTemplate.update(deleteRolesSql, username);

        for (String role : roles) {
            jdbcTemplate.update(insertRoleSql, username, role);
        }
    }

    // @Override
    // public String getUsernameById(Long userId) {
    //     String sql = "SELECT username FROM users WHERE id = ?";
        
    //     return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
    // }
    
}

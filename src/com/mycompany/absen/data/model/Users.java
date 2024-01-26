/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.absen.data.model;

import java.sql.*;

/**
 *
 * @author atha.dhaiffathin
 */
public class Users {
    private Integer id;  
    private String username; 
    private String password;
    private String role;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
    
    
    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Users mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Users entity = new Users();
        entity.setId(resultSet.getInt("id"));
        entity.setUsername(resultSet.getString("username"));
        entity.setPassword(resultSet.getString("password"));
        entity.setRole(resultSet.getString("role"));

        return entity;
    }
}

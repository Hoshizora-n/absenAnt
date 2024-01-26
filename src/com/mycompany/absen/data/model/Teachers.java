/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.absen.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author atha.dhaiffathin
 */
public class Teachers {
    private long Id;
    private long userId;
    private String name;
    private String nidn;
    private String number;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Teachers(long Id, long userId, String name, String nidn, String number) {
        this.Id = Id;
        this.userId = userId;
        this.name = name;
        this.nidn = nidn;
        this.number = number;
    }
    
    public static Teachers mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Teachers entity = new Teachers(
           resultSet.getLong("id"),
           resultSet.getLong("user_id"),
           resultSet.getString("name"),
           resultSet.getString("nidn"),
           resultSet.getString("number")
        );

        return entity;
    }
}

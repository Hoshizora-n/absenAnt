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
public class Students {
    private long Id;
    private long userId;
    private String name;
    private String nim;
    private String password;
    private String number;
    private long semesterId;
    private String semesterName;
    private long prodiId;
    private String prodiName;

    public Students(long Id, long userId, String name, String nim, String number, long semesterId, String semesterName, long prodiId, String prodiName) {
        this.Id = Id;
        this.userId = userId;
        this.name = name;
        this.nim = nim;
        this.number = number;
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.prodiId = prodiId;
        this.prodiName = prodiName;
    }

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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public long getProdiId() {
        return prodiId;
    }

    public void setProdiId(long prodiId) {
        this.prodiId = prodiId;
    }

    public String getProdiName() {
        return prodiName;
    }

    public void setProdiName(String prodiName) {
        this.prodiName = prodiName;
    }

    @Override
    public String toString() {
        return "Students{" + "Id=" + Id + ", userId=" + userId + ", name=" + name + ", nim=" + nim + ", number=" + number + ", semesterId=" + semesterId + ", semesterName=" + semesterName + ", prodiId=" + prodiId + ", prodiName=" + prodiName + '}';
    }

    public Students(long Id) {
        this.Id = Id;
    }
    
    public static Students mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Students entity = new Students(
           resultSet.getLong("id"),
           resultSet.getLong("user_id"),
           resultSet.getString("name"),
           resultSet.getString("nim"),
           resultSet.getString("number"),
           resultSet.getLong("semester_id"),
           resultSet.getString("semester_name"),
           resultSet.getLong("prodi_id"),
           resultSet.getString("prodi")
        );

        return entity;
    }
    
    public static Students mapResultSetToEntityOnlyStudents(ResultSet resultSet) throws SQLException {
        Students entity = new Students(
           resultSet.getLong("id"),
           resultSet.getLong("user_id"),
           resultSet.getString("name"),
           resultSet.getString("nim"),
           resultSet.getString("number"),
           0,
           null,
           0,
           null
        );

        return entity;
    }
}

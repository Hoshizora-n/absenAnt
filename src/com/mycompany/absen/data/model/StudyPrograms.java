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
public class StudyPrograms {
    private Integer id;  
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static StudyPrograms mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        StudyPrograms entity = new StudyPrograms();
        entity.setId(resultSet.getInt("id"));
        entity.setName(resultSet.getString("name"));

        return entity;
    }
}

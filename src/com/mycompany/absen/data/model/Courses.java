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
public class Courses {
    private long Id;
    private long teacherId;
    private String name;
    private String time;
    private String schedule;
    private String day;
    private long semesterId;
    private String semesterName;
    private long prodiId;
    private String prodiName;
    private String teacherName;

    public Courses(long Id, long teacherId, String name, String time, String schedule, String day, long semesterId, String semesterName, long prodiId, String prodiName, String teacherName) {
        this.Id = Id;
        this.teacherId = teacherId;
        this.name = name;
        this.time = time;
        this.schedule = schedule;
        this.day = day;
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.prodiId = prodiId;
        this.prodiName = prodiName;
        this.teacherName = teacherName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    public static Courses mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Courses entity = new Courses(
           resultSet.getLong("id"),
           resultSet.getLong("teacher_id"),
           resultSet.getString("name"),
           resultSet.getString("time"),
           resultSet.getString("schedule"),
           resultSet.getString("day"),
           resultSet.getLong("semester_id"),
           resultSet.getString("semester_name"),
           resultSet.getLong("study_id"),
           resultSet.getString("prodi"),
           null
        );

        return entity;
    }
    
    public static Courses mapResultSetToEntityOnlyCoursesAndTeacher(ResultSet resultSet) throws SQLException {
        Courses entity = new Courses(
           resultSet.getLong("id"),
           resultSet.getLong("teacher_id"),
           resultSet.getString("name"),
           resultSet.getString("time"),
           resultSet.getString("schedule"),
           resultSet.getString("day"),
           resultSet.getLong("semester_id"),
           null,
           resultSet.getLong("study_id"),
           null,
           resultSet.getString("teacher_name")
        );

        return entity;
    }

    @Override
    public String toString() {
        return "Courses{" + "Id=" + Id + ", teacherId=" + teacherId + ", name=" + name + ", time=" + time + ", schedule=" + schedule + ", day=" + day + ", semesterId=" + semesterId + ", semesterName=" + semesterName + ", prodiId=" + prodiId + ", prodiName=" + prodiName + '}';
    }
    
    
}

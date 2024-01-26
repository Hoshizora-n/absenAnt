/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.absen.data;

/**
 *
 * @author atha.dhaiffathin
 */
public class Global {
    private static Global instance;
    
    private long UserId;
    private long StudentId;
    private long TeacherId;

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long UserId) {
        this.UserId = UserId;
    }

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long StudentId) {
        this.StudentId = StudentId;
    }

    public long getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(long TeacherId) {
        this.TeacherId = TeacherId;
    }    
    
    private Global() {
    }

    // Public method to get the instance of the class
    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }
}

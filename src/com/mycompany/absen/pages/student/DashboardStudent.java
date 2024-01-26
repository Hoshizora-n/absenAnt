/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.absen.pages.student;

import com.mycompany.absen.data.Global;
import com.mycompany.absen.data.Koneksi;
import com.mycompany.absen.data.model.Courses;
import com.mycompany.absen.data.model.Semesters;
import com.mycompany.absen.data.model.Students;
import com.mycompany.absen.data.model.StudyPrograms;
import com.mycompany.absen.pages.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author atha.dhaiffathin
 */
public class DashboardStudent extends javax.swing.JFrame {
    
    Students student = null;
    boolean activeCourse = false;
    Courses course = null;
    int meetInt = 0;

    /**
     * Creates new form Dashboard
     */
    public DashboardStudent() {
        initComponents();
        initData();
        getCourse();
    }
    
    private void initData() {
        Koneksi koneksi = Koneksi.getInstance();

        try (Connection connection = koneksi.getConnection()) {
            String teachQuery = "select * from students s where user_id = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(teachQuery)) {
                preparedStatement.setLong(1, Global.getInstance().getUserId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        student = new Students(resultSet.getLong("id"));
                        student.setSemesterId(resultSet.getLong("semester_id"));
                        student.setProdiId(resultSet.getLong("study_id"));
                        Global.getInstance().setStudentId(student.getId());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void setData() {
        if (course != null) {
            courseFoundLabel.setText("");
            
            courseName.setText(course.getName());
            courseTeacher.setText(course.getTeacherName());
            
            LocalDate currentDate = LocalDate.now();

            // Define a formatter for the desired pattern
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", Locale.ENGLISH);

            // Format the current date as a string
            String formattedDate = currentDate.format(formatter);
            String formattedDateNow = currentDate.format(formatter1);

            courseDate.setText(formattedDate);
            courseTime.setText(course.getTime());
            
            String startDate = course.getSchedule().substring(0, 10);
            String endDate = formattedDateNow;
            
            int countDay = countDayOccurrences(parseDate(startDate), parseDate(endDate), course.getDay());
            
            String meetNo = "Pertemuan " + countDay;
            
            meetInt = countDay;
            
            meetName.setText(meetNo);
            
            absenBtn.setVisible(true);
        } else {
            courseFoundLabel.setText("No course found!");
            
            courseName.setText("");
            courseTeacher.setText("");
            
            courseDate.setText("");
            courseTime.setText("");
            
            meetName.setText("");
            
            absenBtn.setVisible(false);
        }
    }
    
    private void getCourse() {
        course = null;
        
        long studentId = student.getId();
        final long semId = student.getSemesterId();
        final long prodiId = student.getProdiId();
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the day of the week
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        
        LocalTime currentTime = LocalTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Format the current time as a string
        String time = currentTime.format(formatter);
        
        String Query = "SELECT *, t.name as teacher_name FROM courses c INNER JOIN teachers t ON c.teacher_id = t.id WHERE LOWER(day) = LOWER(?) AND semester_id = ? AND study_id = ? AND STR_TO_DATE('20/01/2024', '%d/%m/%Y') BETWEEN DATE_FORMAT(STR_TO_DATE(SUBSTRING_INDEX(schedule, '-', 1), '%d/%m/%Y'), '%Y-%m-%d') AND DATE_FORMAT(STR_TO_DATE(SUBSTRING_INDEX(schedule, '-', -1), '%d/%m/%Y'), '%Y-%m-%d') AND ? BETWEEN TIME(SUBSTRING(`time`, 1, 5)) AND TIME(SUBSTRING(`time`, -5)) LIMIT 1";
        
        Koneksi koneksi = Koneksi.getInstance();

        try (Connection connection = koneksi.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query)) {
                preparedStatement.setString(1, dayOfWeek.toString());
                preparedStatement.setLong(2, semId);
                preparedStatement.setLong(3, prodiId);
                preparedStatement.setString(4, time);
                

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        activeCourse = true;
                        course = Courses.mapResultSetToEntityOnlyCoursesAndTeacher(resultSet);
                    }
                }
            }
            
            setData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private static int countDayOccurrences(LocalDate startDate, LocalDate endDate, String targetDay) {
        DayOfWeek targetDayOfWeek = DayOfWeek.valueOf(targetDay.toUpperCase());
        int occurrences = 0;

        while (!startDate.isAfter(endDate)) {
            if (startDate.getDayOfWeek() == targetDayOfWeek) {
                occurrences++;
            }
            startDate = startDate.plusDays(1);
        }

        return occurrences;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        courseName = new javax.swing.JLabel();
        meetName = new javax.swing.JLabel();
        courseDate = new javax.swing.JLabel();
        absenBtn = new javax.swing.JButton();
        courseTeacher = new javax.swing.JLabel();
        courseTime = new javax.swing.JLabel();
        courseFoundLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        logoutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        courseName.setText("Algoritma Dan Struktur Data");

        meetName.setText("Pertemuan 14");

        courseDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        courseDate.setText("Rabu, 10/01/2024");

        absenBtn.setText("ABSEN");
        absenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                absenBtnActionPerformed(evt);
            }
        });

        courseTeacher.setText("Pak Akbar");

        courseTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        courseTime.setText("19:00 - 21:00");

        courseFoundLabel.setText("No course found");
        courseFoundLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jMenu2.setText("Profile");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        logoutMenu.setText("Logout");
        logoutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(logoutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseFoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(courseTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseTime, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(absenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(meetName, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(courseFoundLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(courseName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetName)
                    .addComponent(courseDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseTime)
                    .addComponent(courseTeacher))
                .addGap(18, 18, 18)
                .addComponent(absenBtn)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void absenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_absenBtnActionPerformed
        Koneksi koneksi = Koneksi.getInstance();
        
        // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Get the current time
            LocalTime currentTime = LocalTime.now();

            // Combine date and time to create LocalDateTime
            LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);

            // Define the date-time formatter for the desired pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Format the current date and time
            String formattedDateTime = currentDateTime.format(formatter);


        try (Connection connection = koneksi.getConnection()) {
            boolean found = false;
            String query = "SELECT id from history where student_id = ? and course_id = ? and meet_no = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, student.getId());
                preparedStatement.setLong(2, course.getId());
                preparedStatement.setInt(3, meetInt);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        found = true;
                    }
                }
            }
            
            if (found) {
                JOptionPane.showMessageDialog(null, "already absen", "Absen", JOptionPane.ERROR_MESSAGE);
            } else {
                query = "INSERT INTO history (student_id, course_id, meet_no, time) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, student.getId());
                    preparedStatement.setLong(2, course.getId());
                    preparedStatement.setInt(3, meetInt);
                    preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
                    
                    // Use executeUpdate instead of executeQuery
                    int rowsAffected = preparedStatement.executeUpdate();

                    // Check if the insertion was successful (rowsAffected > 0)
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "success absen", "Absen", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_absenBtnActionPerformed

    private void logoutMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMenuMouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login page = new Login();
                page.setVisible(true);
                page.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_logoutMenuMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Profile dialog = new Profile(DashboardStudent.this, true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getCourse();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton absenBtn;
    private javax.swing.JLabel courseDate;
    private javax.swing.JLabel courseFoundLabel;
    private javax.swing.JLabel courseName;
    private javax.swing.JLabel courseTeacher;
    private javax.swing.JLabel courseTime;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu logoutMenu;
    private javax.swing.JLabel meetName;
    // End of variables declaration//GEN-END:variables
}

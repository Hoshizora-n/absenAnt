/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.absen.pages.admin;

import com.mycompany.absen.data.Koneksi;
import com.mycompany.absen.data.model.Semesters;
import com.mycompany.absen.data.model.Students;
import com.mycompany.absen.data.model.StudyPrograms;
import com.mycompany.absen.pages.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author atha.dhaiffathin
 */
public class Student extends javax.swing.JFrame {
    
    private DefaultTableModel tableModel;
    private Students SelectedStudents;
    private ArrayList<Semesters> semesters;
    private ArrayList<StudyPrograms> studyPrograms;

    /**
     * Creates new form Student
     */
    public Student() {
        initComponents();
        tableModel = (DefaultTableModel) studentTable.getModel();
        
        semesters = new ArrayList<>();
        studyPrograms = new ArrayList<>();
        setTable();
        getDataTable();
        initData();
    }
    
    private void setTable() {
        int userIdColumnIndex = 1;  // Adjust this index based on your actual table structure
        int semesterIdColumnIndex = 5;
        int prodiIdColumnIndex = 7;

        studentTable.getColumnModel().getColumn(userIdColumnIndex).setMinWidth(0);
        studentTable.getColumnModel().getColumn(userIdColumnIndex).setMaxWidth(0);
        studentTable.getColumnModel().getColumn(userIdColumnIndex).setWidth(0);

        studentTable.getColumnModel().getColumn(semesterIdColumnIndex).setMinWidth(0);
        studentTable.getColumnModel().getColumn(semesterIdColumnIndex).setMaxWidth(0);
        studentTable.getColumnModel().getColumn(semesterIdColumnIndex).setWidth(0);

        studentTable.getColumnModel().getColumn(prodiIdColumnIndex).setMinWidth(0);
        studentTable.getColumnModel().getColumn(prodiIdColumnIndex).setMaxWidth(0);
        studentTable.getColumnModel().getColumn(prodiIdColumnIndex).setWidth(0);
        
        studentTable.getColumnModel().getColumn(0).setMaxWidth(35);
        studentTable.getColumnModel().getColumn(6).setMinWidth(150);

        // Refresh the table
        tableModel.fireTableDataChanged();
    }
    
    private void deleteStudent() {
        Koneksi koneksi = Koneksi.getInstance();

            try (Connection conn = koneksi.getConnection()) {
                String query = "DELETE FROM users WHERE id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setLong(1, SelectedStudents.getUserId());

                    // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Delete users successful!");
                    } else {
                        throw new SQLException("Failed to delete users");
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Success delete student with id " + SelectedStudents.getId(), "Create students", JOptionPane.INFORMATION_MESSAGE);
                getDataTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Exception", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }
    
    private void getDataTable() {
        Koneksi koneksi = Koneksi.getInstance();
        
        tableModel.setRowCount(0);
        
        try (Connection connection = koneksi.getConnection()) {
            String query = "select s.*, sem.id as semester_id, sem.name as semester_name, sp.id as prodi_id, sp.name as prodi from students s inner join semesters sem on s.semester_id = sem.id inner join study_programs sp on s.study_id = sp.id order by s.id asc ;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                
                Students student = null;
                while (resultSet.next()) {
                    student = Students.mapResultSetToEntity(resultSet);
                    tableModel.addRow(new Object[]{
                        student.getId(),
                        student.getUserId(),
                        student.getName(),
                        student.getNim(),
                        student.getNumber(),
                        student.getSemesterId(),
                        student.getSemesterName(),
                        student.getProdiId(),
                        student.getProdiName()
                    });
                }
                
                tableModel.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void initData() {
        Koneksi koneksi = Koneksi.getInstance();

        try (Connection connection = koneksi.getConnection()) {
            String query = "SELECT * FROM semesters";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                
                Semesters semester = null;
                while (resultSet.next()) {
                    semester = Semesters.mapResultSetToEntity(resultSet);
                    semesters.add(semester);
                }
            }
            
            String prodiQuery = "SELECT * FROM study_programs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(prodiQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                
                StudyPrograms prodi = null;
                while (resultSet.next()) {
                    prodi = StudyPrograms.mapResultSetToEntity(resultSet);
                    studyPrograms.add(prodi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        addStudentBtn = new javax.swing.JButton();
        EditStudent = new javax.swing.JButton();
        DeleteStudent = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        dashboardMenu = new javax.swing.JMenu();
        teacherMenu = new javax.swing.JMenu();
        logoutMenu = new javax.swing.JMenu();

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Students");

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USER ID", "NAME", "NIM", "NUMBER", "SEMESTER_ID", "SEMESTER", "PRODI_ID", "PRODI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentTable);
        if (studentTable.getColumnModel().getColumnCount() > 0) {
            studentTable.getColumnModel().getColumn(0).setResizable(false);
            studentTable.getColumnModel().getColumn(1).setResizable(false);
            studentTable.getColumnModel().getColumn(5).setResizable(false);
            studentTable.getColumnModel().getColumn(7).setResizable(false);
        }

        addStudentBtn.setText("Add");
        addStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentBtnActionPerformed(evt);
            }
        });

        EditStudent.setText("Edit");
        EditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditStudentActionPerformed(evt);
            }
        });

        DeleteStudent.setText("Delete");
        DeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteStudentActionPerformed(evt);
            }
        });

        dashboardMenu.setText("Dashboard");
        dashboardMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMenuMouseClicked(evt);
            }
        });
        dashboardMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardMenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(dashboardMenu);

        teacherMenu.setText("Teacher");
        teacherMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(teacherMenu);

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addStudentBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(addStudentBtn)
                    .addComponent(EditStudent)
                    .addComponent(DeleteStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentBtnActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddStudentDialog dialog = new AddStudentDialog(Student.this, true, semesters, studyPrograms);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_addStudentBtnActionPerformed

    private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseClicked

        // Get the selected row index
        int rowIndex = studentTable.getSelectedRow();

        if (rowIndex != -1) {
            // Assuming the order of columns is: ID, USER ID, NAME, NIM, NUMBER, SEMESTER_ID, SEMESTER, PRODI_ID, PRODI
            long id = (long) tableModel.getValueAt(rowIndex, 0);
            long userId = (long) tableModel.getValueAt(rowIndex, 1);
            String name = (String) tableModel.getValueAt(rowIndex, 2);
            String nim = (String) tableModel.getValueAt(rowIndex, 3);
            String number = (String) tableModel.getValueAt(rowIndex, 4);
            long semesterId = (long) tableModel.getValueAt(rowIndex, 5);
            String semesterName = (String) tableModel.getValueAt(rowIndex, 6);
            long prodiId = (long) tableModel.getValueAt(rowIndex, 7);
            String prodiName = (String) tableModel.getValueAt(rowIndex, 8);

            // Create a Students object
            Students selectedStudent = new Students(id, userId, name, nim, number, semesterId, semesterName, prodiId, prodiName);
            
            SelectedStudents = selectedStudent;
        }
    }//GEN-LAST:event_studentTableMouseClicked

    private void EditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditStudentActionPerformed
        if (SelectedStudents == null) {
            JOptionPane.showMessageDialog(null, "select data to edit", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditStudentDialog dialog = new EditStudentDialog(Student.this, true, semesters, studyPrograms, SelectedStudents);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_EditStudentActionPerformed

    private void DeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteStudentActionPerformed
        if (SelectedStudents == null) {
            JOptionPane.showMessageDialog(null, "select data to delete", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(null, "are you sure to delete student " + SelectedStudents.getId(), "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == 0) {
            deleteStudent();
        }
    }//GEN-LAST:event_DeleteStudentActionPerformed

    private void dashboardMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardMenuActionPerformed
//        this.dispose();
        System.out.println("hi");
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Dashboard dashboard = new Dashboard();
//                dashboard.setVisible(true);
//                dashboard.setLocationRelativeTo(null);
//            }
//        });
    }//GEN-LAST:event_dashboardMenuActionPerformed

    private void dashboardMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMenuMouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dashboard.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_dashboardMenuMouseClicked

    private void teacherMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherMenuMouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Teacher page = new Teacher();
                page.setVisible(true);
                page.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_teacherMenuMouseClicked

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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteStudent;
    private javax.swing.JButton EditStudent;
    private javax.swing.JButton addStudentBtn;
    private javax.swing.JMenu dashboardMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu logoutMenu;
    private javax.swing.JTable studentTable;
    private javax.swing.JMenu teacherMenu;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.absen.pages.admin;

import com.mycompany.absen.data.Koneksi;
import com.mycompany.absen.data.model.Students;
import com.mycompany.absen.data.model.Teachers;
import com.mycompany.absen.pages.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author atha.dhaiffathin
 */
public class Teacher extends javax.swing.JFrame {
    
    private DefaultTableModel tableModel;
    private Teachers SelectedTeacher;

    /**
     * Creates new form Teacher
     */
    public Teacher() {
        initComponents();
        tableModel = (DefaultTableModel) teacherTable.getModel();
        
        setTable();
        getDataTable();
    }
    
    private void deleteTeacher() {
        Koneksi koneksi = Koneksi.getInstance();

            try (Connection conn = koneksi.getConnection()) {
                String query = "DELETE FROM users WHERE id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setLong(1, SelectedTeacher.getUserId());

                    // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Delete users successful!");
                    } else {
                        throw new SQLException("Failed to delete users");
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Success delete student with id " + SelectedTeacher.getId(), "Delete teachers", JOptionPane.INFORMATION_MESSAGE);
                getDataTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Exception", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }
    
    private void setTable() {
        int userIdColumnIndex = 1;  // Adjust this index based on your actual table structure

        teacherTable.getColumnModel().getColumn(userIdColumnIndex).setMinWidth(0);
        teacherTable.getColumnModel().getColumn(userIdColumnIndex).setMaxWidth(0);
        teacherTable.getColumnModel().getColumn(userIdColumnIndex).setWidth(0);
        
        teacherTable.getColumnModel().getColumn(0).setMaxWidth(35);

        // Refresh the table
        tableModel.fireTableDataChanged();
    }
    
    private void getDataTable() {
        Koneksi koneksi = Koneksi.getInstance();
        
        tableModel.setRowCount(0);
        
        try (Connection connection = koneksi.getConnection()) {
            String query = "select t.* from teachers t order by t.id asc ;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                
                Teachers teacher = null;
                while (resultSet.next()) {
                    teacher = Teachers.mapResultSetToEntity(resultSet);
                    tableModel.addRow(new Object[]{
                        teacher.getId(),
                        teacher.getUserId(),
                        teacher.getName(),
                        teacher.getNidn(),
                        teacher.getNumber()
                    });
                }
                
                tableModel.fireTableDataChanged();
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        addTeacher = new javax.swing.JButton();
        editTeacher = new javax.swing.JButton();
        deleteTeacher = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        teacherTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Teachers");

        addTeacher.setText("Add");
        addTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTeacherActionPerformed(evt);
            }
        });

        editTeacher.setText("Edit");
        editTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTeacherActionPerformed(evt);
            }
        });

        deleteTeacher.setText("Delete");
        deleteTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTeacherActionPerformed(evt);
            }
        });

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USER ID", "NAME", "NIDN", "NUMBER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(teacherTable);
        if (teacherTable.getColumnModel().getColumnCount() > 0) {
            teacherTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jMenu1.setText("Dashboard");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Student");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu5.setText("Logout");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(addTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(addTeacher)
                    .addComponent(editTeacher)
                    .addComponent(deleteTeacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dashboard.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Student student = new Student();
                student.setVisible(true);
                student.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login page = new Login();
                page.setVisible(true);
                page.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_jMenu5MouseClicked

    private void addTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTeacherActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTeacherDialog dialog = new AddTeacherDialog(Teacher.this, true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_addTeacherActionPerformed

    private void deleteTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTeacherActionPerformed
        if (SelectedTeacher == null) {
            JOptionPane.showMessageDialog(null, "select data to delete", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(null, "are you sure to delete teacher " + SelectedTeacher.getId(), "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == 0) {
            deleteTeacher();
        }
    }//GEN-LAST:event_deleteTeacherActionPerformed

    private void teacherTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherTableMouseClicked
        // Get the selected row index
        int rowIndex = teacherTable.getSelectedRow();

        if (rowIndex != -1) {
            // Assuming the order of columns is: ID, USER ID, NAME, NIDN, NUMBER
            long id = (long) tableModel.getValueAt(rowIndex, 0);
            long userId = (long) tableModel.getValueAt(rowIndex, 1);
            String name = (String) tableModel.getValueAt(rowIndex, 2);
            String nim = (String) tableModel.getValueAt(rowIndex, 3);
            String number = (String) tableModel.getValueAt(rowIndex, 4);

            // Create a Students object
            Teachers selectedTeacher = new Teachers(id, userId, name, nim, number);
            
            SelectedTeacher = selectedTeacher;
        }
    }//GEN-LAST:event_teacherTableMouseClicked

    private void editTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTeacherActionPerformed
        if (SelectedTeacher == null) {
            JOptionPane.showMessageDialog(null, "select data to edit", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditTeacherDialog dialog = new EditTeacherDialog(Teacher.this, true, SelectedTeacher);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_editTeacherActionPerformed

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
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTeacher;
    private javax.swing.JButton deleteTeacher;
    private javax.swing.JButton editTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable teacherTable;
    // End of variables declaration//GEN-END:variables
}
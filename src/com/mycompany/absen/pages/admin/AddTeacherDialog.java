/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.absen.pages.admin;

import com.mycompany.absen.data.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author atha.dhaiffathin
 */
public class AddTeacherDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddTeacherDialog
     */
    public AddTeacherDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        nidnInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JTextField();
        Add = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        numberInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Add Teacher");

        jLabel2.setText("Name :");

        jLabel3.setText("NIDN :");

        jLabel4.setText("Password :");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel5.setText("Number :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameInput)
                            .addComponent(nidnInput)
                            .addComponent(passwordInput)
                            .addComponent(numberInput, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nidnInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(numberInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Add)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        final String name = nameInput.getText();
        final String nidn = nidnInput.getText();
        String number = numberInput.getText();
        String password = passwordInput.getText();
        
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "name can't empty", "validation error", JOptionPane.WARNING_MESSAGE);
        } else if (nidn.equals("")) {
            JOptionPane.showMessageDialog(null, "nim can't empty", "validation error", JOptionPane.WARNING_MESSAGE);
        } else if (number.equals("")) {
            JOptionPane.showMessageDialog(null, "number can't empty", "validation error", JOptionPane.WARNING_MESSAGE);
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "password can't empty", "validation error", JOptionPane.WARNING_MESSAGE);
        } else {
            Koneksi koneksi = Koneksi.getInstance();

            try (Connection conn = koneksi.getConnection()) {
                String query = "INSERT INTO users (username, password, role) VALUES (?, ?, 'teacher')";

                long userId = 0;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, nidn);
                    preparedStatement.setString(2, password);

                    // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Insert users successful!");
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                long lastInsertedId = generatedKeys.getLong(1);
                                userId = lastInsertedId;
                                System.out.println("Last users inserted ID: " + lastInsertedId);
                            } else {
                                throw new SQLException("Failed to retrieve last users inserted ID.");
                            }
                        }
                    } else {
                        throw new SQLException("Failed to insert users");
                    }
                }
                
                query = "INSERT INTO teachers (user_id, name, nidn, number) VALUES (?, ?, ?, ?)";

                long teacherId = 0;
                try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setLong(1, userId);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, nidn);
                    preparedStatement.setString(4, number);

                    // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Insert teachers successful!");
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                long lastInsertedId = generatedKeys.getLong(1);
                                teacherId = lastInsertedId;
                                System.out.println("Last teachers inserted ID: " + lastInsertedId);
                            } else {
                                throw new SQLException("Failed to retrieve teachers last inserted ID.");
                            }
                        }
                    } else {
                        throw new SQLException("Failed to insert teachers");
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Success create teacher with id " + teacherId, "Create teachers", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Exception", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_AddActionPerformed

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
            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTeacherDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTeacherDialog dialog = new AddTeacherDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nameInput;
    private javax.swing.JTextField nidnInput;
    private javax.swing.JTextField numberInput;
    private javax.swing.JTextField passwordInput;
    // End of variables declaration//GEN-END:variables
}
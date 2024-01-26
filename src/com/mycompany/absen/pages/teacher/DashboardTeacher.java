/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.absen.pages.teacher;

import com.mycompany.absen.data.Global;
import com.mycompany.absen.data.Koneksi;
import com.mycompany.absen.data.model.Courses;
import com.mycompany.absen.data.model.Semesters;
import com.mycompany.absen.data.model.StudyPrograms;
import com.mycompany.absen.data.model.Teachers;
import com.mycompany.absen.pages.Login;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author atha.dhaiffathin
 */
public class DashboardTeacher extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private Courses SelectedCourses;
    private ArrayList<Semesters> semesters = new ArrayList<>();
    private ArrayList<StudyPrograms> studyPrograms = new ArrayList<>();

    /**
     * Creates new form Dashboard
     */
    public DashboardTeacher() {
        initComponents();
        tableModel = (DefaultTableModel) courseTable.getModel();
        setTable();
        initData();
        getDataTable();
    }
    
    private void getDataTable() {
        Koneksi koneksi = Koneksi.getInstance();

        final long teacherId = Global.getInstance().getTeacherId();

        tableModel.setRowCount(0);

        try (Connection connection = koneksi.getConnection()) {
            String query = "select c.*, sem.name as semester_name, sp.name as prodi from courses c inner join semesters sem on c.semester_id = sem.id inner join study_programs sp on c.study_id = sp.id where c.teacher_id = ? order by c.id asc;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, teacherId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Courses course = null;
                    while (resultSet.next()) {
                        course = Courses.mapResultSetToEntity(resultSet);
                        tableModel.addRow(new Object[]{
                            course.getId(),
                            course.getTeacherId(),
                            course.getName(),
                            course.getTime(),
                            course.getSchedule(),
                            course.getDay(),
                            course.getSemesterName(),
                            course.getProdiName(),
                            course.getSemesterId(),
                            course.getProdiId()
                        });
                    }

                    tableModel.fireTableDataChanged();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTable() {
        int teacherIdColumnIndex = 1;  // Adjust this index based on your actual table structure
        int semesterIdColumnIndex = 8;
        int prodiIdColumnIndex = 9;

        courseTable.getColumnModel().getColumn(teacherIdColumnIndex).setMinWidth(0);
        courseTable.getColumnModel().getColumn(teacherIdColumnIndex).setMaxWidth(0);
        courseTable.getColumnModel().getColumn(teacherIdColumnIndex).setWidth(0);

        courseTable.getColumnModel().getColumn(semesterIdColumnIndex).setMinWidth(0);
        courseTable.getColumnModel().getColumn(semesterIdColumnIndex).setMaxWidth(0);
        courseTable.getColumnModel().getColumn(semesterIdColumnIndex).setWidth(0);

        courseTable.getColumnModel().getColumn(prodiIdColumnIndex).setMinWidth(0);
        courseTable.getColumnModel().getColumn(prodiIdColumnIndex).setMaxWidth(0);
        courseTable.getColumnModel().getColumn(prodiIdColumnIndex).setWidth(0);
        
        courseTable.getColumnModel().getColumn(0).setMaxWidth(35);
        courseTable.getColumnModel().getColumn(6).setMinWidth(150);

        // Refresh the table
        tableModel.fireTableDataChanged();
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
            
            String teachQuery = "select * from teachers t where user_id = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(teachQuery)) {
                preparedStatement.setLong(1, Global.getInstance().getUserId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Global.getInstance().setTeacherId(Teachers.mapResultSetToEntity(resultSet).getId());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteStudent() {
        Koneksi koneksi = Koneksi.getInstance();

            try (Connection conn = koneksi.getConnection()) {
                String query = "DELETE FROM courses WHERE id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setLong(1, SelectedCourses.getId());

                    // Use executeUpdate() for INSERT, UPDATE, or DELETE statements
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Delete courses successful!");
                    } else {
                        throw new SQLException("Failed to delete courses");
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Success delete course with id " + SelectedCourses.getId(), "Delete courses", JOptionPane.INFORMATION_MESSAGE);
                getDataTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Exception", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        download = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        profileMenu = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TEACHER_ID", "NAME", "TIME", "SCHEDULE", "DAY", "SEMESTER", "PRODI", "SEMESTER_ID", "PRODI_ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(courseTable);

        jLabel1.setText("Courses");

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        download.setText("Download");
        download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadActionPerformed(evt);
            }
        });

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        profileMenu.setText("Profile");
        profileMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(profileMenu);

        jMenu2.setText("Logout");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(download, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(add)
                    .addComponent(download)
                    .addComponent(edit)
                    .addComponent(delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCourse dialog = new AddCourse(DashboardTeacher.this, true, semesters, studyPrograms);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_addActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        if (SelectedCourses == null) {
            JOptionPane.showMessageDialog(null, "select data to update", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditCourse dialog = new EditCourse(DashboardTeacher.this, true, semesters, studyPrograms, SelectedCourses);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                
                getDataTable();
            }
        });
    }//GEN-LAST:event_editActionPerformed

    private void profileMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMenuMouseClicked
        java.awt.EventQueue.invokeLater(() -> {
            Profile dialog = new Profile(DashboardTeacher.this, true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_profileMenuMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        this.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            Login page = new Login();
            page.setVisible(true);
            page.setLocationRelativeTo(null);
        });
    }//GEN-LAST:event_jMenu2MouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (SelectedCourses == null) {
            JOptionPane.showMessageDialog(null, "select data to delete", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(null, "are you sure to delete course " + SelectedCourses.getId(), "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == 0) {
            deleteStudent();
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void courseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseTableMouseClicked
        // Get the selected row index
        int rowIndex = courseTable.getSelectedRow();

        if (rowIndex != -1) {
            // Assuming the order of columns is: ID, USER ID, NAME, NIM, NUMBER, SEMESTER_ID, SEMESTER, PRODI_ID, PRODI
            long id = (long) tableModel.getValueAt(rowIndex, 0);
            long teacherId = (long) tableModel.getValueAt(rowIndex, 1);
            String name = (String) tableModel.getValueAt(rowIndex, 2);
            String time = (String) tableModel.getValueAt(rowIndex, 3);
            String schedule = (String) tableModel.getValueAt(rowIndex, 4);
            String day = (String) tableModel.getValueAt(rowIndex, 5);
            String semesterName = (String) tableModel.getValueAt(rowIndex, 6);
            String prodiName = (String) tableModel.getValueAt(rowIndex, 7);
            long semesterId = (long) tableModel.getValueAt(rowIndex, 8);
            long prodiId = (long) tableModel.getValueAt(rowIndex, 9);

            // Create a Students object
            Courses selectedCourse = new Courses(id, teacherId, name, time, schedule, day, semesterId, semesterName, prodiId, prodiName, null);
            
            SelectedCourses = selectedCourse;
        }
    }//GEN-LAST:event_courseTableMouseClicked

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
    
    private static String getFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
        fileChooser.setSelectedFile(new File("output.xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    private Map<String, Map<Integer, String>> fetchDataFromHistory(Connection connection) throws SQLException {
        Map<String, Map<Integer, String>> data = new TreeMap<>();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT meet_no, s.name as student_name, time FROM history h INNER JOIN students s ON s.id = h.student_id ORDER BY h.student_id, h.meet_no";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int meetNo = resultSet.getInt("meet_no");
                    String studentName = resultSet.getString("student_name");
                    String time = resultSet.getString("time");

                    data.putIfAbsent(studentName, new TreeMap<>());
                    data.get(studentName).put(meetNo, time);
                }
            }
        }

        return data;
    }

    private void generateExcel(Map<String, Map<Integer, String>> data, String outputPath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("History");
        
        String startDate = SelectedCourses.getSchedule().substring(0, 10);
        String endDate = SelectedCourses.getSchedule().substring(11);
            
        int countDay = countDayOccurrences(parseDate(startDate), parseDate(endDate), SelectedCourses.getDay());

        int rowIdx = 0;

        // Create header row with meet_no values
        Row headerRow = sheet.createRow(rowIdx++);
        
        for (int i = 1; i <= countDay; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue("Pertemuan " + i);
        }

        // Create rows for each student_id
        for (Map.Entry<String, Map<Integer, String>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entry.getKey());

            for (Map.Entry<Integer, String> meetEntry : entry.getValue().entrySet()) {
                Cell cell = row.createCell(meetEntry.getKey());
                cell.setCellValue(meetEntry.getValue());
            }
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }
    
    private void downloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadActionPerformed
        if (SelectedCourses == null) {
            JOptionPane.showMessageDialog(null, "select data to download report", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Koneksi koneksi = Koneksi.getInstance();
        try (Connection connection = koneksi.getConnection()) {
            // Assuming you have a method to fetch data from the history table
            Map<String, Map<Integer, String>> data = fetchDataFromHistory(connection);

            // Get the file path from the user
            String outputPath = getFilePath();

            if (outputPath != null) {
                generateExcel(data, outputPath);
                JOptionPane.showMessageDialog(null, "Excel file generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error Generate Report", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_downloadActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton delete;
    private javax.swing.JButton download;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu profileMenu;
    // End of variables declaration//GEN-END:variables
}

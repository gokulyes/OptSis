/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.dialog;

import com.gokul.optsis.MainWindow;
import java.awt.Frame;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Gokul WPC
 */
public class StrategyListDialog extends javax.swing.JDialog {
    
    private Connection connection ;
    private DefaultTableModel ListTableModel;
    private MainWindow mainWindow;

    /**
     * Creates new form StrategyListDialog
     */
    public StrategyListDialog(java.awt.Frame parent, boolean modal, MainWindow window) {
        super(parent, modal);
        
       
        initComponents();
        this.mainWindow = window;
        
        ListTableModel = (DefaultTableModel) tblStrgList.getModel();
        showTableData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDiagHeader = new javax.swing.JPanel();
        pnlDiagFooter = new javax.swing.JPanel();
        pnlTable = new javax.swing.JPanel();
        btnStgSelect = new javax.swing.JButton();
        btnDigClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStrgList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Strategy Select");

        pnlDiagHeader.setBackground(new java.awt.Color(129, 152, 48));

        javax.swing.GroupLayout pnlDiagHeaderLayout = new javax.swing.GroupLayout(pnlDiagHeader);
        pnlDiagHeader.setLayout(pnlDiagHeaderLayout);
        pnlDiagHeaderLayout.setHorizontalGroup(
            pnlDiagHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlDiagHeaderLayout.setVerticalGroup(
            pnlDiagHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(pnlDiagHeader, java.awt.BorderLayout.PAGE_START);

        pnlDiagFooter.setBackground(new java.awt.Color(129, 152, 48));
        pnlDiagFooter.setPreferredSize(new java.awt.Dimension(400, 25));

        javax.swing.GroupLayout pnlDiagFooterLayout = new javax.swing.GroupLayout(pnlDiagFooter);
        pnlDiagFooter.setLayout(pnlDiagFooterLayout);
        pnlDiagFooterLayout.setHorizontalGroup(
            pnlDiagFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlDiagFooterLayout.setVerticalGroup(
            pnlDiagFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        getContentPane().add(pnlDiagFooter, java.awt.BorderLayout.PAGE_END);

        btnStgSelect.setText("Select");
        btnStgSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStgSelectMouseClicked(evt);
            }
        });

        btnDigClose.setText("Close");
        btnDigClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDigCloseMouseClicked(evt);
            }
        });

        tblStrgList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Strategy"
            }
        ));
        tblStrgList.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblStrgList);
        tblStrgList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStgSelect)
                .addGap(126, 126, 126))
            .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTableLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(btnDigClose)
                    .addContainerGap(223, Short.MAX_VALUE)))
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnStgSelect)
                .addGap(123, 123, 123))
            .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                    .addContainerGap(242, Short.MAX_VALUE)
                    .addComponent(btnDigClose)
                    .addGap(124, 124, 124)))
        );

        getContentPane().add(pnlTable, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDigCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDigCloseMouseClicked
        dispose();
    }//GEN-LAST:event_btnDigCloseMouseClicked

    private void btnStgSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStgSelectMouseClicked
        int iSelectedRow = tblStrgList.getSelectedRow();
        mainWindow.selectStrategy(ListTableModel.getValueAt(iSelectedRow, 0).toString());
//        MainWindow.selectStrategy(ListTableModel.getValueAt(iSelectedRow, 0).toString());
    }//GEN-LAST:event_btnStgSelectMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StrategyListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StrategyListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StrategyListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StrategyListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                StrategyListDialog dialog = new StrategyListDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDigClose;
    private javax.swing.JButton btnStgSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDiagFooter;
    private javax.swing.JPanel pnlDiagHeader;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblStrgList;
    // End of variables declaration//GEN-END:variables

    private void showTableData() {
        
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nColumnCnt = 1; //

        String strSQL =  "Select distinct(name) from STRATEGY"; // Get all Distinct Strategy names from table.

        try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {

                    Object rowData[] = new Object[nColumnCnt] ;

                    while(rs.next()) {

                            rowData[0] = rs.getString(1);
                            ListTableModel.addRow(rowData);
                            ListTableModel.fireTableDataChanged();
                            System.out.print( "\n" + "Name: " + rowData[0]); 

                    }

                }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.);
//            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        } 
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
//
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }  
        System.out.print( "\n" + "showTableData: Finish" ); 
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

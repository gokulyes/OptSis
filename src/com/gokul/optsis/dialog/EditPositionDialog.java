/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.dialog;

import com.gokul.optsis.model.OptionLeg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gokul WPC
 */
public class EditPositionDialog extends javax.swing.JDialog {

    private Connection connection ;
    private int iID =1;
    /**
     * Creates new form EditPositionDialog
     */
    public EditPositionDialog(java.awt.Frame parent, boolean modal, int iID) {
        super(parent, modal);
        initComponents();
        this.iID = iID;
        
        showStgShowTableData(this.iID);
    }
  
    public void showStgShowTableData(int iID) {
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nColumnCnt = 1; //
//        String strStrategyName = "";

        String strSQL =  "Select * from STRATEGY where id =" + iID ;

        try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {


                    if(rs.next()) {

//                        strStrategyName = rs.getString(2);
                        OptionLeg objOptionLeg = new OptionLeg(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5), rs.getBoolean(6), rs.getInt(7));
                        
                        txtID.setText(Integer.toString(objOptionLeg.getID()));
                        txtName.setText(objOptionLeg.getStrategyName());
                        txtSymbol.setText(objOptionLeg.getSymbol());
                        if(objOptionLeg.getPosition()){
                            rdbtnLong.setSelected(true);
                        } else {
                            rdbtnShort.setSelected(true);
                        }
                        txtPrice.setText(Integer.toString(objOptionLeg.getnPrice()));
                        if(objOptionLeg.getPostionCover()){
                            chkCovered.setSelected(true);
                        } else {
                            chkCovered.setSelected(false);
                        }
                        txtCPrice.setText(Integer.toString(objOptionLeg.getPostionCoverPrice()));
//                        System.out.print("\n ID: " + rs.getInt(1) + " Name: " +  rs.getString(2) + " Symbol: "+ rs.getString(3) + " Position: " + rs.getBoolean(4));

                    }

                }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());

            ex.printStackTrace();
        } 
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

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
            
    }
   public void updateCoverPrice(int iID, int cPrice) {
       
        PreparedStatement stmt = null;

        String strSQL =  "update STRATEGY set coverprice = " + cPrice + ",covered = true where id =" + iID ;

        try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                
                if (stmt.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Cover price updated");
                }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } 
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
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

        btnGroup = new javax.swing.ButtonGroup();
        pnlHeader = new javax.swing.JPanel();
        pnlFooter = new javax.swing.JPanel();
        pnlContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSymbol = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPrice = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        rdbtnLong = new javax.swing.JRadioButton();
        rdbtnShort = new javax.swing.JRadioButton();
        chkCovered = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Position");

        pnlHeader.setBackground(new java.awt.Color(129, 152, 48));

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlFooter.setBackground(new java.awt.Color(129, 152, 48));
        pnlFooter.setPreferredSize(new java.awt.Dimension(400, 25));

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFooter, java.awt.BorderLayout.PAGE_END);

        pnlContent.setMinimumSize(new java.awt.Dimension(330, 290));
        pnlContent.setPreferredSize(new java.awt.Dimension(350, 300));
        pnlContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID");
        pnlContent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        txtID.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtID.setEnabled(false);
        pnlContent.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 130, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name");
        pnlContent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        txtName.setEnabled(false);
        pnlContent.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 130, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Symbol");
        pnlContent.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        txtSymbol.setEnabled(false);
        pnlContent.add(txtSymbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 130, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Position");
        pnlContent.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrice.setEnabled(false);
        pnlContent.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Price");
        pnlContent.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Cover Price");
        pnlContent.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        txtCPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCPrice.setEnabled(false);
        pnlContent.add(txtCPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, -1));

        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 23));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        pnlContent.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

        btnClose.setText("Close");
        btnClose.setPreferredSize(new java.awt.Dimension(80, 23));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });
        pnlContent.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        btnGroup.add(rdbtnLong);
        rdbtnLong.setSelected(true);
        rdbtnLong.setText("Long");
        rdbtnLong.setEnabled(false);
        pnlContent.add(rdbtnLong, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        btnGroup.add(rdbtnShort);
        rdbtnShort.setText("Short");
        rdbtnShort.setEnabled(false);
        pnlContent.add(rdbtnShort, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        chkCovered.setText("Covered");
        chkCovered.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCoveredMouseClicked(evt);
            }
        });
        pnlContent.add(chkCovered, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.activeCaption));
        pnlContent.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 168, 250, -1));

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void chkCoveredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCoveredMouseClicked
       if(chkCovered.isSelected()) {
           txtCPrice.setEnabled(true);
       } else {
           txtCPrice.setEnabled(false);
       }
    }//GEN-LAST:event_chkCoveredMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        updateCoverPrice(this.iID, Integer.parseInt(txtCPrice.getText()));
    }//GEN-LAST:event_btnUpdateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkCovered;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JRadioButton rdbtnLong;
    private javax.swing.JRadioButton rdbtnShort;
    private javax.swing.JTextField txtCPrice;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSymbol;
    // End of variables declaration//GEN-END:variables
}
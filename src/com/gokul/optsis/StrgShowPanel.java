/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis;

import com.gokul.optsis.model.StrategyTableModel;

import com.gokul.optsis.MainWindow;
import com.gokul.optsis.dialog.EditPositionDialog;
import com.gokul.optsis.model.OptionLeg;
import com.gokul.optsis.model.OptionStrategy;
import java.awt.Frame;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Gokul WPC
 */
public class StrgShowPanel extends javax.swing.JPanel {

    private Connection connection ;
    private MainWindow mainWindow;   
    private StrategyTableModel strategyTableModel = new StrategyTableModel();
    private OptionStrategy objOptionStrategy = new OptionStrategy();
    
    /**
     * Creates new form StgyShowPanel
     */
    public StrgShowPanel(OptionStrategy obj) {
        initComponents();
        
        this.objOptionStrategy = obj;
        
        initPnlShow();
//        getOptionStrategyData(stgName);
//        showStgShowTableData(stgName);
//        showChart(stgName);
        showStgShowTableData();
        showChart();
    }
    
    public void initPnlShow() {
        strategyTableModel = (StrategyTableModel) tblStgShow.getModel();
    }
//    
//    public void getOptionStrategyData(String strStgName) {
//      PreparedStatement stmt = null;
//        ResultSet rs = null;
//        String strSQL =  "Select * from STRATEGY where name ='" + strStgName + "'";
//        objOptionStrategy = new OptionStrategy();
//        OptionLeg objOptionLeg;
//        
//         try {
//                Class.forName("org.h2.Driver");
//                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//                stmt = connection.prepareStatement(strSQL);
//                rs = stmt.executeQuery();
//                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
//                
//                if(rs != null) {
//                    objOptionStrategy.setStrName(strStgName);
//                    while(rs.next()) {
//                        objOptionLeg = new OptionLeg();
//                        objOptionLeg.setID(rs.getInt(1));
//                        objOptionLeg.setStrategyName(rs.getNString(2));
//                        objOptionLeg.setSymbol(rs.getString(3));
//                        objOptionLeg.setPosition(rs.getBoolean(4));
//                        objOptionLeg.setPrice(rs.getInt(5));
//                        objOptionLeg.setPostionCovered(rs.getBoolean(6));
//                        objOptionLeg.setPostionCoverPrice(rs.getInt(7));
//                        objOptionStrategy.setOptLeg(objOptionLeg);
////                      objOptionStrategy.setOptLeg(new OptionLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  
//
//                    }
//
//                }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
////            System.out.println("SQLState: " + ex.);
////            System.out.println("VendorError: " + ex.getErrorCode());
//            ex.printStackTrace();
//        } 
//        finally {
//            // it is a good idea to release
//            // resources in a finally{} block
//            // in reverse-order of their creation
//            // if they are no-longer needed
////
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException sqlEx) { } // ignore
//
//                rs = null;
//            }
//
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException sqlEx) { } // ignore
//
//                stmt = null;
//            }
//        }
//    }

    public void showChart () {

        pnlChart.removeAll();
        pnlChart.add(getLineChart(objOptionStrategy.getPayOffData()));
        pnlChart.revalidate();
        pnlChart.repaint();	
    } 
//    
//    public void showChart (String strStgName) {
//
//        pnlChart.removeAll();
//        pnlChart.add(getLineChart(objOptionStrategy.getPayOffData()));
//        pnlChart.revalidate();
//        pnlChart.repaint();	
//    } 
    
    private  ChartPanel getLineChart(List<Integer> list) {
            JFreeChart lineChart = ChartFactory.createXYLineChart(
                     "PayOff",
                     "Spot price","P/L",
                     createDataset(list) ,
                     PlotOrientation.VERTICAL,
                     true,true,false);	

            ChartPanel chartPanel = new ChartPanel( lineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 880, 600 ) );
            chartPanel.setVisible(true);

            return chartPanel;

    }    

    private  XYDataset createDataset(List<Integer> list) {

            var series = new XYSeries("Net");
                    int nUnderlyingPrice = 8000;

                    for (Integer element : list) {
                            series.add(nUnderlyingPrice, element.doubleValue());
                            nUnderlyingPrice += 100;
                    }	        

            var dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            return dataset;	 

     }	    
    public void showStgShowTableData() {
 
        for (OptionLeg objOptionLeg : objOptionStrategy.getListOptLeg()) { // For each OptionLeg in the list
            strategyTableModel.addRowData(objOptionLeg);
        }
        strategyTableModel.fireTableDataChanged();    
         
    }        
  
//    public void showStgShowTableData(String strStgName) {
// 
//        for (OptionLeg objOptionLeg : objOptionStrategy.getListOptLeg()) { // For each OptionLeg in the list
//            strategyTableModel.addRowData(objOptionLeg);
//        }
//        strategyTableModel.fireTableDataChanged();    
//         
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStgShow = new javax.swing.JTable();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 32767));
        pnlChart = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1200, 715));
        setPreferredSize(new java.awt.Dimension(1200, 715));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filler1.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 480, 320));

        tblStgShow.setModel(strategyTableModel);
        tblStgShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStgShowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStgShow);
        // set the column width for each column
        tblStgShow.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblStgShow.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblStgShow.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblStgShow.getColumnModel().getColumn(3).setPreferredWidth(10);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 300));

        filler2.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 900, 590));
        add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 880, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void tblStgShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStgShowMouseClicked
       
        int iSelectedRow = tblStgShow.getSelectedRow();
        int id = strategyTableModel.getRowData(iSelectedRow).getID(); // Get seleected row ID.

//        System.out.print("\nmouseClicked : " + StgTableModel.getRowData(iSelectedRow).toString());// StgTableModel.getValueAt(iSelectedRow, 0));

        EditPositionDialog epd = new EditPositionDialog(null, true, id);
       epd.setLocationRelativeTo(null);
       epd.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_tblStgShowMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JTable tblStgShow;
    // End of variables declaration//GEN-END:variables
}

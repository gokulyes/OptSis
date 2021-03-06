/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.panel;

import com.gokul.optsis.MainWindow;
import com.gokul.optsis.model.StrategyTableModel;

import com.gokul.optsis.dialog.EditPositionDialog;
import com.gokul.optsis.model.OptionLeg;
import com.gokul.optsis.model.OptionStrategy;
import com.gokul.optsis.model.StrategyPayOffTableModel;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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
    private StrategyPayOffTableModel strategyPayOffTableModel = new StrategyPayOffTableModel();
    
    /**
     * Creates new form StgyShowPanel
     */
    public StrgShowPanel(MainWindow window) {
        initComponents();
        
        this.mainWindow = window;
        
        initPnlShow();

        showStgShowTableData();
        showChart();
        showStgPayoffTabelData();
    }
    
    private void initPnlShow() {
        strategyTableModel = (StrategyTableModel) tblStgShow.getModel();
        strategyPayOffTableModel = (StrategyPayOffTableModel) tblStgPayoff.getModel();
        tblStgPayoff.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                int status = (int)tblStgPayoff.getModel().getValueAt(row, 1);
                if (status >= 0) {
                    setBackground(new Color(129, 152, 48));
                    setForeground(Color.WHITE);
                } else {
                    setBackground(new Color(192, 204, 153));
                    setForeground(table.getForeground());
                }       
                return this;
            }   
        });        
    }


    private void showChart () {

        pnlChart.removeAll();
        pnlChart.add(getLineChart());        
        pnlChart.revalidate();
        pnlChart.repaint();	
    } 
    
    private  ChartPanel getLineChart() {
            JFreeChart lineChart = ChartFactory.createXYLineChart(
                     "PayOff",
                     "Spot price","P/L",
                     createDataset() ,
                     PlotOrientation.VERTICAL,
                     true,true,false);	

            ChartPanel chartPanel = new ChartPanel( lineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 590, 500 ) );
            chartPanel.setVisible(true);

            return chartPanel;

    }    

    private  XYDataset createDataset() {
        
        List<Integer> listNet = mainWindow.getCurrentStrategy().getNetPayOffData();
        List<Integer> listCurrent = mainWindow.getCurrentStrategy().getCurrentPayOffData();

        XYSeries seriesNet = new XYSeries("Net");
        int nUnderlyingPrice = mainWindow.getCurrentStrategy().getChartStart();

        for (Integer element : listNet) {
            seriesNet.add(nUnderlyingPrice, element.doubleValue());
            nUnderlyingPrice += 100;
        }

        nUnderlyingPrice = mainWindow.getCurrentStrategy().getChartStart();
        XYSeries seriesCurrent = new XYSeries("Current ");

        for (Integer element : listCurrent) {
            seriesCurrent.add(nUnderlyingPrice, element.doubleValue());
            nUnderlyingPrice += 100;
        }                

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesCurrent);
        dataset.addSeries(seriesNet);

        return dataset;	 

     }	    
    private void showStgShowTableData() {
 
        for (OptionLeg objOptionLeg : mainWindow.getCurrentStrategy().getCurrentLstOptLeg()) { // For each OptionLeg in the list
            strategyTableModel.addRowData(objOptionLeg);
        }
        strategyTableModel.fireTableDataChanged();    
         
    }  
    private void showStgPayoffTabelData() {
        strategyPayOffTableModel.setPlData(mainWindow.getCurrentStrategy().getChartStart(), mainWindow.getCurrentStrategy().getChartEnd(), mainWindow.getCurrentStrategy().getCurrentPayOffData());
    }
     
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStgPayoff = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 32767));

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
        add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 610, 520));
        add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 590, 500));

        tblStgPayoff.setModel(strategyPayOffTableModel);
        jScrollPane2.setViewportView(tblStgPayoff);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 20, 220, 800));

        filler3.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        add(filler3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 240, 820));
    }// </editor-fold>//GEN-END:initComponents

    private void tblStgShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStgShowMouseClicked
       
        int iSelectedRow = tblStgShow.getSelectedRow();
        int id = strategyTableModel.getRowData(iSelectedRow).getID(); // Get seleected row ID.

        EditPositionDialog epd = new EditPositionDialog(null, true, this.mainWindow, strategyTableModel.getRowData(iSelectedRow));
        epd.setLocationRelativeTo(null);
        epd.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_tblStgShowMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JTable tblStgPayoff;
    private javax.swing.JTable tblStgShow;
    // End of variables declaration//GEN-END:variables
}

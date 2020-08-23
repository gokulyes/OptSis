/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest;

import com.gokul.optsis.MainWindow;
import com.gokul.optsis.backtest.dialog.AddNewLegDialog;
import com.gokul.optsis.backtest.model.BackTestLeg;
import com.gokul.optsis.backtest.model.BackTestStrategy;
import com.gokul.optsis.backtest.model.PositionTableModel;
import com.gokul.optsis.backtest.model.OptLeg;
import com.gokul.optsis.backtest.model.OptStrg;
import com.gokul.optsis.backtest.model.OptionChain;
import com.gokul.optsis.backtest.model.OptionPrice;
import com.gokul.optsis.model.OptionChainTableModel;
import com.gokul.optsis.util.Util;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
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
public class StrgBackTestPanel extends javax.swing.JPanel {
    
    private PositionTableModel positionTableModel = new PositionTableModel();
    private OptionChainTableModel optionChainTableModel = new OptionChainTableModel();
    private OptStrg objOptStrg =  new OptStrg();
    private OptionChain optionChain = new OptionChain();
    private MainWindow mainwindow;
    private Date dtBackTest;
    private BackTestStrategy backTestStrategy = new BackTestStrategy();

    /**
     * Creates new form StrgBackTestPanel
     */
    public StrgBackTestPanel(MainWindow window) {
       
        initComponents();
        this.mainwindow = window;
        
        initPnl();
        showPositionTableData();
        showOptionChainTableData();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        dtPicker = new com.toedter.calendar.JDateChooser();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 0), new java.awt.Dimension(300, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPosition = new javax.swing.JTable(positionTableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                if (col == 9) {
                    Float obj =(Float)positionTableModel.getValueAt(row, col);
                    if(obj > 100 && col == 9) {
                        comp.setBackground(new Color(129, 152, 48));
                    }
                } else {
                    comp.setBackground(new Color(192, 204, 153));
                }

                return comp;
            }
        };
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 0), new java.awt.Dimension(500, 32767));
        pnlChart = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOptionChain = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1800, 715));
        setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(129, 152, 48));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1800, 109));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Strategy Back Test");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_foot_20px.png"))); // NOI18N
        jButton1.setText("Add Leg");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_database_import_20px.png"))); // NOI18N
        jButton2.setText("import data");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        dtPicker.setDateFormatString("yyyy-MM-dd");
        dtPicker.setPreferredSize(new java.awt.Dimension(111, 29));
        dtPicker.setDate(new Date());
        dtPicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtPickerPropertyChange(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_next_20px.png"))); // NOI18N
        btnNext.setMnemonic('N');
        btnNext.setText("Next");
        btnNext.setPreferredSize(new java.awt.Dimension(111, 29));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_previous_20px.png"))); // NOI18N
        btnPrevious.setMnemonic('P');
        btnPrevious.setText("Previous");
        btnPrevious.setPreferredSize(new java.awt.Dimension(111, 29));
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(dtPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(1142, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlContent.setPreferredSize(new java.awt.Dimension(1800, 601));
        pnlContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filler1.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        pnlContent.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 530, 590));

        tblPosition.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row =  e.getFirstRow();
                    updateCoverPrice(row);
                }
                //         System.out.println(e);
            }
        });
        tblPosition.setModel(positionTableModel);
        jScrollPane1.setViewportView(tblPosition);

        pnlContent.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 510, 430));

        filler2.setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        pnlContent.add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, 440));

        pnlChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlContent.add(pnlChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 480, 420));

        tblOptionChain.setModel(optionChainTableModel);
        jScrollPane2.setViewportView(tblOptionChain);

        pnlContent.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, -1, -1));

        add(pnlContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      AddNewLegDialog objAddNewLegDialog = new AddNewLegDialog(this.mainwindow , true, this.mainwindow);
      objAddNewLegDialog.setLocationRelativeTo(null);
      objAddNewLegDialog.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       Util.insertIntoHDataTable();
    }//GEN-LAST:event_jButton2MouseClicked

    private void dtPickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtPickerPropertyChange
        if ("date".equals(evt.getPropertyName())) {
//            System.out.println(evt.getPropertyName()
//                + ": " + (Date) evt.getNewValue());
            
            dtBackTest  = (Date) evt.getNewValue();
            showOptionChain();
            showBackTestSeries();
            showChart();
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_dtPickerPropertyChange

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        Calendar c = Calendar.getInstance(); 
        c.setTime(dtBackTest); 
        c.add(Calendar.DATE, 1);
        dtBackTest = c.getTime();  
        dtPicker.setDate(dtBackTest);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        Calendar c = Calendar.getInstance(); 
        c.setTime(dtBackTest); 
        c.add(Calendar.DATE, -1);
        dtBackTest = c.getTime();  
        dtPicker.setDate(dtBackTest);      // TODO add your handling code here:
    }//GEN-LAST:event_btnPreviousActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private com.toedter.calendar.JDateChooser dtPicker;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlChart;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblOptionChain;
    private javax.swing.JTable tblPosition;
    // End of variables declaration//GEN-END:variables

    private void initPnl() {
            objOptStrg = Util.getStrgTestData();
            positionTableModel = (PositionTableModel) tblPosition.getModel();
  
            showChart();

    }
    
    private void showOptionChain() {
            
            optionChain = Util.getHistoricData(dtPicker.getDate());
            optionChainTableModel = (OptionChainTableModel) tblOptionChain.getModel();

            showOptionChainTableData();
    
    }
    
    private void showPositionTableData() {
        for (OptLeg objOptLeg : objOptStrg.getListOptLeg()) { // For each OptLeg in the list
            positionTableModel.addRowData(objOptLeg);
        }
        positionTableModel.fireTableDataChanged();        
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
            chartPanel.setPreferredSize( new java.awt.Dimension( 400, 400 ) );
            chartPanel.setVisible(true);

            return chartPanel;

    }    

    private  XYDataset createDataset() {
        
        List<Float> listNet = objOptStrg.getNetPayOffData();
        List<Float> listCurrent = objOptStrg.getCurrentPayOffData();
        List<Float> listHistorical = backTestStrategy.getCurrentPayOffData();

        XYSeries seriesNet = new XYSeries("Net");
        int nUnderlyingPrice = objOptStrg.getChartStart();

        for (Float element : listNet) {
            seriesNet.add(nUnderlyingPrice, element.doubleValue());
            nUnderlyingPrice += 100;
        }

        nUnderlyingPrice = objOptStrg.getChartStart();
        XYSeries seriesCurrent = new XYSeries("Current ");

        for (Float element : listCurrent) {
            seriesCurrent.add(nUnderlyingPrice, element.doubleValue());
            nUnderlyingPrice += 100;
        } 
        
        nUnderlyingPrice = objOptStrg.getChartStart();
        XYSeries seriesHistorical = new XYSeries("Historical ");

        for (Float element : listHistorical) {
            seriesHistorical.add(nUnderlyingPrice, element.doubleValue());
            nUnderlyingPrice += 100;
        }         

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesCurrent);
        dataset.addSeries(seriesNet);
        dataset.addSeries(seriesHistorical);

        return dataset;	 

     }

    private void showOptionChainTableData() {
        optionChainTableModel.clearAllRows();
        
        for (OptionPrice objOptionPrice : optionChain.getListOptionPrice()) { // For each OptLeg in the list
            optionChainTableModel.addRowData(objOptionPrice);
        }
        optionChainTableModel.fireTableDataChanged();  
    }

    private void showBackTestSeries() {
        backTestStrategy = new BackTestStrategy();
        BackTestLeg backTestLeg =  new BackTestLeg();
            for (OptLeg objOptLeg : objOptStrg.getListOptLeg()) { // For each OptLeg in the list
                backTestLeg = Util.getHistoricPrice(dtBackTest, objOptLeg);

                if(backTestLeg != null) {
                    backTestStrategy.addRow(backTestLeg);
                    objOptStrg.setHistPrice(objOptLeg.getID(), backTestLeg.getPrice());
                }
                
            }
            

    }
    private void updateCoverPrice(int row) {
        objOptStrg.updateCurrentList();
        showChart();

    }
    
}

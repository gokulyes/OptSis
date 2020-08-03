/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.util;


import com.gokul.optsis.MainWindow;
import com.gokul.optsis.model.OptionLeg;
import com.gokul.optsis.model.OptionStrategy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Util {

  public static OptionStrategy getOptionStrategyData(String strStgName) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "Select * from STRATEGY where name ='" + strStgName + "'";
        OptionStrategy objOptionStrategy = new OptionStrategy();
        OptionLeg objOptionLeg;
        
         try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {
                    objOptionStrategy.setStrName(strStgName);
                    while(rs.next()) {
                        objOptionLeg = new OptionLeg();
                        objOptionLeg.setID(rs.getInt(1));
                        objOptionLeg.setStrategyName(rs.getNString(2));
                        objOptionLeg.setSymbol(rs.getString(3));
                        objOptionLeg.setPosition(rs.getBoolean(4));
                        objOptionLeg.setPrice(rs.getInt(5));
                        objOptionLeg.setPostionCovered(rs.getBoolean(6));
                        objOptionLeg.setPostionCoverPrice(rs.getInt(7));
                        objOptionStrategy.setOptLeg(objOptionLeg);
//                      objOptionStrategy.setOptLeg(new OptionLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  

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
        return objOptionStrategy;
    }   
  
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        
    	java.net.URL imgURL = MainWindow.class.getResource(path);
    	
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    
    public static ChartPanel getLineChart(List<Integer> list) {
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

     public static XYDataset createDataset(List<Integer> list) {

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

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.util;


import com.gokul.optsis.MainWindow;
import com.gokul.optsis.backtest.model.BackTestLeg;
import com.gokul.optsis.backtest.model.HData;
import com.gokul.optsis.backtest.model.HistoricalData;
import com.gokul.optsis.backtest.model.OptLeg;
import com.gokul.optsis.backtest.model.OptStrg;
import com.gokul.optsis.backtest.model.OptionChain;
import com.gokul.optsis.backtest.model.OptionPrice;
import com.gokul.optsis.model.OptionLeg;
import com.gokul.optsis.model.OptionStrategy;
import com.gokul.optsis.model.StrgSetting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Util {
    
    public static void createNewStrategyTable() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "CREATE TABLE STRATEGY (id int auto_increment, name varchar2(20), symbol varchar2(20), position boolean, price int, covered boolean, coverprice int)";
        
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            DatabaseMetaData dmd = connection.getMetaData();
            rs = dmd.getTables(null, null, "STRATEGY", null);
            
            if(rs.next()) { // Table exists do nothing.
                
            } else {
                stmt = connection.prepareStatement(strSQL);
                stmt.executeUpdate();
                System.out.print("\nStrategy Table created\n");                
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
    }	  
    
    public static void createNewStrategySettingTable() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "CREATE TABLE STRGSETTINGS (id int auto_increment, name varchar2(20), chartstart int, chartend int)";
        
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            DatabaseMetaData dmd = connection.getMetaData();
            rs = dmd.getTables(null, null, "STRGSETTINGS", null);
            
            if(rs.next()) { // Table exists do nothing.
                
            } else {
                stmt = connection.prepareStatement(strSQL);
                stmt.executeUpdate();
//                System.out.print("\n STRGSETTINGS Table created\n");                
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
    } 
    
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
                        objOptionLeg.setCovered(rs.getBoolean(6));
                        objOptionLeg.setCoverPrice(rs.getInt(7));
                        objOptionStrategy.addOptLeg(objOptionLeg);
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

    public static StrgSetting getStrgSettingData() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "Select * from STRGSETTINGS";
        StrgSetting objStrgSetting = null;
        
         try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {
                    while(rs.next()) {
                        objStrgSetting = new StrgSetting();
                        objStrgSetting.setID(rs.getInt(1));
                        objStrgSetting.setName(rs.getNString(2));
                        objStrgSetting.setChartStart(rs.getInt(3));
                        objStrgSetting.setChartEnd(rs.getInt(4));
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
        return objStrgSetting;
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
    public static void createNewStrgTestTable() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "CREATE TABLE STRGTEST (id int auto_increment, name varchar2(20),dtcreated date default CURRENT_DATE, symbol varchar2(20), position boolean, price int, covered boolean, dtcovered date default CURRENT_DATE, coverprice int)";
        
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            
            DatabaseMetaData dmd = connection.getMetaData();
            rs = dmd.getTables(null, null, "STRATEGY", null);
            
            if(rs.next()) { // Table exists do nothing.
                
            } else {
                stmt = connection.prepareStatement(strSQL);
                stmt.executeUpdate();
                System.out.print("\nSTRGTEST Table created\n");                
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
    }     

    public static OptStrg getStrgTestData() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String strSQL =  "Select * from STRGTEST";
        OptStrg objOptStrg = new OptStrg();
        OptLeg objOptLeg;
        
         try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {
                    objOptStrg.setStrName("BackTest");
                    while(rs.next()) {
                        objOptLeg = new OptLeg();
                        objOptLeg.setID(rs.getInt(1));
                        objOptLeg.setStrategyName(rs.getNString(2));
                        objOptLeg.setDateCreated((rs.getDate(3)));
                        objOptLeg.setSymbol(rs.getString(4));
                        objOptLeg.setPosition(rs.getBoolean(5));
                        objOptLeg.setPrice(rs.getInt(6));
                        objOptLeg.setCovered(rs.getBoolean(7));
                        objOptLeg.setDateCovered(rs.getDate(8));
                        objOptLeg.setCoverPrice(rs.getInt(9));
                        objOptStrg.addOptLeg(objOptLeg);
//                      objOptStrg.setOptLeg(new OptLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  

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
        return objOptStrg;
    } 
    
    public static OptionChain getHistoricData(Date date) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        java.sql.Date sd = new java.sql.Date(date.getTime());
        String strSQL =  "Select * from HISTORICDATA where date ='" + sd + "'";
        OptionChain optionChain = new OptionChain();
        OptionPrice optionPrice;
        
         try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {

                    while(rs.next()) {
                        optionPrice = new OptionPrice();
                        optionPrice.setCe(rs.getFloat(5));
                        optionPrice.setStrike(rs.getInt(4));
                        optionPrice.setPe(rs.getFloat(6));
   
                        optionChain.addRow(optionPrice);
//                      objOptStrg.setOptLeg(new OptLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  

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
        return optionChain;
    } 
    
    public static BackTestLeg getHistoricPrice(Date date, OptLeg optionLeg) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        java.sql.Date sd = new java.sql.Date(date.getTime());
        boolean type = optionLeg.getTypeBoolean();
        java.sql.Date ed = new java.sql.Date(optionLeg.getExpiry().getTime());
        int strike = optionLeg.getStrike();
        
        String strSQL =  "Select * from HISTORICDATA where date ='" + sd + "' and expiry = '" + ed + "' and strike =" + strike;

        BackTestLeg backTestLeg = null;
        
         try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(strSQL);
                rs = stmt.executeQuery();
                
//                System.out.print( "\n" + "strSQL: " + strSQL); 
                
                if(rs != null) {

                    while(rs.next()) {
                        backTestLeg = new BackTestLeg();
                        backTestLeg.setType(type);
                        backTestLeg.setPosition(optionLeg.getPosition());
                        backTestLeg.setStrike(strike);
                        if (!type) // False: Call, True: Put
                            backTestLeg.setPrice(rs.getFloat(5));
                        else
                            backTestLeg.setPrice(rs.getFloat(6));
   

//                      objOptStrg.setOptLeg(new OptLeg(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5)));  

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

        return backTestLeg;
    }    
    

    public static List<HistoricalData> importCSVtoDatabase() {
        String line = "";  
        String splitBy = ",";  
        List<HistoricalData> listhistoricalData = new ArrayList<>();
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Gokul WPC\\Downloads\\NTradeBook_2020 - Sheet4.csv"));  
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] data = line.split(splitBy);    // use comma as separator  
                listhistoricalData.add(new HistoricalData(data[0],data[1],data[2]));
                System.out.println("Date [Date=" + data[0] + ", Symbol=" + data[1] + ", price=" + data[2] +"]");  
            }  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }
        
        System.out.print("\nimportCSVtoDatabase");
        return listhistoricalData;
    }
    
    public static void insertIntoHistoricalTable() {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        int nColumnCnt = 4; //
        List<HistoricalData> HDataList = importCSVtoDatabase();
//      Create table  HISTORIC (id int auto_increment,date date, symbol varchar2(20), price float(2))  
        String insert_into_strategy = "insert into HISTORIC (date, symbol, price)values ( ?, ?, ?)";

        try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(insert_into_strategy);
                
                for(HistoricalData hData: HDataList) {
                    System.out.print("\n" + hData.toString());
                    stmt.setDate(1, new java.sql.Date(hData.getDate().getTime()));
                    stmt.setString(2, hData.getSymbol());
                    stmt.setFloat(3, hData.getPrice());  
                   
                    if (stmt.executeUpdate() > 0) {
//                           JOptionPane.showMessageDialog(null, "New Strategy name inserted into table");
                           Object rowData[] = new Object[nColumnCnt] ;
                           rowData[0] = hData.getDate().getTime();
                           rowData[1] = hData.getSymbol();
                           rowData[2] = hData.getPrice();

                           System.out.print("\n" + rowData.toString());
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
    //            if (rs != null) {
    //                try {
    //                    rs.close();
    //                } catch (SQLException sqlEx) { } // ignore
    //
    //                rs = null;
    //            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }        
    }

    public static List<HData> importCSVtoHDatabase() {
        String line = "";  
        String splitBy = ",";  
        List<HData> listhistoricalData = new ArrayList<>();
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Gokul WPC\\Downloads\\NTradeBook_2020 - Sheet5.csv"));  
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] data = line.split(splitBy);    // use comma as separator  
                listhistoricalData.add(new HData(data[0], data[1], data[2], data[3], data[4], data[5]));
                System.out.println("Date [Date=" + data[0] + ", Symbol=" + data[1] + ", price=" + data[2] +"]");  
            }  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }
        
        System.out.print("\nimportCSVtoHDatabase");
        return listhistoricalData;
    }
    
    public static void insertIntoHDataTable() {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        int nColumnCnt = 4; //
        List<HData> HDataList = importCSVtoHDatabase();
//      Create table  HISTORICDATA (symbol varchar2(20), date date, expiry date,strike int, ce float(2), pe float(2))  
        String insert_into_strategy = "insert into HISTORICDATA (symbol, date, expiry, strike, ce, pe)values ( ?, ?, ?, ?, ?, ?)";

        try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                stmt = connection.prepareStatement(insert_into_strategy);
                
                for(HData hData: HDataList) {
                    System.out.print("\n" + hData.toString());
                    stmt.setString(1, hData.getSymbol());
                    stmt.setDate(2, new java.sql.Date(hData.getDate().getTime()));
                    stmt.setDate(3, new java.sql.Date(hData.getExpiry().getTime()));
                    stmt.setInt(4, hData.getStrike());
                    stmt.setFloat(5, hData.getCe());  
                    stmt.setFloat(6, hData.getPe());  
                   
                    if (stmt.executeUpdate() > 0) {
//                           JOptionPane.showMessageDialog(null, "New Strategy name inserted into table");
                           Object rowData[] = new Object[nColumnCnt] ;
                           rowData[0] =  hData.getSymbol();
                           rowData[1] = hData.getDate().getTime();
                           rowData[2] = hData.getExpiry().getTime();

                           System.out.print("\n" + rowData.toString());
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
    //            if (rs != null) {
    //                try {
    //                    rs.close();
    //                } catch (SQLException sqlEx) { } // ignore
    //
    //                rs = null;
    //            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }        
    }    
}


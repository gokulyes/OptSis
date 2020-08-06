/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;

/**
 *
 * @author Gokul WPC
 */
public class StrgSetting {
    private int nId;
    private String strName; 				// Strategy name
    private int chartStart = 0;
    private int chartEnd = 0;    
    
    public StrgSetting() {
        
    }
    
    public StrgSetting(int id, String name, int start, int end) {
        this.nId = id;
        this.strName = name;
        this.chartStart = start;
        this.chartEnd = end;
    }    

    public int getID() {
        return nId;
    }

    public void setID(int nId) {
        this.nId = nId;
    }

    public String getName() {
        return strName;
    }

    public void setName(String strName) {
        this.strName = strName;
    }

    public int getChartStart() {
        return chartStart;
    }

    public void setChartStart(int chartStart) {
        this.chartStart = chartStart;
    }

    public int getChartEnd() {
        return chartEnd;
    }

    public void setChartEnd(int chartEnd) {
        this.chartEnd = chartEnd;
    }    
 
}

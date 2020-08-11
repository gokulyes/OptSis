/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gokul WPC
 */
public class BackTestStrategy {
    private String symbol;
    private Date date;
    private Date expiry;
    private List<BackTestLeg> listBackTestLeg = new ArrayList<>();  
    private int chartStart = 9000;
    private int chartEnd = 12000;
        

    public BackTestStrategy() {
    }

    public BackTestStrategy(String symbol, Date date, Date expiry) {
        this.symbol = symbol;
        this.date = date;
        this.expiry = expiry;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public List<BackTestLeg> getListBackTestLeg() {
        return listBackTestLeg;
    }

    public void setListBackTestLeg(List<BackTestLeg> listBackTestLeg) {
        this.listBackTestLeg = listBackTestLeg;
    }
    
    public void addRow(BackTestLeg leg) {
        leg.setChartStart(getChartStart());
        leg.setChartEnd(getChartEnd());        
        listBackTestLeg.add(leg);
    }
    
  public List<Float> getCurrentPayOffData() {

        boolean boolFirst = true;
        int nInnerCounter = 0;
        List<Float> listOLPayOff = new ArrayList<>();


        for (BackTestLeg outerElement : listBackTestLeg) {

                nInnerCounter = 0;
                for (Float intElement : outerElement.getPayOffData()) {

                        if (boolFirst == true) {
                                listOLPayOff.add(intElement);
                        } else {
                                listOLPayOff.set(nInnerCounter,  listOLPayOff.get(nInnerCounter) + intElement);
                        }
                        nInnerCounter++;
                }
                boolFirst = false;

        }

        return listOLPayOff;		

    }    

    private int getChartStart() {
        return this.chartStart;
    }

    private int getChartEnd() {
        return this.chartEnd;
    }
}

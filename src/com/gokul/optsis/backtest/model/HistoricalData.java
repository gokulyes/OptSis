/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gokul WPC
 */
public class HistoricalData {
    private Date date;
    private String symbol;
    private float price;

    @Override
    public String toString() {
        return "HistoricalData{" + "date=" + date + ", symbol=" + symbol + ", price=" + price + '}';
    }
    
    public HistoricalData() {
        
    }
    public HistoricalData(Date dt, String sym, int value) {
        this.date= dt;
        this.symbol = sym;
        this.price = value;
    } 
    public HistoricalData(String dt, String sym, String value) {

        try {
            this.date= new SimpleDateFormat("dd-MMM-yyyy").parse(dt);
            this.symbol = sym;
            this.price = Float.valueOf(value);//Integer.parseInt(value);
        } catch (ParseException ex) {
            Logger.getLogger(HistoricalData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public Date getDate() {
        return this.date;
    }
    public String getSymbol() {
        return this.symbol;
    }
    public float getPrice() {
        return this.price;
                
    }
    
}

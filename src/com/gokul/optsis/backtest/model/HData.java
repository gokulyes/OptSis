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
public class HData {
    private String symbol;
    private Date date;
    private Date expiry;
    private int strike;
    private float ce; // Call price
    private float pe; // Put price
    
    public HData() {
        
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

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public float getCe() {
        return ce;
    }

    public void setCe(float ce) {
        this.ce = ce;
    }

    public float getPe() {
        return pe;
    }

    public void setPe(float pe) {
        this.pe = pe;
    }

    public HData(String symbol, Date date, Date expiry, int strike, float ce, float pe) {
        this.symbol = symbol;
        this.date = date;
        this.expiry = expiry;
        this.strike = strike;
        this.ce = ce;
        this.pe = pe;
    }
    
    public HData(String symbol, String date, String expiry, String strike, String ce, String pe) {
        try {
            this.symbol = symbol;
            this.date = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
            this.expiry = new SimpleDateFormat("dd-MMM-yyyy").parse(expiry);
            this.strike = Integer.parseInt(strike);
            this.ce = Float.valueOf(ce);
            this.pe = Float.valueOf(pe);
        } catch (ParseException ex) {
            Logger.getLogger(HData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

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
public class OptionChain {
    private String symbol;
    private Date date;
    private Date expiry;
    private List<OptionPrice> listOptionPrice = new ArrayList<>();
    
    public void clearAllRows() {
        listOptionPrice.clear();
    }
    
    public void addRow(OptionPrice oprice) {
        listOptionPrice.add(oprice);
    }
    
    public OptionPrice getRow(int row) {
        return listOptionPrice.get(row);
    }
    
    public int getSize() {
        return listOptionPrice.size();
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

    public List<OptionPrice> getListOptionPrice() {
        return listOptionPrice;
    }

    public void setListOptionPrice(List<OptionPrice> listOptionPrice) {
        this.listOptionPrice = listOptionPrice;
    }

    public OptionChain(String symbol, Date date, Date expiry) {
        this.symbol = symbol;
        this.date = date;
        this.expiry = expiry;
    }

    public OptionChain() {
    }
    
}

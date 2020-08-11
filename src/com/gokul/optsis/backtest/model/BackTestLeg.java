/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gokul WPC
 */
public class BackTestLeg {
    private boolean type; // CE: False, PE: True
    private boolean position;  // Long: True, Short: False
    private int strike;
    private float price;
    private int chartStart = 0;
    private int chartEnd = 0;    

    public BackTestLeg() {
    }

    public BackTestLeg(boolean type, boolean position, int strike, float price) {
        this.type = type;
        this.position = position;
        this.strike = strike;
        this.price = price;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public List<Float> getPayOffData() {
        List<Float> listOLPayOff = new ArrayList<>();

        for(int nUnderlying = chartStart; nUnderlying <= chartEnd; nUnderlying+=100) {
                if(! isType()) { // False: Call, True: Put
                        if (isPosition()) { // Long ------ 		true: Long, False: Short 
                                if (nUnderlying > getStrike()) { // In the Money
                                        listOLPayOff.add(nUnderlying - getStrike() - getPrice());
                                } else { // Out of the Money
                                        listOLPayOff.add(- getPrice());
                                }


                        } else if (!isPosition()) { //Short
                                if (nUnderlying > getStrike()) { // In the Money
                                        listOLPayOff.add(-(nUnderlying - getStrike() - getPrice()));
                                } else { // Out of the Money
                                        listOLPayOff.add(getPrice());
                                }

                        }

                } else { // Put
                        if (isPosition()) { // Long
                                if (nUnderlying < getStrike()) { // In the Money
                                        listOLPayOff.add( getStrike() - nUnderlying - getPrice());
                                } else { // Out of the Money
                                        listOLPayOff.add(- getPrice());
                                }


                        } else if (!isPosition()) { //Short
                                if (nUnderlying < getStrike()) { // In the Money
                                        listOLPayOff.add(-(getStrike() - nUnderlying - getPrice()));
                                } else { // Out of the Money
                                        listOLPayOff.add(getPrice());
                                }	

                        }

                }
        }			

        return listOLPayOff;
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

    @Override
    public String toString() {
        return "BackTestLeg{" + "type=" + type + ", position=" + position + ", strike=" + strike + ", price=" + price + '}';
    }
    
    
    
}

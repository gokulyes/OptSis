/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

/**
 *
 * @author Gokul WPC
 */
public class OptionPrice {
    private int strike;
    private float ce;
    private float pe;

    @Override
    public String toString() {
        return "OptionPrice{" + "strike=" + strike + ", ce=" + ce + ", pe=" + pe + '}';
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

    public OptionPrice(int strike, float ce, float pe) {
        this.strike = strike;
        this.ce = ce;
        this.pe = pe;
    }

    public OptionPrice() {
    }
    
    
}

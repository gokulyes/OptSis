/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class OptionLeg {
	
	@Override
	public String toString() {
		return "OptionLeg [nId=" + nId + ", strName=" + strName + ", strSymbol=" + strSymbol + ", bPosition="
				+ bPosition + ", nPrice=" + nPrice + ", bCovered=" + bCovered + ", iCoverPrice=" + iCoverPrice + "]";
	}


	private int nId;
	private String strName; 				// Strategy name
	private String strSymbol;				// Option Symbol
	private boolean bPosition = true;		// Long: true, Short: false
	private int nPrice;
	private boolean bCovered = false;		// Position covered?
	private int iCoverPrice = 0;			// Position covered at price.
        private int chartStart = 0;
        private int chartEnd = 0;
	
	public OptionLeg () {
		
	}

	
	public OptionLeg(String strSym, boolean bPosition, int nPrice) {
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;

	}	

	public OptionLeg(int id, String sname, String strSym, boolean bPosition, int nPrice) {
		this.nId = id;
		this.strName = sname;		
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;

	}	
	
	public OptionLeg(int id, String sname, String strSym, boolean bPosition, int nPrice, boolean bCover, int coverPrice) {
		this.nId = id;
		this.strName = sname;				
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;
		this.bCovered = bCover;
		this.iCoverPrice = coverPrice;

	}
	
	public void setID(int id) {
		this.nId = id;
	}
	
	public int getID () {
		return this.nId;
	}
	
	public void setStrategyName(String sName) {
		this.strName = sName;
	}
	
	public String getStrategyName() {
		return this.strName ;
	}
	
	public String getSymbol() {
		return strSymbol;
	}
	
	public void setSymbol(String strSym) {
		this.strSymbol = strSym;
	}

	public boolean getPosition() {
		return bPosition;
	}

	public void setPosition(boolean bPosition) {
		this.bPosition = bPosition;
	}

	public String getType() {
		if(this.strSymbol.substring(this.strSymbol.length() -2) == "CE") {
			return "CE"; // 0:Call ; 1: Put
		} else if(this.strSymbol.substring(this.strSymbol.length() -2) == "PE") {	
			return "PE";
		} else {
			return "";
		}
	}
        
        public boolean getTypeBoolean() {
            if(this.strSymbol.substring(this.strSymbol.length() -2) == "CE") {
                    return false; // 0:Call ; 1: Put
            } else if(this.strSymbol.substring(this.strSymbol.length() -2) == "PE") {	
                    return true;
            } else {
                    return false;
            }            
        }

	public int getPrice() {
		return nPrice;
	}

	public void setPrice(int nPrice) {
		this.nPrice = nPrice;
	}
	
	public void setCovered(boolean cover) {
		this.bCovered = cover;
	}
	
	public boolean getCovered() { 
		return this.bCovered;
	}
	
	public void setCoverPrice(int price) {
		this.iCoverPrice = price;
	}
	
	public int getCoverPrice() {
		return this.iCoverPrice;
	}
	
	
	public List<Integer> getPayOffData() {
            List<Integer> listOLPayOff = new ArrayList<>();
    //		String strType = this.strSymbol.substring(this.strSymbol.length() -2);
            int nStrike = strSymbol.length() > 12 ? Integer.parseInt(strSymbol.substring(0, strSymbol.length()-2).substring(12)) : 0;		

            for(int nUnderlying = chartStart; nUnderlying <= chartEnd; nUnderlying+=100) {
                    if(this.strSymbol.substring(this.strSymbol.length() -2).equals("CE")) { // 0: Call, 1: Put
                            if (bPosition) { // Long ------ 		true: Long, False: Short 
                                    if (nUnderlying > nStrike) { // In the Money
                                            listOLPayOff.add(nUnderlying - nStrike - nPrice);
                                    } else { // Out of the Money
                                            listOLPayOff.add(- nPrice);
                                    }


                            } else if (!bPosition) { //Short
                                    if (nUnderlying > nStrike) { // In the Money
                                            listOLPayOff.add(-(nUnderlying - nStrike - nPrice));
                                    } else { // Out of the Money
                                            listOLPayOff.add(nPrice);
                                    }

                            }

                    } else { // Put
                            if (bPosition) { // Long
                                    if (nUnderlying < nStrike) { // In the Money
                                            listOLPayOff.add( nStrike - nUnderlying - nPrice);
                                    } else { // Out of the Money
                                            listOLPayOff.add(- nPrice);
                                    }


                            } else if (!bPosition) { //Short
                                    if (nUnderlying < nStrike) { // In the Money
                                            listOLPayOff.add(-(nStrike - nUnderlying - nPrice));
                                    } else { // Out of the Money
                                            listOLPayOff.add(nPrice);
                                    }	

                            }

                    }
            }			

            return listOLPayOff;
    }
        
    public void setChartStart(int start) {
        this.chartStart = start;
    }
    
    public int getChartStart() {
        return this.chartStart;
    }
    
    public void setChartEnd(int end) {
        this.chartEnd =  end;
    }
    
    public int getChartEnd() {
        return this.chartEnd;
    }
    
    public Date getExpiry() {
       // NIFTY2030JUL10000CE
       
        Date dt = null;
        try {
            dt = new SimpleDateFormat("yyddMMM").parse(strSymbol.substring(5, 12));
        } catch (ParseException ex) {
            Logger.getLogger(OptionLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;

    }
    
    public int getStrike() {
       return strSymbol.length() > 12 ? Integer.parseInt(strSymbol.substring(0, strSymbol.length()-2).substring(12)) : 0; 
    }



}
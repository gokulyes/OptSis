/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gokul WPC
 */
public class OptStrg  {

    private String strName;
    private int iPL;
    private List<OptLeg> listOptLeg = new ArrayList<>();	
    private List<OptLeg> currentLstOptLeg = new ArrayList<>();	
    private int chartStart = 9000;
    private int chartEnd = 12000;
    
    public OptStrg() {
//        System.out.print("\n OptStrg: chartStart - " + chartStart);
    }	


    public String getStrName() {
            return strName;
    }

    public void setStrName(String strName) {
            this.strName = strName;
    }
    
     public List<OptLeg> getListOptLeg() {
//        listOptLeg = new ArrayList<>();
//        try {
//            
//            Date dt = new Date();
//            OptLeg obj = new OptLeg();
//            obj.setID(1);
//            obj.setStrategyName("BackTest");
//
//            dt = new SimpleDateFormat("dd-MM-yyyy").parse("06-08-2020");
//            obj.setDateCovered(dt);
//            obj.setSymbol("NIFTY");
//            obj.setPrice(100);
//            obj.setPosition(false);
//            obj.setCovered(false);
//            obj.setDateCovered(dt);
//            obj.setCoverPrice(1000);
//            listOptLeg.add(obj);
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(OptStrg.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return listOptLeg;
    }   

    public List<OptLeg> getCurrentLstOptLeg() {
//            currentLstOptLeg = new ArrayList<>();
//            OptLeg obj = new OptLeg();
//            obj.setID(1);
//            obj.setStrategyName("Test");
//            obj.setSymbol("Test");
//            obj.setPrice(100);
//            obj.setPosition(false);
//            obj.setPostionCovered(false);
//            obj.setPostionCoverPrice(1000);
//            currentLstOptLeg.add(obj);
            return currentLstOptLeg;
    }

    public void addOptLeg(OptLeg optionLeg) {
            optionLeg.setChartStart(getChartStart());
            optionLeg.setChartEnd(getChartEnd());
            optionLeg.setHistPrice(optionLeg.getPrice());
            this.listOptLeg.add(optionLeg);
            if (!optionLeg.getCovered()) { // If not covered. Add to current list.
              currentLstOptLeg.add(optionLeg);
            }
            setPL();
    } 

    public void setOptLeg(int id, OptLeg optionLeg) {
        List<OptLeg> listOL = new ArrayList<>();	 
        for (OptLeg objOptLeg : listOptLeg) {
            if(objOptLeg.getID() == id) {
                listOL.add(optionLeg);
                
                if(optionLeg.getPosition()) { // if Positition is covered then remove from current list.
                    currentLstOptLeg.remove(objOptLeg);
                }
                
            } else {
                listOL.add(objOptLeg);
            }
        }
        this.listOptLeg = listOL;
        setPL();
    }         

    public List<Integer> getCurrentPayOffData() {

        boolean boolFirst = true;
        int nInnerCounter = 0;
        List<Integer> listOLPayOff = new ArrayList<>();


        for (OptLeg outerElement : currentLstOptLeg) {

                nInnerCounter = 0;
                for (Integer intElement : outerElement.getPayOffData()) {

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
    
    public List<Integer> getNetPayOffData() {

        List<Integer> listNetPayOff = new ArrayList<>();
        List<Integer> getCurrentPayOffData = getCurrentPayOffData();
        
        getCurrentPayOffData.forEach(objPl -> {
            listNetPayOff.add(objPl + this.iPL);
        });

        return listNetPayOff;		

    }    

    public int getPL() {
        return iPL;
    }

    public void setPL() {
        int pl = 0;
        for (OptLeg objOptLeg : listOptLeg) { // For each OptLeg in the list
            if(objOptLeg.getCovered()) {
                if(objOptLeg.getPosition()) {
                    pl +=  (objOptLeg.getCoverPrice() - objOptLeg.getPrice());
                } else {
                    pl +=  (objOptLeg.getPrice() - objOptLeg.getCoverPrice());
                }
            }
        }
        this.iPL = pl;

    }
    
    public int getChartStart(){
        return this.chartStart ;
    }
    
    public int getChartEnd() {
        return this.chartEnd ;
    }
    
    public void setChartSetting(int start, int end){
        this.chartStart = start;
        this.chartEnd = end;
    }
    
    public void setHistPrice(int id, float price) {
        for (OptLeg objOptLeg : listOptLeg) {
            if(objOptLeg.getID() == id) {
                objOptLeg.setHistPrice(price);
            }
                
        }
    }


    @Override
    public String toString() {
            return "OptStrg [strName=" + strName + ", listOptLeg=" + listOptLeg + "]";
    }
	

}

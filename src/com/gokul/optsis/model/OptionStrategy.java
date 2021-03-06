/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;

import java.util.ArrayList;
import java.util.List;

public class OptionStrategy {

    private String strName;
    private int iPL;
    private List<OptionLeg> listOptLeg = new ArrayList<>();	
    private List<OptionLeg> currentLstOptLeg = new ArrayList<>();	
    private int chartStart = 9000;
    private int chartEnd = 12000;
    
    public OptionStrategy() {
        System.out.print("\n OptionStrategy: chartStart - " + chartStart);
    }	


    public String getStrName() {
            return strName;
    }

    public void setStrName(String strName) {
            this.strName = strName;
    }
    
     public List<OptionLeg> getListOptLeg() {
            listOptLeg = new ArrayList<>();
            OptionLeg obj = new OptionLeg();
            obj.setID(1);
            obj.setStrategyName("Test");
            obj.setSymbol("NIFTY");
            obj.setPrice(100);
            obj.setPosition(false);
            obj.setCovered(false);
            obj.setCoverPrice(1000);
            listOptLeg.add(obj);
            return listOptLeg;
    }   

    public List<OptionLeg> getCurrentLstOptLeg() {
//            currentLstOptLeg = new ArrayList<>();
//            OptionLeg obj = new OptionLeg();
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

    public void addOptLeg(OptionLeg optionLeg) {
            optionLeg.setChartStart(getChartStart());
            optionLeg.setChartEnd(getChartEnd());
            this.listOptLeg.add(optionLeg);
            if (!optionLeg.getCovered()) { // If not covered. Add to current list.
              currentLstOptLeg.add(optionLeg);
            }
            setPL();
    } 

    public void setOptLeg(int id, OptionLeg optionLeg) {
        List<OptionLeg> listOL = new ArrayList<>();	 
        for (OptionLeg objOptionLeg : listOptLeg) {
            if(objOptionLeg.getID() == id) {
                listOL.add(optionLeg);
                
                if(optionLeg.getPosition()) { // if Positition is covered then remove from current list.
                    currentLstOptLeg.remove(objOptionLeg);
                }
                
            } else {
                listOL.add(objOptionLeg);
            }
        }
        this.listOptLeg = listOL;
        setPL();
    }         

    public List<Integer> getCurrentPayOffData() {

        boolean boolFirst = true;
        int nInnerCounter = 0;
        List<Integer> listOLPayOff = new ArrayList<>();


        for (OptionLeg outerElement : currentLstOptLeg) {

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
        for (OptionLeg objOptionLeg : listOptLeg) { // For each OptionLeg in the list
            if(objOptionLeg.getCovered()) {
                if(objOptionLeg.getPosition()) {
                    pl +=  (objOptionLeg.getCoverPrice() - objOptionLeg.getPrice());
                } else {
                    pl +=  (objOptionLeg.getPrice() - objOptionLeg.getCoverPrice());
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


    @Override
    public String toString() {
            return "OptionStrategy [strName=" + strName + ", listOptLeg=" + listOptLeg + "]";
    }
	

}

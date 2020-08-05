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

    public OptionStrategy() {

    }	


    public String getStrName() {
            return strName;
    }

    public void setStrName(String strName) {
            this.strName = strName;
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
            this.listOptLeg.add(optionLeg);
            if (!optionLeg.getPostionCover()) { // If not covered. Add to current list.
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
            if(objOptionLeg.getPostionCover()) {
                if(objOptionLeg.getPosition()) {
                    pl +=  (objOptionLeg.getPostionCoverPrice() - objOptionLeg.getPrice());
                } else {
                    pl +=  (objOptionLeg.getPrice() - objOptionLeg.getPostionCoverPrice());
                }
            }
        }
        this.iPL = pl;

    }


    @Override
    public String toString() {
            return "OptionStrategy [strName=" + strName + ", listOptLeg=" + listOptLeg + "]";
    }
	

}

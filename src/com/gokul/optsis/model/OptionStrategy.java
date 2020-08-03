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
	
	public OptionStrategy() {

	}	
	
	public OptionStrategy(String strName, List<OptionLeg> listOptLeg) {
		super();
		this.strName = strName;
		this.listOptLeg = listOptLeg;
	}

	public String getStrName() {
		return strName;
	}


	public void setStrName(String strName) {
		this.strName = strName;
	}


	public List<OptionLeg> getListOptLeg() {
		return listOptLeg;
	}


	public void setListOptLeg(List<OptionLeg> listOptLeg) {
		this.listOptLeg = listOptLeg;
	} 
	
	public void setOptLeg(OptionLeg optionLeg) {
		this.listOptLeg.add(optionLeg);
	} 
	
	public List<Integer> getPayOffData() {

		boolean boolFirst = true;
		int nInnerCounter = 0;
		List<Integer> listOLPayOff = new ArrayList<>();


		for (OptionLeg outerElement : listOptLeg) {
			
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
        
        public int getPL() {
            int iPL = 0;
            for (OptionLeg objOptionLeg : listOptLeg) { // For each OptionLeg in the list
                if(objOptionLeg.getPostionCover()) {
                    if(objOptionLeg.getPosition()) {
                        iPL +=  (objOptionLeg.getPostionCoverPrice() - objOptionLeg.getPrice());
                    } else {
                        iPL +=  (objOptionLeg.getPrice() - objOptionLeg.getPostionCoverPrice());
                    }
                }
            }
            return iPL;
            
        }

	
	@Override
	public String toString() {
		return "OptionStrategy [strName=" + strName + ", listOptLeg=" + listOptLeg + "]";
	}
	

}

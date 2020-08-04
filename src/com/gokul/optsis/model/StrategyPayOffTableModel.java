/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gokul WPC
 */
public class StrategyPayOffTableModel extends AbstractTableModel {

		
    private String[] columnNames = {"Strike Price", "P/L" };
    private List<Integer> strikeData = new ArrayList<Integer>();
    private List<Integer> plData = new ArrayList<Integer>();

    @Override
    public int getRowCount() {
         return plData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int col0 = strikeData.get(rowIndex);
        int col1 = plData.get(rowIndex);
        switch(columnIndex){
        case 0:
                return col0;                  
        case 1:
                return col1;    
        default : return null;
      }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void setPlData(List<Integer> data) {
        this.plData = data;
        
        strikeData = new ArrayList<Integer>();
        for(int nUnderlying =8000; nUnderlying <= 12000; nUnderlying+=100) {
            strikeData.add(nUnderlying);
        }
     }
    
}

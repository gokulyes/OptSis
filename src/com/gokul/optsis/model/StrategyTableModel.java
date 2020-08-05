/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StrategyTableModel extends AbstractTableModel {

		
    private String[] columnNames = {"ID", "Symbol", "Position", "Price" };
    private List<OptionLeg> data = new ArrayList<OptionLeg>();
    private String strStrategyName;

    //Abstract method implementation
    public int getRowCount() {
       return data.size();
    }

    //Abstract method implementation
    public int getColumnCount() {
       return columnNames.length;
    }

    //Abstract method implementation
    public Object getValueAt(int row, int colum) {
        OptionLeg objOptionLeg = data.get(row);

      switch(colum){
        case 0:
                return objOptionLeg.getID();                  
        case 1:
                return objOptionLeg.getSymbol();
        case 2: 
                if (objOptionLeg.getPosition())
                        return "Long";
                else
                        return "Short";
        case 3: return objOptionLeg.getPrice();
        default : return null;
      }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0 && getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        return super.getColumnClass(columnIndex);
    }


    public void addRowData(OptionLeg objOptionLeg){

                data.add(objOptionLeg);
    }            


    public OptionLeg getRowData(int selRow) {
        return data.get(selRow);
    }

    public void clearAllRows() {
        data.clear();
    }

    public String getStrategyName() {
        return strStrategyName;
    }

    @Override
    public String toString() {
            return "StrategyTable [columnNames=" + Arrays.toString(columnNames) + ", data=" + data.toArray() + "]";
    }	

		
}

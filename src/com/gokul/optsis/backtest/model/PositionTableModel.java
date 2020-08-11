/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.backtest.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gokul WPC
 */
public class PositionTableModel extends AbstractTableModel {

		
    private String[] columnNames = {"ID", "Symbol", "Date", "Position", "Price", "Covered","CDate", "CPrice", "BPrice" };
    private List<OptLeg> data = new ArrayList<OptLeg>();
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
        OptLeg objOptLeg = data.get(row);

      switch(colum){
        case 0:
                return objOptLeg.getID();                  
        case 1:
                return objOptLeg.getSymbol();
        case 2: return objOptLeg.getDateCreatedString();
        case 3: 
                if (objOptLeg.getPosition())
                        return "Long";
                else
                        return "Short";
        case 4: return objOptLeg.getPrice();
        case 5: return objOptLeg.getCovered();
        case 6: return objOptLeg.getDateCoveredString();
        case 7: return objOptLeg.getCoverPrice();
        case 8: return objOptLeg.getHistPrice();
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


    public void addRowData(OptLeg objOptLeg){

                data.add(objOptLeg);
    }            


    public OptLeg getRowData(int selRow) {
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

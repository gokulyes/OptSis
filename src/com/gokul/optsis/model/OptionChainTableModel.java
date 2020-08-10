/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokul.optsis.model;

import com.gokul.optsis.backtest.model.OptionChain;
import com.gokul.optsis.backtest.model.OptionPrice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gokul WPC
 */
public class OptionChainTableModel  extends AbstractTableModel {

		
    private String[] columnNames = {"Calls", "Strike", "Put"};
    private OptionChain optionChain = new OptionChain();

    //Abstract method implementation
    public int getRowCount() {
       return optionChain.getSize();
    }

    //Abstract method implementation
    public int getColumnCount() {
       return columnNames.length;
    }

    //Abstract method implementation
    public Object getValueAt(int row, int colum) {
        OptionPrice objOptionPrice = optionChain.getRow(row);

      switch(colum){
        case 0:
                return objOptionPrice.getCe();
        case 1:
                return objOptionPrice.getStrike();
        case 2: 
               return objOptionPrice.getPe();
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


    public void addRowData(OptionPrice objOptionPrice){
        
                optionChain.addRow(objOptionPrice);
    }            


    public OptionPrice getRowData(int selRow) {
        return optionChain.getRow(selRow);
    }

    public void clearAllRows() {
        optionChain.clearAllRows();
    }


    @Override
    public String toString() {
            return "StrategyTable [columnNames=" + Arrays.toString(columnNames)  + "]";
    }	

		
}

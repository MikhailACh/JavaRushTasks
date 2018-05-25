package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements ATableInterface{
        ATableInterface ati;

        TableInterfaceWrapper(ATableInterface ati) {
            this.ati = ati;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            ati.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return ati.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            ati.setHeaderText(newHeaderText);
        }
    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}
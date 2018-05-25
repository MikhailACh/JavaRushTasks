package com.javarush.task.task19.task1905;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        Customer customer  = new Customer(){
            @Override
            public String getCompanyName() {return "JavaRush Ltd.";}

            @Override
            public String getCountryName() {return "Ukraine";}
        };

        Contact contact = new Contact() {
            @Override
            public String getName(){
                return "Ivanov, Ivan";
            }
            @Override
            public String getPhoneNumber(){
                return "+38(050)123-45-67";
            }
        };
        DataAdapter adapter = new DataAdapter(customer, contact);
        System.out.println(adapter.getCountryCode());
        System.out.println(adapter.getCompany());
        System.out.println(adapter.getContactFirstName());
        System.out.println(adapter.getContactLastName());
        System.out.println(adapter.getDialString());
    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        public String getCountryCode(){
            String s = null;
            for (Map.Entry<String, String> pair : countries.entrySet()){
                if (customer.getCountryName().equals(pair.getValue()))
                    s = pair.getKey();
            }
            return s;
        }

        public String getCompany() {
            return customer.getCompanyName();
        }

        public String getContactFirstName() {
            //return contact.getName().substring(contact.getName().indexOf(" ") + 1);
            return contact.getName().split(",")[1].trim();
        }

        public String getContactLastName() {
            return contact.getName().split(",")[0];
        }

        public String getDialString() {
            return "callto://" + contact.getPhoneNumber().replaceAll("[()-]", "");
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}
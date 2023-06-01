/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.bill;

import java.text.DecimalFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
/**
 *
 * @author Xuan Thao
 */
//public class NewClass {
    

public class DecimalFormatExample {
    public static void main(String[] args) {
     // Số hàng triệu cần định dạng
        double number = 123000000;

        // Định dạng số hàng triệu với VND và số thập phân
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,###.### VND", symbols);
        String formattedNumber = decimalFormat.format(number);

        // In số hàng triệu đã được định dạng
        System.out.println("Số hàng triệu định dạng: " + formattedNumber);
    }
    
}
//}

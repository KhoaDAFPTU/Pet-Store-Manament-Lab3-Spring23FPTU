/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Tools {
    public static Scanner sc = new Scanner(System.in);
    public static double getADouble(String inputMsg, String errorMsg, double min) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < min)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int min) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
       
    public static String getString(String inputMsg, String errorMsg) {
        String id;        
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();            
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
    public static String regexString(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }
    public static String getDate(String inputMsg, String errorMsg) {
        String data;
        boolean x;
        while (true) {
            System.out.print(inputMsg);
            data = sc.nextLine().trim();
            try {
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                date.setLenient(false);
                date.parse(data);
                return data;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static int updateAnInteger(String inputMsg, int min, int oldData){
        boolean check = true;
        int number = oldData;
        do{
            try{
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if(tmp.isEmpty()){
                    check = false;
                }else{
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            }catch(Exception e){
                System.out.println("Input number!!!");
            }
        }while(check == true || number < min);
        return number;
    }
    public static double updateADouble(String inputMsg, double min, double oldData){
        boolean check = true;
        double number = oldData;
        do{
            try{
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if(tmp.isEmpty()){
                    check = false;
                }else{
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            }catch(Exception e){
                System.out.println("Input number!!!");
            }
        }while(check == true || number < min);
        return number;
    }
    public static String updateString(String inputMsg, String oldData){
        String result = oldData;
        System.out.printf(inputMsg);
        String tmp = sc.nextLine();
        if(!tmp.isEmpty()){
            result = tmp;
        }
        return result;
    }
}

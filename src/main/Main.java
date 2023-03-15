/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.PetManagement;
import java.text.ParseException;
import ui.Menu;

public class Main {
    public static void main(String[] args) throws ParseException {
        PetManagement pl = new PetManagement();
        Menu menu = new Menu("PET STORE MANAGEMENT.");
        menu.addNewOption("1. Add a pet.");
        menu.addNewOption("2. Find a pet.");
        menu.addNewOption("3. Update a pet.");
        menu.addNewOption("4. Delete a pet.");
        menu.addNewOption("5. Add an order.");
        menu.addNewOption("6. List orders.");
        menu.addNewOption("7. Sort orders.");
        menu.addNewOption("8. Save data.");
        menu.addNewOption("9. Load data.");
        menu.addNewOption("10.Exit.");

        int choiceMenu;
        do{
            pl.show();
            menu.printMenu();
            choiceMenu = menu.getChoice();
            switch(choiceMenu){
                case 1:
                    pl.addAPet();
                    break;
                case 2:
                    pl.findAPet();
                    break;
                case 3:
                    pl.updateAPet();
                    break;
                case 4:
                    pl.deleteAPet();
                    break;
                case 5:
                    pl.addAnOrder();
                    break;
                case 6:
                    pl.listOrders();
                    break;
                case 7:
                    pl.sortOrders();
                    break;
                case 8:
                    pl.saveData();
                    break;
                case 9:
                    pl.loadData();
                    break;
                case 10:
                    System.out.println("Bye,bye.See you next time.");
                    break;
            }
        }while(choiceMenu != 10);
    }
}

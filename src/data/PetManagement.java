/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import utils.Tools;

public class PetManagement {

    private HashMap<String, Pet> map = new HashMap<>();
    private HashMap<Integer, Order> mapOrder = new HashMap<>();
    private ArrayList<String> listIdPetOrder = new ArrayList();

    public void addAPet() {
        String id, confirm;
        do {
            do {
                id = Tools.regexString("Enter ID(Pxxx): ", "x is digit.Input again!", "^[P|p]\\d{3}$");
                if (map.containsKey(id)) {
                    System.out.println("Duplicated ID.Input again!");
                }
            } while (map.containsKey(id));
            String depscription;
            do {
                depscription = Tools.getString("Enter depscription: ", "Khong duoc bo trong!");
                if (depscription.length() < 3 || depscription.length() > 50) {
                    System.out.println("The description field must be from 3 to 50 characters");
                }
            } while (depscription.length() < 3 || depscription.length() > 50);
            String date = Tools.getDate("Enter Date(dd/MM/yyyy): ", "Wrong.Input again!");
            double price = Tools.getADouble("Enter Price: ", "More than 0.", 0);
            String category;
            do {
                category = Tools.getString("Enter Category(Cat/Dog/Parrot): ", "Not blank or empty.");
                if (!category.equalsIgnoreCase("Cat") && !category.equalsIgnoreCase("Dog")
                        && !category.equalsIgnoreCase("Parrot")) {
                    System.out.println("Just Cat or Dog or Parrot.");
                }
            } while (!category.equalsIgnoreCase("Cat") && !category.equalsIgnoreCase("Dog")
                    && !category.equalsIgnoreCase("Parrot"));
            Pet pet = new Pet(id, depscription, date, price, category);
            map.put(id, pet);
            System.out.println("Pet's information has been added.");
            confirm = Tools.regexString("Do you want to continues(y/n): ", "Just y or n.", "^[Y|y|n|N]$");
        } while (confirm.equalsIgnoreCase("Y"));

    }

    public void show() {
        for (Pet value : map.values()) {
            System.out.println(value);
        }
    }

    public void findAPet() {
        String id = Tools.regexString("Enter ID(Pxxx) Searching: ", "x is digit.Input again!", "^[P|p]\\d{3}$");
        if (map.containsKey(id)) {
            System.out.println("+----+--------------------+---------------+----------+----------+");
            System.out.println("| ID |    DEPSCRIPTION    |      DATE     |   PRICE  | CATEGORY |");
            System.out.println("+----+--------------------+---------------+----------+----------+");
            map.get(id).showInfor();
            System.out.println("+----+--------------------+---------------+----------+----------+");
        } else {
            System.out.println("The pet does not exist.");
        }
    }

    public void updateAPet() {
        String id = Tools.regexString("Enter ID(Pxxx) Updating: ", "x is digit.Input again!", "^[P|p]\\d{3}$");
        if (map.containsKey(id)) {
            System.out.println("HERE IS OLD INFORMATION:");
            System.out.println("+-----+--------------------+---------------+----------+----------+");
            System.out.println("| ID  |    DEPSCRIPTION    |      DATE     |   PRICE  | CATEGORY |");
            System.out.println("+-----+--------------------+---------------+----------+----------+");
            map.get(id).showInfor();
            System.out.println("+-----+--------------------+---------------+----------+----------+");
            String newDepscription = Tools.updateString("Enter New Depscription: ", map.get(id).getDescription());
            String confirmDate = Tools.regexString("Do you want to change Date(y/n): ", "Just y or n.", "^[y|Y|n|N]$");
            if (confirmDate.equalsIgnoreCase("Y")) {
                String newDate = Tools.getDate("Enter New Date(dd/MM/yyyy): ", "Wrong.Input again!");
                map.get(id).setDate(newDate);
            }
            double newPrice = Tools.updateADouble("Enter New Price: ", 0, map.get(id).getPrice());
            String confirmCategory = Tools.regexString("Do you want to change Category(y/n): ", "Just y or n", "^[y|Y|n|N]$");
            if (confirmCategory.equalsIgnoreCase("Y")) {
                String category;
                do {
                    category = Tools.getString("Enter Category(Cat/Dog/Parrot): ", "Not blank or empty.");
                    if (!category.equalsIgnoreCase("Cat") && !category.equalsIgnoreCase("Dog")
                            && !category.equalsIgnoreCase("Parrot")) {
                        System.out.println("Just Cat or Dog or Parrot.");
                    }
                } while (!category.equalsIgnoreCase("Cat") && !category.equalsIgnoreCase("Dog")
                        && !category.equalsIgnoreCase("Parrot"));
                map.get(id).setCategory(category);
            }
            map.get(id).setDescription(newDepscription);
            map.get(id).setPrice(newPrice);
            System.out.println("Pet's information has been updated.");
            System.out.println("Successfull.");
        } else {
            System.out.println("The pet does not exist.");
        }
    }

    public void deleteAPet() {
        String id = Tools.regexString("Enter ID(Pxxx) Deleting: ", "x is digit.Input again!", "^[P|p]\\d{3}$");
        if (map.containsKey(id)) {
            String confirm = Tools.regexString("Are you sure delete(y/n): ", "Just y or n.", "^[Y|y|N|n]$");
            if (confirm.equalsIgnoreCase("Y")) {
                boolean check = false;
                for (int i = 0; i < listIdPetOrder.size(); i++) {
                    if (listIdPetOrder.get(i).equalsIgnoreCase(id)) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("The pet is already in an order detail.Fail");
                } else {
                    map.remove(id, map.get(id));
                    System.out.println("Remove successfull");
                }
            } else {
                System.out.println("You cancel.. Fail.");
            }
        } else {
            System.out.println("The pet does not exist.");
        }
    }

    public void addAnOrder() {
        ArrayList<Pet> list = new ArrayList();
        ArrayList<OrderDetail> listPetOrder = new ArrayList();
        for (Pet x : map.values()) {
            list.add(x);
        }
        String date = Tools.getDate("Enter Date(dd/MM/yyyy): ", "Wrong.Input again!");
        String name = Tools.getString("Enter Name Customer: ", "Not blank or empty.");
        String confirm;
        do {
            int stt = 1;
            System.out.println("+-----+-----+--------------------+---------------+----------+----------+");
            System.out.println("| STT | ID  |    DEPSCRIPTION    |      DATE     |   PRICE  | CATEGORY |");
            System.out.println("+-----+-----+--------------------+---------------+----------+----------+");
            for (int i = 0; i < list.size(); i++) {
                System.out.print("|" + stt++ + "     ");
                System.out.printf("|%-5s|%-20s|%-15s|%-10.1f|%-10s|\n", list.get(i).getId(), list.get(i).getDescription(),
                        list.get(i).getDate(), list.get(i).getPrice(), list.get(i).getCategory());
            }
            System.out.println("+-----+-----+--------------------+---------------+----------+----------+");
            int choice = Tools.getAnInteger("Enter your choice[1.." + list.size() + "]: ", "Just from 1 to " + list.size(), 1, list.size());
            listIdPetOrder.add(list.get(choice - 1).getId());
            int quantity = Tools.getAnInteger("Enter Quantity: ", "More than 0.Input again!", 0);
            listPetOrder.add(new OrderDetail(list.get(choice - 1).getId(), quantity, list.get(choice - 1).getPrice() * quantity));
            confirm = Tools.regexString("Do you want to order continue(Y/N): ", "Just Y or N.", "^[Y|y|N|n]$");
        } while (confirm.equalsIgnoreCase("Y"));
        double totalOrder = 0;
        for (int i = 0; i < listPetOrder.size(); i++) {
            totalOrder += listPetOrder.get(i).getTotal();
        }
        mapOrder.put((mapOrder.size() + 1), new Order((mapOrder.size() + 1), date, name, listPetOrder.size(), totalOrder));
        System.out.println("Order successfully.");
    }

    public void listOrders() throws ParseException {
        String dateStart = Tools.getDate("Enter Date Start(dd/MM/yyyy): ", "Wrong.Input again!");
        String dateEnd;
        do {
            dateEnd = Tools.getDate("Enter Date End(dd/MM/yyyy): ", "Wrong.Input put again!");
            if (getTime(dateEnd) <= getTime(dateStart)) {
                System.out.println("The end date must be greater than the start date.");
            }
        } while (getTime(dateEnd) <= getTime(dateStart));
        int stt = 1;
        int countPet = 0;
        double totalOrder = 0;
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        System.out.println("| NO  | ORDER ID |  ORDER DATE   |      CUSTOMER      |PET COUNT |ORDER TOTAL($) |");
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        for (Order o : mapOrder.values()) {
            if (getTime(o.getDate()) >= getTime(dateStart) && getTime(o.getDate()) <= getTime(dateEnd)) {
                System.out.printf("|%-5d|%-10d|%-15s|%-20s|%10d|%15.1f|\n", stt++, o.getId(), o.getDate(), o.getNameCustomer(), o.getCountPet(), o.getTotal());
                countPet += o.getCountPet();
                totalOrder += o.getTotal();
            }
        }
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        System.out.println("|     |TOTAL     |               |                    |        " + countPet + "|" + "          " + totalOrder + "|");
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");

    }

    public long getTime(String date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        return formater.parse(date).getTime();
    }

    public void sortOrders() throws ParseException {
        ArrayList<Order> listSort = new ArrayList();
        for (Order o : mapOrder.values()) {
            listSort.add(o);
        }
        int choice = Tools.getAnInteger("Enter your choice[Order(1.ID 2.Date 3.Name or 4.Total)]: ", "Just from 1 to 4", 1, 4);
        int choiceType = Tools.getAnInteger("[1.ASC or 2.DESC]: ", "Just 1 or 2", 1, 2);
        System.out.println("LIST OF ORDERS:");
        switch (choice) {
            case 1:
                System.out.println("Sorted by : Order ID");
                if (choiceType == 1) {
                    System.out.println("Sort order :ASC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return o1.getId() - o2.getId();
                    });
                } else {
                    System.out.println("Sort order :DESC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return o2.getId() - o1.getId();
                    });
                }
                break;
            case 2:
            /*System.out.println("Sorted by : Order Date");
                if (choiceType == 1) {
                    System.out.println("Sort order :ASC");
                    Collections.sort(listSort, (o1, o2) -> {
                        long t1 = getTime(o1.getDate());
                        long t2 = getTime(o2.getDate());
                        return t1 - t2;
                    });
                } else {
                    System.out.println("Sort order :DESC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return o2.getId() - o1.getId();
                    });
                }
                break;
             */
            case 3:
                System.out.println("Sorted by : Order Customer Name");
                if (choiceType == 1) {
                    System.out.println("Sort order :ASC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return o1.getNameCustomer().compareToIgnoreCase(o2.getNameCustomer());
                    });
                } else {
                    System.out.println("Sort order :DESC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return o2.getNameCustomer().compareToIgnoreCase(o1.getNameCustomer());
                    });
                }
                break;
            case 4:
                System.out.println("Sorted by : Order Total");
                if (choiceType == 1) {
                    System.out.println("Sort order :ASC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return (int) (o1.getTotal() - o2.getTotal());
                    });
                } else {
                    System.out.println("Sort order :DESC");
                    Collections.sort(listSort, (o1, o2) -> {
                        return (int) (o2.getTotal() - o1.getTotal());
                    });
                }
                break;
        }
        int stt = 1;
        int countPet = 0;
        double totalOrder = 0;
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        System.out.println("| NO  | ORDER ID |  ORDER DATE   |      CUSTOMER      |PET COUNT |ORDER TOTAL($) |");
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        for (Order o : listSort) {
            System.out.printf("|%-5d|%-10d|%-15s|%-20s|%10d|%15.1f|\n", stt++, o.getId(), o.getDate(), o.getNameCustomer(), o.getCountPet(), o.getTotal());
            countPet += o.getCountPet();
            totalOrder += o.getTotal();
        }
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
        System.out.println("|     |TOTAL     |               |                    |        " + countPet + "|" + "          " + totalOrder + "|");
        System.out.println("+-----+----------+---------------+--------------------+----------+---------------+");
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream("pets.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Fail.");
        }
        System.out.println("Save data to pets.dat successfully.");
        try {
            FileOutputStream fos = new FileOutputStream("orders.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapOrder);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Fail.");
        }
        System.out.println("Save data to orders.dat successfully.");
    }

    public void loadData() {
        map.clear();
        mapOrder.clear();
        try {
            FileInputStream fis = new FileInputStream("pets.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<String, Pet>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Fail.");
        }
        System.out.println("Load data from pets.dat successfully.");
        try {
            FileInputStream fis = new FileInputStream("orders.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapOrder = (HashMap<Integer, Order>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Fail.");
        }
        System.out.println("Load data from orders.dat successfully.");
    }
}

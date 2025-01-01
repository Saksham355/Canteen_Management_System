package com.example.canteen.ap_canteen;

import javax.xml.transform.Source;

import java.util.*;

public class admin extends user{
    public admin(String id,String password){
        super(id,password);
    }
    public void admin_menu(){
        System.out.println("Select from the following options:");
        System.out.println("1.Show full menu");
        System.out.println("2.Add Items");
        System.out.println("3.Update Items");
        System.out.println("4.Delete Items");
        System.out.println("5.Get orders");
        System.out.println("6.Handle orders");
        System.out.println("7.Initiate refund");
        System.out.println("8.Sales report");
        System.out.println("9.Sign out");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt();
        switch(choice){
            case 1:
                canteen.m.printfull();
                admin_menu();
                break;
            case 2:
                addItems();
                admin_menu();
                break;
            case 3:
                updateItems();
                admin_menu();
                break;
            case 4:
                removeItems();
                admin_menu();
                break;
            case 5:
                printOrders();
                admin_menu();
                break;
            case 6:
                updateOrders();
                admin_menu();
                break;
            case 7:
                refund();
                admin_menu();
                break;
            case 8:
                salesreport();
                admin_menu();
            case 9:
                canteen.main_menu();
                admin_menu();
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                admin_menu();
        }
    }
    public void addItems(){
        System.out.println("Full menu:");
        canteen.m.printfull();
        System.out.println("Enter item category");
        Scanner sc = new Scanner(System.in);
        String category = sc.nextLine();
        if(canteen.m.getMenu().get(category.toLowerCase())==null){
            System.out.println("Invalid category, please try again.");
            return;
        }
        ArrayList<String> catagorylist = canteen.m.getMenu().get(category.toLowerCase());
        System.out.println("Enter item name");
        String itemName = sc.nextLine();
        if(canteen.m.getMenu().get(category.toLowerCase()).contains(itemName)){
            System.out.println("Item already exists, please try again.");
            return;
        }
        System.out.println("Enter item price");
        int itemPrice;
        try {
            itemPrice = sc.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid item price, please try again.");
            return;
        }
        items new_item = new items(itemName,itemPrice,"NA",category,true);
        canteen.code_to_item.put(itemName,new_item);
        catagorylist.add(itemName);
        ObjectSaver.saveObject(canteen.code_to_item,"codetoitems.ser");
        ObjectSaver.saveObject(canteen.m.getMenu(),"menu.ser");
    }
    public void updateItems(){
        System.out.println("Full menu:");
        canteen.m.printfull();
        System.out.println("Enter item name");
        Scanner sc = new Scanner(System.in);
        String itemname= sc.nextLine();
        if(canteen.code_to_item.get(itemname)==null){
            System.out.println("Invalid item, please try again.");
            return;
        }
        items item = canteen.code_to_item.get(itemname);
        canteen.code_to_item.remove(itemname);
        System.out.println("Enter item price");
        int itemPrice;
        try {
            itemPrice = sc.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid item price, please try again.");
            return;
        }
        item.setPrice(itemPrice);
        System.out.println("Change availability status(Available/Unavailable)");
        Scanner sc2 = new Scanner(System.in);
        String opt = sc2.nextLine();
        boolean availability = (opt.compareToIgnoreCase("Available")==0)?true:false;
        item.setAvailability(availability);
        canteen.code_to_item.put(itemname,item);
        ObjectSaver.saveObject(canteen.code_to_item,"codetoitems.ser");
    }
    public void removeItems(){
        System.out.println("Enter item name");
        Scanner sc = new Scanner(System.in);
        String itemname = sc.nextLine();
        items i  = canteen.code_to_item.get(itemname);
        for(order o:canteen.orders){
            if(o.getOrderList().contains(i)){
                o.setStatus("Denied");
                customer c = canteen.id_to_customer.get(o.getId());
                c.getPast().add(o);
                order ord = c.getMy_order();
                ord  = new order(c.getId());
                canteen.orders.remove(o);
                ObjectSaver.saveObject(ord,"pendingorders.ser");
            }
        }
        String cat = i.getCategory();
        ArrayList<String> catagorylist = canteen.m.getMenu().get(cat.toLowerCase());
        catagorylist.remove(itemname);
        canteen.code_to_item.remove(itemname);
        ObjectSaver.saveObject(canteen.code_to_item,"codetoitems.ser");
        ObjectSaver.saveObject(canteen.m.getMenu(),"menu.ser");
    }
    public void printOrders(){
        int ind = 1;
        PriorityQueue<order>OrderTest = GetpendingOrders.getPendingorders();
        if(OrderTest==null){
            System.out.println("There are no orders");
            return;
        }
        StringBuilder listStr  = new StringBuilder();
        StringBuilder itemStr  = new StringBuilder();
        for(order o:OrderTest){
            itemStr.append("Order-id:"+o.getId());
            itemStr.append("\n");
            itemStr.append("Items: \n");
            for(items i : o.getOrderList()){
                itemStr.append(i.getCode()+":"+i.getQuantity()+"\n");
            }
            itemStr.append("Status:"+o.isStatus() + "\n");
            listStr.insert(0,itemStr);
            itemStr = new StringBuilder();

            ind++;
        }
        System.out.println(listStr);

    }
    public void updateOrders(){
        if(canteen.orders.size()==0){
            System.out.println("There are no orders");
            return;
        }
        System.out.println("Enter order id:");
        Scanner sc = new Scanner(System.in);
        String orderid = sc.nextLine();
        order ord = null;
        for(order o:canteen.orders){
            if(o.getId().equals(orderid)){
                ord = o;
                break;
            }
        }
        if(ord == null){
            System.out.println("Order not found");
            return;
        }
        System.out.println("Enter status to update(Preparing/Prepared/Delivered/Completed):");
        String status = sc.nextLine();
        ord.setStatus(status);
        if(status.compareToIgnoreCase("Completed")==0){
            canteen.orders.remove(ord);
            GetpendingOrders.removePendingOrders(ord.getId());
            customer c =canteen.id_to_customer.get(orderid);
            canteen.sales +=1;
            ObjectSaver.saveObject(ord,"pastorders.ser",true);
            c.getPast().add(ord);
        }
    }
    public void refund(){
        System.out.println("Enter order id:");
        Scanner sc = new Scanner(System.in);
        String orderid = sc.nextLine();
        order ord = null;
        for(order o:canteen.orders){
            if(o.getId().equals(orderid)){
                ord = o;
                break;
            }
        }
        if(ord == null){
            System.out.println("Order not found");
        }
        else{
            System.out.println(ord.getTotal());
            canteen.account -= ord.getTotal();
            ord.setStatus("Cancelled");
            customer c = canteen.id_to_customer.get(ord.getId());
            c.getPast().add(ord);
            canteen.orders.remove(ord);
            GetpendingOrders.removePendingOrders(ord.getId());
        }
    }
    public void salesreport() {
        System.out.println("Total earnings:" + canteen.account);
        System.out.println("Total sales:" + canteen.orders.size());
        if (canteen.popular != null) {
            System.out.println("Most popular item:" + canteen.popular.getCode());
        }
        else{
            System.out.println("No item is popular yet!");
        }
    }
}

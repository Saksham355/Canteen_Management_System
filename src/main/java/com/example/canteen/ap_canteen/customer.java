package com.example.canteen.ap_canteen;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class customer  extends user implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean vip;
    private ArrayList<order> past = new ArrayList<order>();
    private order my_order;
    private cart cart;
    public ArrayList<order>getPast() {
        return past;
    }
    public customer(){

        super();
        my_order = new order("");
    }
    public customer(String id, String password) {
        super(id, password);
        my_order = new order(getId());

    }
    public order getMy_order() {
        return my_order;
    }
    public cart getCart() {
        return cart;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
    public boolean getVip() {
        return vip;
    }

    public void customer_menu()
    {
        System.out.println("1.Display Menu");
        System.out.println("2.Display by category");
        System.out.println("3.Display sorted menu");
        System.out.println("4.Search item and read Reviews");
        System.out.println("5.Add items to cart");
        System.out.println("6.Cancel Order");
        System.out.println("7.View Past Orders and Submit Reviews ");
        System.out.println("8.Go to Cart/Place Order");
        System.out.println("9.Track order");
        System.out.println("10.Become Vip");
        System.out.println("11.Sign out");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                canteen.m.printfull();
                customer_menu();
                break;
            case 2:

                displayCategory(scan);
                customer_menu();
                break;
            case 3:
                ArrayList<items> all_items = new ArrayList<items>(canteen.code_to_item.values());
                all_items.sort(Comparator.comparingInt(items::getPrice));
                System.out.println("Ascending");
                System.out.println("\n");
                for (items i : all_items) {
                    items.printItem(i.getCode());
                }
                System.out.println("Descending");
                System.out.println("\n");
                for(int ind = all_items.size()-1;ind>=0;ind--){
                    items.printItem(all_items.get(ind).getCode());
                }
                customer_menu();
                break;
            case 4:
                System.out.println("Enter item name");
                String name = scan.next();
                if(canteen.m.search(name)==null){
                    System.out.println("Item not found");
                    break;
                }
                items i = canteen.m.search(name);
                items.printItem(i.getCode());
                System.out.println("Review:"+i.getReview());
                customer_menu();
                break;
            case 5:
                placeOrder(scan);
                customer_menu();
                break;
            case 6:
                cancelOrder();
                customer_menu();
                break;
            case 7:
                viewPastOrders();
                customer_menu();
                break;
            case 8:

                cart.cart_menu(this);
                customer_menu();
                break;
            case 9:
                if(my_order.getOrderList().size()==0) {
                    System.out.println("You have no orders");
                    break;
                }
                System.out.println("Status:"+my_order.isStatus());
                customer_menu();
                break;
            case 10:
                if(this.getVip()==true){
                    System.out.println("You are already a VIP member");
                    customer_menu();
                    break;
                }
                System.out.println("You need to pay 50 rupees to become a VIP member");
                System.out.println("Type 1 to pay,0 to cancel.");
                while(true) {
                    Scanner sc = new Scanner(System.in);
                    String pay = sc.nextLine();
                    if (pay.equals("1")) {
                        canteen.account += 50;
                        System.out.println("Congratulations,you are a VIP member now!");
                        this.setVip(true);
                        customer_menu();
                        break;
                    } else if (pay.equals("0")) {
                        customer_menu();
                        break;
                    }
                    else{
                        System.out.println("Invalid choice,please try again!");
                    }
                }
                break;
            case 11:
                canteen.main_menu();
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                customer_menu();
        }

    }

    private void displayCategory(Scanner scan) {
        System.out.println("Select Category by number");
        System.out.println("1.Indian");
        System.out.println("2.Continental");
        System.out.println("3.Chinese");
        System.out.println("4.Beverages");
        System.out.println("5.Snacks");
        System.out.println("6.Sweets");
        System.out.println("7.Exit");
        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                canteen.m.print_indian();
                displayCategory(scan);
                break;
            case 2:
                canteen.m.print_continental();
                displayCategory(scan);
                break;
            case 3:
                canteen.m.print_chinese();
                displayCategory(scan);
                break;
            case 4:
                canteen.m.print_beverages();
                displayCategory(scan);
                break;
            case 5:
                canteen.m.print_snacks();
                displayCategory(scan);
                break;
            case 6:
                canteen.m.print_sweets();
                displayCategory(scan);
                break;
            case 7:
                customer_menu();
                break;
            default:
                System.out.println("Invalid category choice.");
        }
    }

    public void placeOrder(Scanner scan) {
        System.out.println("Enter items to add (item-quantity-addons/requests) and enter -1 to exit");
        boolean loop = true;
        while (loop) {
            System.out.println("Enter item to add:");
            String item = scan.next();
            if (canteen.code_to_item.containsKey(item) && canteen.code_to_item.get(item).getAvailability() == true) {
                System.out.println("Quantity:");
                int quantity = scan.nextInt();
                System.out.println("Add-ons:");
                Scanner s = new Scanner(System.in);
                String requests = s.nextLine();
                canteen.code_to_item.get(item).setQuantity(quantity);
                canteen.code_to_item.get(item).setAddons(requests);
                my_order.getOrderList().add(canteen.code_to_item.get(item));
                canteen.code_to_item.get(item).count +=quantity;
                if(canteen.popular == null){
                    canteen.popular = canteen.code_to_item.get(item);
                }
                else if(canteen.popular.count<canteen.code_to_item.get(item).count){
                    canteen.popular = canteen.code_to_item.get(item);
                }
                System.out.println("Anything else?[Y/N]");

                while(true) {
                    Scanner sc3 = new Scanner(System.in);
                    String option = sc3.nextLine();
                    if (option.compareToIgnoreCase("N") == 0) {
                        loop = false;
                        break;
                    } else if (option.compareToIgnoreCase("Y") == 0) {
                        loop = true;
                        break;
                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
            } else {
                System.out.println("Sorry,item is not available");
                System.out.println("Anything else?[Y/N]");

                while(true) {
                    Scanner sc1 = new Scanner(System.in);
                    String option = sc1.nextLine();
                    if (option.compareToIgnoreCase("N") == 0) {
                        loop = false;
                        break;
                    } else if (option.compareToIgnoreCase("Y") == 0) {
                        loop = true;
                        break;

                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
            }
        }
        if (my_order.getOrderList().isEmpty()) {
            System.out.println("Nothing added!");
        } else {

            cart = new cart(my_order);
            System.out.println("Items added to cart,successfully!");
        }
        customer_menu();
    }

    public String placeOrderTest(String itemname,String breaker ,Scanner scan) {
        String ans = null;
        System.out.println("Enter items to add (item-quantity-addons/requests) and enter -1 to exit");
        boolean loop = true;
        while (loop) {
            System.out.println("Enter item to add:");
            String item = itemname;
            if (canteen.code_to_item.containsKey(item) && canteen.code_to_item.get(item).getAvailability() == true) {
                System.out.println("Quantity:");
                int quantity = scan.nextInt();
                System.out.println("Add-ons:");
                Scanner s = new Scanner(System.in);
                String requests = s.nextLine();
                canteen.code_to_item.get(item).setQuantity(quantity);
                canteen.code_to_item.get(item).setAddons(requests);
                my_order.getOrderList().add(canteen.code_to_item.get(item));
                canteen.code_to_item.get(item).count +=quantity;
                if(canteen.popular == null){
                    canteen.popular = canteen.code_to_item.get(item);
                }
                else if(canteen.popular.count<canteen.code_to_item.get(item).count){
                    canteen.popular = canteen.code_to_item.get(item);
                }
                System.out.println("Anything else?[Y/N]");

                while(true) {
                    Scanner sc3 = new Scanner(System.in);
                    String option = sc3.nextLine();
                    if (option.compareToIgnoreCase("N") == 0) {
                        loop = false;
                        break;
                    } else if (option.compareToIgnoreCase("Y") == 0) {
                        loop = true;
                        break;
                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
            } else {
                ans = "Sorry,item is not available";

                System.out.println("Sorry,item is not available");
                System.out.println("Anything else?[Y/N]");

                while(true) {
                    Scanner sc1 = new Scanner(System.in);
                    String option = breaker;
                    if (option.compareToIgnoreCase("N") == 0) {
                        loop = false;
                        break;
                    } else if (option.compareToIgnoreCase("Y") == 0) {
                        loop = true;
                        break;

                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
            }
        }
        if (my_order.getOrderList().isEmpty()) {
            System.out.println("Nothing added!");
        } else {

            cart = new cart(my_order);
            System.out.println("Items added to cart,successfully!");
        }
//        customer_menu();
    return ans;}
    private void cancelOrder() {
        if( my_order.isStatus().equals("Prepared")||my_order.isStatus().equals("Delivered") ) {
            System.out.println("Order cannot be cancelled");
        }
        else {
            if(!canteen.orders.contains(this.my_order)){
                System.out.println("You dont have any orders placed!");
                return;
            }
            order cancelled = this.my_order.clone(this.my_order);
            cancelled.setStatus("Cancelled");
            canteen.orders.remove(cancelled);
            ObjectSaver.saveObject(cancelled,"pastorders.ser",true);
            this.past.add(cancelled);
            canteen.account -= my_order.getTotal();
            my_order = new order(this.getId());
            canteen.sales -=1;
            System.out.println("Order cancelled!");
        }
    }


    private void viewPastOrders()  {
        System.out.println("Displaying past orders:");
        int ind2 = 1;
        ArrayList<order> pastorders = null;
        try {
           pastorders = getPastOrders.getPastOrders(this.getId());
        }
        catch (Exception e) {

        }
        if (pastorders != null||pastorders == null){
        for (order orders : pastorders) {
            System.out.println("Order-"+ind2+":");
            System.out.println("Status:"+orders.isStatus());
            int ind1 = 1;
            for(items item : orders.getOrderList()) {

                System.out.println(ind1+". "+item.getCode()+":"+item.getQuantity());
                ind1++;
            }
            ind2++;
        }}
        else if(pastorders == null){
            System.out.println("No past orders");
        }
        else {
            System.out.println("Give review for an item[Y/N]");
            Scanner scan = new Scanner(System.in);
            String choice = scan.next();
            if (choice.equals("Y")) {
                System.out.println("Enter order index");
                int index = scan.nextInt();
                order reviewOrder = getPast().get(index - 1);
                if (reviewOrder.isStatus() == "Cancelled") {
                    System.out.println("Order was cancelled!");
                } else {
                    System.out.println("Enter item");
                    String item = scan.next();
                    System.out.println("Enter review");
                    String review = scan.nextLine();
                    items i = canteen.code_to_item.get(item);
                    if (reviewOrder.getOrderList().contains(i)) {
                        i.setReview(review);
                        System.out.println("Review submitted!");
                    } else {
                        System.out.println("You did not order this, please try again.");
                    }
                }
            }
        }
    }
}

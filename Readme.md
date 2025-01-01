# Byte Me! : Canteen Management System

**Overview**

This Canteen Management System is a Java-based application designed to streamline canteen operations for both customers and administrators. It provides functionalities for:

**Customer Features:**

* User Login and Sign-up
* Menu Browsing
* Item Selection and Order Placement
* Order Tracking
* Order Cancellation
* Past Orders and Reviews

**Admin Features:**

* Menu Management
* Order Management
* Sales Reporting

**Code Structure**

The project consists of the following Java classes:

1. **canteen.java**
    * Main entry point for the application.
    * Handles user interactions, login/signup, and redirects to appropriate functionalities.
    * Manages the overall state of the system, including item inventory, customer information, and orders.
    * Sample customers, admin testing:
      * Customer1  - id:c1, password:c1
      * Customer2  - id:c2, password:c2
      * Customer3  - id:c3, password:c3
      * Admin has a fixed id:admin@123 and password:admin, only password is required for admin login
      
2. **customer.java**
    * Represents a customer user.
    * Handles customer-specific functionalities like placing orders, viewing past orders, and submitting reviews.
3. **admin.java**
    * Represents an admin user.
    * Handles admin-specific functionalities like managing the menu, processing orders, and generating reports.
4. **items.java**
    * Represents an individual item in the canteen.
    * Stores information about the item, such as its code, price, availability, and reviews.
5. **menu.java**
    * Manages the canteen's menu.
    * Organizes items into categories and provides functionalities for searching and displaying items.
6. **order.java**
    * Represents an order placed by a customer.
    * Stores information about the items in the order, their quantities, and the order status.
7. **cart.java**
    * Manages the customer's shopping cart.
    * Allows customers to add, remove, and view items in their cart.
    * Calculates the total cost of the items in the cart.
8. **Serialization**
   * The Objectsaver class contains the saveObject() method that takes the object to serialize along with the file name.
   * The AppendingObjectOutputStream class has a AppendingObjectOutputStream() helper method that checks if the file exists and appends to it , or creates a new file.
9. **Deserialization**
   * Custom deserialization class for:-
   * customers{LoadCustomers,Authenticator}
   * pending-orders{GetpendingOrders}
   * Customer past orders{getPastOrders}
   * Menu{GetMenu,Getcodetoitem}
10. **GUI creation using JAVAFX**
    * Screen for displaying canteen menu
      * Run: HelloApplication.java
      * Controller class: HelloController
      * FXML file: hello-view.fxml
    * Screen for displaying pending orders in canteen
      * Run: pendingApplication.java
      * Controller class: pendingController
      * FXML file: pending-order.fxml

**Concepts Used**
   * Menu of Food Items-ArrayList,HashMap
   * List of current orders to be processed-PriorityQueue(for VIP functionality)
   *Order History for each individual customer-ArrayList
   * Id to customer pairing using map
   * item name: item pairing using map
   * Serialization - Deserialization using object input and output streams

**Assumptions**
   * Whenever a refund is initiated the amount will be deducted from the account of the canteen.
   * Whenever an item is removed from a menu, the status of the orders containing the item is det to "Denied",and the order is added to the past order of the customer and refund is made.
   * Cancelled order can be seen in the past order of the customer.
   * Orders having status "Prepared" or "Delivered" cannot be cancelled.
   * Tests for "InvalidLogin" and "InvalidOrder" have been created by replicating the exact functionality, providing inputs through arguments and returning the output to test.
   * All the tests can be run through the TestSuite class in test folder.

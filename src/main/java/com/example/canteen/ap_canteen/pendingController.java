package com.example.canteen.ap_canteen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.PriorityQueue;

public class pendingController {

    @FXML
    private VBox dynamicOrderTables;

    public void initialize() {

        PriorityQueue<order> pending = GetpendingOrders.getPendingorders();
        PriorityQueue<order> pendingOrders = new PriorityQueue<>(pending);


        for (order ord : pendingOrders) {

            dynamicOrderTables.getChildren().add(new Label("Order ID: " + ord.getId()));


            dynamicOrderTables.getChildren().add(new Label("Order Status: " + ord.isStatus()));


            TableView<OrderItem> tableView = createOrderTable(ord);
            dynamicOrderTables.getChildren().add(tableView);
        }
    }

    private TableView<OrderItem> createOrderTable(order ord) {

        TableView<OrderItem> tableView = new TableView<>();
        TableColumn<OrderItem, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(itemColumn, quantityColumn, priceColumn);

        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
        for (items i : ord.getOrderList()) {
            orderItems.add(new OrderItem(i.getCode(), i.getQuantity(), i.getPrice()));
        }
        tableView.setItems(orderItems);

        tableView.setPrefHeight(150);
        tableView.setPrefWidth(500);

        return tableView;
    }

    public static class OrderItem {
        private final String itemName;
        private final int quantity;
        private final double price;

        public OrderItem(String itemName, int quantity, double price) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }
}

package com.example.canteen.ap_canteen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloController {

    @FXML private TableView<items> indianTable;
    @FXML private TableColumn<items, String> indianCodeColumn;
    @FXML private TableColumn<items, Integer> indianPriceColumn;
    @FXML private TableColumn<items, String> indianAvailabilityColumn;

    @FXML private TableView<items> continentalTable;
    @FXML private TableColumn<items, String> continentalCodeColumn;
    @FXML private TableColumn<items, Integer> continentalPriceColumn;
    @FXML private TableColumn<items, String> continentalAvailabilityColumn;

    @FXML private TableView<items> chineseTable;
    @FXML private TableColumn<items, String> chineseCodeColumn;
    @FXML private TableColumn<items, Integer> chinesePriceColumn;
    @FXML private TableColumn<items, String> chineseAvailabilityColumn;

    @FXML private TableView<items> snacksTable;
    @FXML private TableColumn<items, String> snacksCodeColumn;
    @FXML private TableColumn<items, Integer> snacksPriceColumn;
    @FXML private TableColumn<items, String> snacksAvailabilityColumn;

    @FXML private TableView<items> beveragesTable;
    @FXML private TableColumn<items, String> beveragesCodeColumn;
    @FXML private TableColumn<items, Integer> beveragesPriceColumn;
    @FXML private TableColumn<items, String> beveragesAvailabilityColumn;

    @FXML private TableView<items> sweetsTable;
    @FXML private TableColumn<items, String> sweetsCodeColumn;
    @FXML private TableColumn<items, Integer> sweetsPriceColumn;
    @FXML private TableColumn<items, String> sweetsAvailabilityColumn;

    public void initialize() {
        ArrayList<items> Indiancodes = new ArrayList<items>();
        ArrayList<items> Continentalcodes = new ArrayList<items>();
        ArrayList<items> Chinesecodes = new ArrayList<items>();
        ArrayList<items> snackscodes = new ArrayList<items>();
        ArrayList<items> beveragescodes = new ArrayList<items>();
        ArrayList<items> sweetscodes = new ArrayList<items>();

        Map<String,items> codetoitems = Getcodetoitem.getcodetoitems();
        List<items> allItems = new ArrayList<items>(codetoitems.values());


        for(items item:allItems) {
            String cat = item.getCategory();
            if(cat.equalsIgnoreCase("indian")) {
                Indiancodes.add(item);
            }
            else if(cat.equalsIgnoreCase("continental")) {
                Continentalcodes.add(item);
            }
            else if(cat.equalsIgnoreCase("chinese")) {
                Chinesecodes.add(item);
            }
            else if(cat.equalsIgnoreCase("snacks")) {
                snackscodes.add(item);
            }
            else if(cat.equalsIgnoreCase("beverages")) {
                beveragescodes.add(item);
            }
            else if(cat.equalsIgnoreCase("sweets")) {
                sweetscodes.add(item);
            }

        }

        setupTable(indianTable, indianCodeColumn, indianPriceColumn, indianAvailabilityColumn,Indiancodes );
        setupTable(continentalTable, continentalCodeColumn, continentalPriceColumn, continentalAvailabilityColumn, Continentalcodes);
        setupTable(chineseTable, chineseCodeColumn, chinesePriceColumn, chineseAvailabilityColumn, Chinesecodes);
        setupTable(snacksTable, snacksCodeColumn, snacksPriceColumn, snacksAvailabilityColumn, snackscodes);
        setupTable(beveragesTable, beveragesCodeColumn, beveragesPriceColumn, beveragesAvailabilityColumn, beveragescodes);
        setupTable(sweetsTable, sweetsCodeColumn, sweetsPriceColumn, sweetsAvailabilityColumn, sweetscodes);
    }

    private void setupTable(TableView<items> table, TableColumn<items, String> codeColumn, TableColumn<items, Integer> priceColumn,
                            TableColumn<items, String> availabilityColumn, Iterable<items> itemslist) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));  // Removed the "getCode" method call
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityColumn.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().stringAvailabilty()));

        ObservableList<items> menu = FXCollections.observableArrayList();
        for (items itemp :itemslist ) {
            if (itemp != null) {
                menu.add(itemp);
            }
        }
        table.setItems(menu);
    }
}

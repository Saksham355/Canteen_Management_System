//module com.example.canteen.ap_canteen {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;
//    requires javafx.graphics;
//    requires javafx.base;
//
//
//    opens com.example.canteen.ap_canteen to javafx.fxml;
//    exports com.example.canteen.ap_canteen;
//}
module com.example.canteen.ap_canteen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics; // Already present in your file

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.base;

    opens com.example.canteen.ap_canteen to javafx.fxml;
    exports com.example.canteen.ap_canteen;
}

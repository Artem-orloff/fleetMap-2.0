module main.java.com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.web;
    requires static fleetMapParser;


    opens main.java.com.example.demo1 to javafx.fxml;
    exports main.java.com.example.demo1;
}
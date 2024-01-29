module com.example.cardgamew24lh {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cardgamew24lh to javafx.fxml;
    exports com.example.cardgamew24lh;
}
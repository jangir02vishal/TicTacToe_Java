module com.example.tictactoe_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_javafx to javafx.fxml;
    exports com.example.tictactoe_javafx;
}
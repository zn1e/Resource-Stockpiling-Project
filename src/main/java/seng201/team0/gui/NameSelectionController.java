package seng201.team0.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NameSelectionController {
    @FXML
    Button verifyButton;
    @FXML
    Button nextButton;
    @FXML
    TextField nameTextField;
    private boolean nameVerified = false;
    public void verifyButtonClicked(ActionEvent event){
        String name = nameTextField.getText();
        if (name.length() >= 3 && name.length() <= 15 && name.matches("^[a-zA-Z0-9]+$")){
            nameVerified = true;
            verifyButton.setText("OK"); // can add colors to button
        } // *include else statement for trying again
    }


}

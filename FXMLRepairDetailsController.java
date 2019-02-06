/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class FXMLRepairDetailsController implements Initializable {
    @FXML
    public TextField RepairsDone;
    @FXML
    public void repairsDone(){
        RepairsDone.getText();
    }
    
    @FXML
    public TextField RepairsDate;
    @FXML
    public void repairsDate(){
        RepairsDate.getText();
    }
    
    @FXML
    public TextField RepairsCost;
    @FXML
    public void repairsCost(){
        RepairsCost.getText();
    }
    
    @FXML
    public void backButton(ActionEvent event) throws IOException{
        Parent back = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainscene = new Scene(back);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainscene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

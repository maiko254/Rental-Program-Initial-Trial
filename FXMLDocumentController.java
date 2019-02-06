/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Mike
 */
public class FXMLDocumentController implements Initializable {
    public RentalModel rentalModel = new RentalModel();
    
    @FXML
    private Label isConnected;
    
    @FXML
    private Button CreateTable;
    @FXML
    public void createNewTable(){
        //SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\Mike\\Documents\\NetBeansProjects\\SQLite\\PaymentDetails.sqlite";
        
        //SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS housedetails(\n"
                +"HouseNo text PRIMARY KEY,\n"
                +"Amount text NOT NULL,\n"
                +"Date text NOT NULL,\n"
                +"Month text NOT NULL,\n"
                +"Name text NOT NULL\n"
                +");";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()){
            //create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Button to handle change of scene
     */
    @FXML
    public void changesceneButton(ActionEvent event) throws IOException{
        Parent Payment = FXMLLoader.load(getClass().getResource("FXMLPaymentDetails.fxml"));
        Scene Paymentscene = new Scene(Payment);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Paymentscene);
        window.show();
    }
    
    @FXML
    public void changescene2Button(ActionEvent event) throws IOException{
        Parent Repairs = FXMLLoader.load(getClass().getResource("FXMLRepairDetails.fxml"));
        Scene Repairscene = new Scene(Repairs);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Repairscene);
        window.show();
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        if(rentalModel.isDBConnected()){
            isConnected.setText("            Connected");
        }else{
            isConnected.setText("            Not Connected");
        }
    }
   }    
    


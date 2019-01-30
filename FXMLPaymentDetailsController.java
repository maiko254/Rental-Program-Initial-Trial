/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class FXMLPaymentDetailsController implements Initializable {
    /**
     * Button to handle change of scene
     */
    @FXML
    public void changescene1Button(ActionEvent event) throws IOException{
        Parent Payment = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene Paymentscene = new Scene(Payment);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Paymentscene);
        window.show();
    }
    ObservableList<String>HouseNo = FXCollections.observableArrayList("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13");
    ObservableList<String>MonthPaid = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    
    @FXML
    public ComboBox HouseNoBox;
    @FXML
    private void initializeHouseNoBox(){
        HouseNoBox.setItems(HouseNo);
        String HouseNumber = (String) HouseNoBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public TextField AmountPaid;
    @FXML
    public void AmountInput(){
        AmountPaid.getText();
        String Amount = (String)AmountPaid.getText();
    }
    
    @FXML
    public TextField DatePaid;
    @FXML
    public void DateInput(){
        DatePaid.getText();
    }
    
    @FXML
    public ComboBox MonthPaidBox;
    @FXML
    public void initializeMonthPaidBox(){
        MonthPaidBox.setItems(MonthPaid);
        String Month = (String)MonthPaidBox.getSelectionModel().getSelectedItem();
    }
    
    
    @FXML
    public TextField TenantName;
     @FXML
    public void getTenantName(){
        TenantName.getText();
        String Tenant = (String)TenantName.getText();
    }
    
    
    /*Preparing and executing statement to insert data into PaymentDetails
    database and housedetails table.
    */
    public void insert(String HouseNo, String Amount, String DatePaid, String MonthPaid, String PayerName){
        String url = "jdbc:sqlite:C:\\Users\\Mike\\Documents\\NetBeansProjects\\SQLite\\PaymentDetails.sqlite";
        String sql1 = "INSERT INTO housedetails(HouseNo,Amount,Date,Month,Name) VALUES(?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql1)){
            pstmt.setString(1, HouseNo);
            pstmt.setString(2, Amount);
            pstmt.setString(3, DatePaid);
            pstmt.setString(4, MonthPaid);
            pstmt.setString(5, PayerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    /*Inserting data captured from user input into housedetails table.*/   
    }
    @FXML
    public void TableInsertButton(){
        this.insert((String)HouseNoBox.getSelectionModel().getSelectedItem(), AmountPaid.getText(),DatePaid.getText(), (String)MonthPaidBox.getSelectionModel().getSelectedItem(), TenantName.getText());
    }
    
    @FXML
    private  Button Submit;
    /*
    Saving to file, appending to new line everytime this 
    handlesubmit method is called.
    */
    @FXML
    private void handleSubmit(){
        try {
           File file = new File("Tenant Payment Details.txt");
           FileWriter myfile = new FileWriter(file, true);
           myfile.write("\r\n");
           if(!file.exists()){
               file.createNewFile();
           }
           PrintWriter PTD = new PrintWriter(myfile);
           PTD.println(HouseNoBox.getSelectionModel().getSelectedItem());
           PTD.println(AmountPaid.getText());
           PTD.println(MonthPaidBox.getSelectionModel().getSelectedItem());
           PTD.println(TenantName.getText());
           
           PTD.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
         System.exit(1);
        };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
}
}

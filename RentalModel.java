/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental2;

import java.sql.*;

/**
 *
 * @author Mike
 */
public class RentalModel {
    Connection conection;
    public RentalModel(){
        conection = sqlconnection.Connector();
        if(conection == null)System.exit(1);
    }
    
    public boolean isDBConnected(){
        try {
            return !conection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

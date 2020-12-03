/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author davi
 */
public class MySQL {
    private String server;
    private String port;
    private String schema;
    private String login;
    private String psw;
    
    public MySQL(){
        this.server = "localhost";
        this.port = "3306";
        this.schema = "sp";
        this.login = "davi";
        this.psw = "Bravo@1998";
        
    }

    public Connection connect() throws SQLException{
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sp", 
                "davi", "Bravo@1998");
    }
    
    public boolean close(Connection con) {
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void getAll(Connection con, String table, String[] columns) throws SQLException{
        Statement stat = con.createStatement();
        ResultSet data = stat.executeQuery("SELECT * FROM "+table);
        
        while(data.next()){
            for(int i=0; i<columns.length; i++){
                if(i>0){
                    System.out.print(", ");
                }
                System.out.print(data.getString(columns[i]));
            }
            System.out.print("\n");
        }
    }
    
    
    
}

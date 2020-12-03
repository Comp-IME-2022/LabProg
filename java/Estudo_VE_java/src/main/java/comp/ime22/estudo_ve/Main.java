/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp.ime22.estudo_ve;

import java.sql.Connection;
import java.sql.SQLException;
import Network.MySQL;

/**
 *
 * @author davi
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect();
        
        String[] columns = {"PCOD","CITY"};
        mysql.getAll(mysql_con, "P", columns);
        
        
        mysql.close(mysql_con);
    }
}

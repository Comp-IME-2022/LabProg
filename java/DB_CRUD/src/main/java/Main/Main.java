/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DB.Mongo;
import DB.MySQL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/*
  CREATE SCHEMA `LabProg` ;
  CREATE TABLE `LabProg`.`Users` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `website` VARCHAR(50) NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `suite` VARCHAR(20) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `geo_lat` VARCHAR(10) NOT NULL,
  `geo_lng` VARCHAR(10) NOT NULL,
  `companyName` VARCHAR(50) NOT NULL,
  `catchPhrase` VARCHAR(100) NOT NULL,
  `bs` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
 */

/**
 * @author davi
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        //Connect to MySQL server
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect();
        
        
        //Get JSON and set to list
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/users"), new TypeReference<List<User>>(){});
        
        
        for(User user : users){
            //user.setName("Davi");
            //System.out.println(user);
            mysql.createUser(mysql_con, user);
            //
        }
        
        mysql.deleteUser(mysql_con, 3);
        mysql.deleteUser(mysql_con, 5);
        mysql.deleteUser(mysql_con, 7);
        
        
        //System.out.println(mysql.getUser(mysql_con, "1"));
        
        List<User> newUsers = new ArrayList();
        newUsers = mysql.getAllUsers(mysql_con);
        if(newUsers!=null){
            for(User user : newUsers){
                System.out.println(user);
            }
        }
        
        
        mysql.close(mysql_con);
    }
}

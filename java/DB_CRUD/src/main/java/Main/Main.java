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
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
        /*
        //Connect to MySQL server
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect();
        */
        
        //Connect to MongoDB server
        Mongo mongo = new Mongo();
        MongoClient mongo_con = mongo.connect();
        MongoCollection mongo_coll = mongo.collection(mongo_con);
        
        /*
        //Get JSON and set to list
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/users"), new TypeReference<List<User>>(){});
        
        
        for(User user : users){
            //user.setName("Davi");
            //System.out.println(user);
            //mysql.createUser(mysql_con, user);
            //mongo.createUser(mongo_coll, user);
        }
        //System.out.println(mongo.getUser(mongo_coll, 10));
        
        
        
        List<User> newUsers;
        //newUsers = mysql.getAllUsers(mysql_con);
        newUsers = mongo.getAllUsers(mongo_coll);
        if(newUsers!=null){
            for(User user : newUsers){
                System.out.println(user);
            }
        }
        */
        
        User res = mongo.getUser(mongo_coll, 5);
        res.setName("Davi Pontes");
        //res.setId(13);
        //mongo.createUser(mongo_coll, res);
        //System.out.println(mongo.getUser(mongo_coll, 5));
        mongo.deleteUser(mongo_coll, 5);
        
        //mysql.close(mysql_con);
        mongo.close(mongo_con);
    }
}

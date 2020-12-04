/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClient;

import DB.MySQL;
import DB.Mongo;

/**
 *
 * @author davi
 */
public class Main {
     public static void main(String[] args) throws IOException, SQLException {
        //Connect to MySQL server
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect();
        
        
        //Connect to MongoDB server
        Mongo mongo = new Mongo();
        MongoClient mongo_con = mongo.connect();
        MongoCollection mongo_coll = mongo.collection(mongo_con);
        
        
        //Get JSON and set to list
        ObjectMapper objectMapper = new ObjectMapper();
        List<toDo> toDoList = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/todos"), new TypeReference<List<toDo>>(){});
        
        
        //Set data do DB
        for(toDo task : toDoList){
            System.out.println("MySQL Task: "+task.getId()+" - "+mysql.createToDo(mysql_con, task));
            System.out.println("MongoDB Task: "+task.getId()+" - "+mongo.createToDo(mongo_coll, task));
        }
        
        
        //Close conections
        mysql_con.close();
        mongo.close(mongo_con);
    }
}



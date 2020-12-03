/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import DB.MySQL;

/**
 *
 * @author davi
 */
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        //Connect to MySQL server
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect();
        

        //Get JSON and set to list
        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> postList = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), new TypeReference<List<Post>>(){});
        
        
        //Set data do DB
        for(Post post : postList){
            System.out.println("MySQL Task: "+post.getId()+" - "+mysql.createPost(mysql_con, post));
        }
        
        
        //Print all database
        String[] cols = {"id", "userId", "title", "body"};
        mysql.getAll(mysql_con, "Posts", cols);
        
        
        //Close conections
        mysql_con.close();
    }
}

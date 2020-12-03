/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.List;
import java.util.ArrayList;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import Main.toDo;

/**
 *
 * @author davi
 */
public class Mongo {
    private String server;
    private String port;
    private String db;
    private String collection;
    private String login;
    private String psw;
    
    public Mongo(){
        this.server = "localhost";
        this.port = "27017";
        this.db = "test";
        this.collection = "todos";
        this.login = "davi";
        this.psw = "Bravo%401998";
        
    }

    public MongoClient connect(){
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://"+login+":"+psw+"@"+server+":"+port));
        return client;
    }
    
    public MongoCollection collection(MongoClient client){
        return client.getDatabase(db).getCollection(collection);
    }
    
    public boolean close(MongoClient con) {
        try {
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean createToDo(MongoCollection collection, toDo task){
        int res = 0;
        try{
            Document doc = new Document();
            doc.append("_id",task.getId());
            doc.append("id",task.getId());
            doc.append("userId",task.getUserId());
            doc.append("title",task.getTitle());
            doc.append("completed",task.getCompleted());
            collection.insertOne(doc);
            doc.clear();            
            res = 1;
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        return res>0;
    }
    
    public void getAll(MongoCollection collection){
        try(MongoCursor<Document> cur = collection.find().iterator()){
            while(cur.hasNext()){
                Document doc = cur.next();
                
                List list = new ArrayList(doc.values());
                System.out.print(" UserId: "+list.get(2));
                System.out.print(" Id: "+list.get(1));
                System.out.print(" Title: "+list.get(3));
                System.out.println(" Completed: "+list.get(4));
            }
        }
    }
    
}

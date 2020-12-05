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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import Main.User;
import Main.Address;
import Main.Company;
import Main.Georeference;

/**
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
        this.db = "LabProg";
        this.collection = "Users";
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
    
    public boolean createUser(MongoCollection collection, User user){
        int res = 0;
        try{
            Document doc = new Document();
            doc.append("_id",user.getId());
            doc.append("name",user.getName());
            doc.append("username",user.getUsername());
            doc.append("email",user.getEmail());
            doc.append("phone",user.getPhone());
            doc.append("website",user.getWebsite());
            doc.append("street",user.getAddress().getStreet());
            doc.append("suite",user.getAddress().getSuite());
            doc.append("city",user.getAddress().getCity());
            doc.append("zipcode",user.getAddress().getZipcode());
            doc.append("geo_lat",user.getAddress().getGeo().getLat());
            doc.append("geo_lng",user.getAddress().getGeo().getLng());
            doc.append("companyName",user.getCompany().getName());
            doc.append("catchPhrase",user.getCompany().getCatchPhrase());
            doc.append("bs",user.getCompany().getBs());
            collection.insertOne(doc);
            doc.clear();            
            res = 1;
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        if(res>0){
            System.out.println("User "+user.getId()+" Created!");
        }
        return res>0;
    }
    
    public boolean updateUser(MongoCollection collection, User user){
        try{
            Document doc = new Document();
            doc.append("_id",user.getId());
            doc.append("name",user.getName());
            doc.append("username",user.getUsername());
            doc.append("email",user.getEmail());
            doc.append("phone",user.getPhone());
            doc.append("website",user.getWebsite());
            doc.append("street",user.getAddress().getStreet());
            doc.append("suite",user.getAddress().getSuite());
            doc.append("city",user.getAddress().getCity());
            doc.append("zipcode",user.getAddress().getZipcode());
            doc.append("geo_lat",user.getAddress().getGeo().getLat());
            doc.append("geo_lng",user.getAddress().getGeo().getLng());
            doc.append("companyName",user.getCompany().getName());
            doc.append("catchPhrase",user.getCompany().getCatchPhrase());
            doc.append("bs",user.getCompany().getBs());

            UpdateResult res=collection.updateOne(Filters.eq("_id",user.getId()), new Document("$set", doc));
            if(res.getModifiedCount()>0){
                System.out.println("User "+user.getId()+" Updated!");
                return true;
            }else{
                if(res.getMatchedCount()>0){
                    System.out.println("User "+user.getId()+" not changed!");
                }else{
                    System.out.println("User "+user.getId()+" does not exist!");
                }
                return false;
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }
    
    public boolean deleteUser(MongoCollection collection, Integer userId){
        DeleteResult res = collection.deleteOne(Filters.eq("_id", userId));
        if(res.getDeletedCount()>0){
            System.out.println("User "+userId+" Deleted!");
            return true;
        }else{
            System.out.println("User "+userId+" does not exist!");
            return false;
        }
    }
    
    public User getUser(MongoCollection collection, Integer userId){
        MongoCursor<Document> cursor = collection.find(Filters.eq("_id", userId)).iterator();
        if(cursor.hasNext()){
            User user = new User();
            Document doc = cursor.next();

            user.setId(doc.getInteger("_id"));
            user.setName(doc.getString("name"));
            user.setUsername(doc.getString("username"));
            user.setEmail(doc.getString("email"));
            user.setPhone(doc.getString("phone"));
            user.setWebsite(doc.getString("website"));

            Georeference geo = new Georeference();
            geo.setLat(doc.getString("geo_lat"));
            geo.setLng(doc.getString("geo_lng"));

            Address address = new Address();
            address.setCity(doc.getString("city"));
            address.setStreet(doc.getString("street"));
            address.setSuite(doc.getString("suite"));
            address.setZipcode(doc.getString("zipcode"));
            address.setGeo(geo);

            Company company = new Company();
            company.setName(doc.getString("companyName"));
            company.setCatchPhrase(doc.getString("catchPhrase"));
            company.setBs(doc.getString("bs"));

            user.setAddress(address);
            user.setCompany(company);
            
            System.out.println("User "+userId+" Retrieved!");
            
            return user;
        }else{
            System.out.println("User "+userId+" does not exist!");
            return null;
        }
    }
    
    public List<User> getAllUsers(MongoCollection collection){
        MongoCursor<Document> cursor = collection.find().iterator();
        List<User> users = new ArrayList<User>();
        
        if(cursor.hasNext()){
            while(cursor.hasNext()){
                User user = new User();
                Document doc = cursor.next();

                user.setId(doc.getInteger("_id"));
                user.setName(doc.getString("name"));
                user.setUsername(doc.getString("username"));
                user.setEmail(doc.getString("email"));
                user.setPhone(doc.getString("phone"));
                user.setWebsite(doc.getString("website"));

                Georeference geo = new Georeference();
                geo.setLat(doc.getString("geo_lat"));
                geo.setLng(doc.getString("geo_lng"));

                Address address = new Address();
                address.setCity(doc.getString("city"));
                address.setStreet(doc.getString("street"));
                address.setSuite(doc.getString("suite"));
                address.setZipcode(doc.getString("zipcode"));
                address.setGeo(geo);

                Company company = new Company();
                company.setName(doc.getString("companyName"));
                company.setCatchPhrase(doc.getString("catchPhrase"));
                company.setBs(doc.getString("bs"));

                user.setAddress(address);
                user.setCompany(company);

                users.add(user);
            }
            return users;
        }else{
            System.out.println("No Users on DB!");
            return null;
        }
    }
}

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
    private MongoClient mongo_con;
    private MongoCollection mongo_coll;
    
    public Mongo(){
        this.server = "localhost";
        this.port = "27017";
        this.db = "LabProg";
        this.collection = "Users";
        this.login = "user";
        this.psw = "pass";
        
    }

    public MongoClient connect() throws InterruptedException{
        MongoClient client;
        for(;;){
            try{
                client = new MongoClient(new MongoClientURI("mongodb://"+login+":"+psw+"@"+server+":"+port));
                mongo_con = client;
                collection(mongo_con);
                return client;
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
            Thread.sleep(1);
        }
    }
    
    public MongoCollection collection(MongoClient client){
        MongoCollection coll = client.getDatabase(db).getCollection(collection);
        mongo_coll = coll;
        return coll;
    }
    
    public boolean close(MongoClient con) {
        try {
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean createUser(User user){
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
            mongo_coll.insertOne(doc);
            doc.clear();            
            res = 1;
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        if(res>0){
            System.out.println("Usuário "+user.getId()+" Cadastrado!");
        }
        return res>0;
    }
    
    public boolean updateUser(User user){
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

            UpdateResult res = mongo_coll.updateOne(Filters.eq("_id",user.getId()), new Document("$set", doc));
            if(res.getModifiedCount()>0){
                System.out.println("Usuário "+user.getId()+" Atualizado!");
                return true;
            }else{
                if(res.getMatchedCount()>0){
                    System.out.println("Usuário "+user.getId()+" não alterado!");
                }else{
                    System.out.println("User "+user.getId()+" não existe!");
                }
                return false;
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }
    
    public boolean deleteUser(Integer userId){
        DeleteResult res = mongo_coll.deleteOne(Filters.eq("_id", userId));
        if(res.getDeletedCount()>0){
            System.out.println("Usuário "+userId+" Deletado!");
            return true;
        }else{
            System.out.println("Usuário "+userId+" não existe!");
            return false;
        }
    }
    
    public User getUser(Integer userId){
        MongoCursor<Document> cursor = mongo_coll.find(Filters.eq("_id", userId)).iterator();
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
            
            System.out.println("Usuário "+userId+" retornado!");
            
            return user;
        }else{
            System.out.println("Usuário "+userId+" não existe!");
            return null;
        }
    }
    
    public List<User> getAllUsers(){
        MongoCursor<Document> cursor = mongo_coll.find().iterator();
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
            
            System.out.println("Retornado todos os usuários!");
            
            return users;
        }else{
            System.out.println("O BD não possui usuários cadastrados!");
            return null;
        }
    }
}

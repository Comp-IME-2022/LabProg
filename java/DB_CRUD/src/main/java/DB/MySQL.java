/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import Main.User;
import Main.Address;
import Main.Company;
import Main.Georeference;

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
    private Connection mysql_con;
    
    public MySQL(){
        this.server = "localhost";
        this.port = "3306";
        this.schema = "LabProg";
        this.login = "user";
        this.psw = "pass";
        
    }

    public Connection connect() throws SQLException, InterruptedException{
        Connection con;
        for(;;){
            try{
                con = DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+schema, login, psw);
                mysql_con = con;
                return con;
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
            Thread.sleep(1);
        }
    }
    
    public boolean close(Connection con) {
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean createUser(User user) throws SQLException{
        if(mysql_con!=null){
            int res = 0;
            Statement stat = mysql_con.createStatement();
            try{
                res = stat.executeUpdate("INSERT INTO `LabProg`.`Users` (`id`, `name`, `username`, `email`, `phone`, `website`, `street`, `suite`, `city`, `zipcode`, `geo_lat`, `geo_lng`, `companyName`, `catchPhrase`, `bs`) "
                        + "VALUES ("
                        + "'"+user.getId()+"'"
                        + ", '"+user.getName()+"'"
                        + ", '"+user.getUsername()+"'"
                        + ", '"+user.getEmail()+"'"
                        + ", '"+user.getPhone()+"'"
                        + ", '"+user.getWebsite()+"'"
                        + ", '"+user.getAddress().getStreet()+"'"
                        + ", '"+user.getAddress().getSuite()+"'"
                        + ", '"+user.getAddress().getCity()+"'"
                        + ", '"+user.getAddress().getZipcode()+"'"
                        + ", '"+user.getAddress().getGeo().getLat()+"'"
                        + ", '"+user.getAddress().getGeo().getLng()+"'"
                        + ", '"+user.getCompany().getName()+"'"
                        + ", '"+user.getCompany().getCatchPhrase()+"'"
                        + ", '"+user.getCompany().getBs()+"'"
                        + ");");
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
            if(res>0){
                System.out.println("Usuário "+user.getId()+" Cadastrado!");
            }
            return res>0;
        }else{
            System.out.println("MySQL não conectado!");
            return false;
        }
    }
    
    public boolean updateUser(User user) throws SQLException{
        if(mysql_con!=null){
            int res = 0;
            Statement stat = mysql_con.createStatement();
            try{
                res = stat.executeUpdate("UPDATE `LabProg`.`Users` SET "
                        + "`name`="+ "'"+user.getName()+"'"
                        + ", `username`="+ "'"+user.getUsername()+"'"
                        + ", `email`="+ "'"+user.getEmail()+"'"
                        + ", `phone`="+ "'"+user.getPhone()+"'"
                        + ", `website`="+ "'"+user.getWebsite()+"'"
                        + ", `street`="+ "'"+user.getAddress().getStreet()+"'"
                        + ", `suite`="+ "'"+user.getAddress().getSuite()+"'"
                        + ", `city`="+ "'"+user.getAddress().getCity()+"'"
                        + ", `zipcode`="+ "'"+user.getAddress().getZipcode()+"'"
                        + ", `geo_lat`="+ "'"+user.getAddress().getGeo().getLat()+"'"
                        + ", `geo_lng`="+ "'"+user.getAddress().getGeo().getLng()+"'"
                        + ", `companyName`="+ "'"+user.getCompany().getName()+"'"
                        + ", `catchPhrase`="+ "'"+user.getCompany().getCatchPhrase()+"'"
                        + ", `bs`="+ "'"+user.getCompany().getBs()+"'"
                        + "WHERE (`id`='"+user.getId()+"');");
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
            if(res>0){
                System.out.println("Usuário "+user.getId()+" Atualizado!");
            }
            return res>0;
        }else{
            System.out.println("MySQL não conectado!");
            return false;
        }
    }
    
    public boolean deleteUser(Integer userId) throws SQLException{
        if(mysql_con!=null){
            int res = 0;
            Statement stat = mysql_con.createStatement();
            try{
                res = stat.executeUpdate("DELETE FROM `LabProg`.`Users` WHERE (`id` = '"+userId+"');");
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
            if(res>0){
                System.out.println("Usuário "+userId+" Deletado!");
            }
            return res>0;
        }else{
            System.out.println("MySQL não conectado!");
            return false;
        }
    }
    
    public User getUser(Integer userId) throws SQLException{
        if(mysql_con!=null){
            Statement stat = mysql_con.createStatement();
            User user = new User();
            try{
                ResultSet data = stat.executeQuery("SELECT * FROM LabProg.Users WHERE id="+userId+"");
                while(data.next()){
                    user.setId(data.getInt("id"));
                    user.setName(data.getString("name"));
                    user.setUsername(data.getString("username"));
                    user.setEmail(data.getString("email"));
                    user.setPhone(data.getString("phone"));
                    user.setWebsite(data.getString("website"));

                    Georeference geo = new Georeference();
                    geo.setLat(data.getString("geo_lat"));
                    geo.setLng(data.getString("geo_lng"));

                    Address address = new Address();
                    address.setCity(data.getString("city"));
                    address.setStreet(data.getString("street"));
                    address.setSuite(data.getString("suite"));
                    address.setZipcode(data.getString("zipcode"));
                    address.setGeo(geo);

                    Company company = new Company();
                    company.setName(data.getString("companyName"));
                    company.setCatchPhrase(data.getString("catchPhrase"));
                    company.setBs(data.getString("bs"));

                    user.setAddress(address);
                    user.setCompany(company);
                    break;
                }

                System.out.println("Usuário "+userId+" retornado!");

                return user;

            }catch(Exception e){
                System.out.println("Error: "+e);
                return null;
            }
        }else{
            System.out.println("MySQL não conectado!");
            return null;
        }
    }
    
    public List<User> getAllUsers() throws SQLException{
        if(mysql_con!=null){
            Statement stat = mysql_con.createStatement();
            List<User> users = new ArrayList<User>();
            try{
                ResultSet data = stat.executeQuery("SELECT * FROM LabProg.Users");
                if(!data.next()){
                    System.out.println("O BD não possui usuários cadastrados!");
                    return null;
                }

                do{
                    User user = new User();
                    user.setId(data.getInt("id"));
                    user.setName(data.getString("name"));
                    user.setUsername(data.getString("username"));
                    user.setEmail(data.getString("email"));
                    user.setPhone(data.getString("phone"));
                    user.setWebsite(data.getString("website"));

                    Georeference geo = new Georeference();
                    geo.setLat(data.getString("geo_lat"));
                    geo.setLng(data.getString("geo_lng"));

                    Address address = new Address();
                    address.setCity(data.getString("city"));
                    address.setStreet(data.getString("street"));
                    address.setSuite(data.getString("suite"));
                    address.setZipcode(data.getString("zipcode"));
                    address.setGeo(geo);

                    Company company = new Company();
                    company.setName(data.getString("companyName"));
                    company.setCatchPhrase(data.getString("catchPhrase"));
                    company.setBs(data.getString("bs"));

                    user.setAddress(address);
                    user.setCompany(company);
                    users.add(user);
                }while(data.next());

                System.out.println("Retornado todos os usuários!");

                return users;

            }catch(Exception e){
                System.out.println("Error: "+e);
                return null;
            }
        }else{
            System.out.println("MySQL não conectado!");
            return null;
        }
    }
}

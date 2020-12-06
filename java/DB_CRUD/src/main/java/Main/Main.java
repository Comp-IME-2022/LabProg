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
import java.util.ArrayList;
import java.util.Scanner;

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
    public static void main(String[] args) throws IOException, SQLException, InterruptedException{
        //Connect to MySQL server
        MySQL mysql = new MySQL();
        Connection mysql_con = mysql.connect(); 
        
        //Connect to MongoDB server
        Mongo mongo = new Mongo();
        MongoClient mongo_con = mongo.connect();
        
        Thread.sleep(100);
        
        System.out.println("MySQL Connected...");
        System.out.println("MongoDB Connected...\n");
        
        //Main execution
        Scanner input = new Scanner(System.in);
        selectDB(input, chooseSGBD(input), mysql, mongo);
        
        //Closing connections
        mysql.close(mysql_con);
        mongo.close(mongo_con);
    }
    
    public static Integer chooseSGBD(Scanner input){
        System.out.println("Selecione o SGBD que deseja utilizar:\n[1] MySQL\n[2] MongoDB");
        Integer DB = input.nextInt();
        return DB;
    }
    
    public static void selectDB(Scanner input, Integer opt, MySQL mysql, Mongo mongo) throws SQLException, IOException{
        switch(opt){
            case 1:
                System.out.println("\n=============== MySQL ===============");
                break;
            case 2:
                System.out.println("\n=============== MongoDB ===============");
                break;
            default:
                System.out.println("Opção inválida");
                selectDB(input, chooseSGBD(input), mysql, mongo);
        }
        
        Integer handler = 0;
        while(handler<7){
            handler = handleDB(input, opt, mysql, mongo);
        }
        if(handler==7){
            selectDB(input, chooseSGBD(input), mysql, mongo);
        }
    }
    
    public static Integer handleDB(Scanner input, Integer opt, MySQL mysql, Mongo mongo) throws SQLException, IOException{
        System.out.println("Selecione a ação que deseja efetuar:\n"
            + "[1] Listar Usuários\n"
            + "[2] Buscar Usuário\n"
            + "[3] Cadastrar Usuário\n"
            + "[4] Atualizar Usuário\n"
            + "[5] Deletar Usuário\n"
            + "[6] Atualizar BD online\n"
            + "[7] Escolher outro SGBD\n"
            + "[8] Sair"
        );
        Integer select = input.nextInt();
        System.out.println("");
        
        Integer userId;
        User user;
        List<User> users;
        Georeference geo;
        Address address;
        Company company;
        
        switch(select){
            case 1:
                if(opt==1){
                    users=mysql.getAllUsers();
                }else{
                    users=mongo.getAllUsers();
                }
                if(users!=null){
                    System.out.println("********** Usuários **********");
                    for(User newUser : users){
                        System.out.println(newUser.getId()+" - "+newUser.getName());
                        }
                    System.out.println("******************************\n");
                }else{
                    System.out.println("");
                }
                
                break;
            case 2:
                System.out.println("Digite o ID do usuário:");
                userId = input.nextInt();
                System.out.println("");
                if(opt==1){
                    user=mysql.getUser(userId);
                }else{
                    user=mongo.getUser(userId);
                }
                System.out.println(user);
                break;
            case 3:
                user = new User();

                System.out.println("Entre com as informações requeridas\nID:");
                user.setId(input.nextInt());
                System.out.println("Nome:");
                input.nextLine();
                user.setName(input.nextLine());
                System.out.println("Usuário:");
                user.setUsername(input.nextLine());
                System.out.println("Email:");
                user.setEmail(input.nextLine());
                System.out.println("Telefone:");
                user.setPhone(input.nextLine());
                System.out.println("Website:");
                user.setWebsite(input.nextLine());

                geo = new Georeference();
                System.out.println("Latitude:");
                geo.setLat(input.nextLine());
                System.out.println("Longitude:");
                geo.setLng(input.nextLine());

                address = new Address();
                System.out.println("Cidade:");
                address.setCity(input.nextLine());
                System.out.println("Rua:");
                address.setStreet(input.nextLine());
                System.out.println("Complemento:");
                address.setSuite(input.nextLine());
                System.out.println("ZipCode:");
                address.setZipcode(input.nextLine());
                address.setGeo(geo);

                company = new Company();
                System.out.println("Nome Empresa:");
                company.setName(input.nextLine());
                System.out.println("Catch Phrase:");
                company.setCatchPhrase(input.nextLine());
                System.out.println("BS:");
                company.setBs(input.nextLine());

                user.setAddress(address);
                user.setCompany(company);
                
                if(opt==1){
                    mysql.createUser(user);
                }else{
                    mongo.createUser(user);
                }
                
                System.out.println("");
                break;
            case 4:
                System.out.println("Digite o ID do usuário:");
                userId = input.nextInt();
                System.out.println("");
                
                if(opt==1){
                    user=mysql.getUser(userId);
                }else{
                    user=mongo.getUser(userId);
                }
                System.out.println(user);
                
                System.out.println("Entre com as novas informações do usuários:\nID:");
                user.setId(input.nextInt());
                System.out.println("Nome:");
                input.nextLine();
                user.setName(input.nextLine());
                System.out.println("Usuário:");
                user.setUsername(input.nextLine());
                System.out.println("Email:");
                user.setEmail(input.nextLine());
                System.out.println("Telefone:");
                user.setPhone(input.nextLine());
                System.out.println("Website:");
                user.setWebsite(input.nextLine());

                geo = new Georeference();
                System.out.println("Latitude:");
                geo.setLat(input.nextLine());
                System.out.println("Longitude:");
                geo.setLng(input.nextLine());

                address = new Address();
                System.out.println("Cidade:");
                address.setCity(input.nextLine());
                System.out.println("Rua:");
                address.setStreet(input.nextLine());
                System.out.println("Complemento:");
                address.setSuite(input.nextLine());
                System.out.println("ZipCode:");
                address.setZipcode(input.nextLine());
                address.setGeo(geo);

                company = new Company();
                System.out.println("Nome Empresa:");
                company.setName(input.nextLine());
                System.out.println("Catch Phrase:");
                company.setCatchPhrase(input.nextLine());
                System.out.println("BS:");
                company.setBs(input.nextLine());

                user.setAddress(address);
                user.setCompany(company);
                
                if(opt==1){
                    mysql.updateUser(user);
                }else{
                    mongo.updateUser(user);
                }
                
                System.out.println("");
                break;
            case 5:
                System.out.println("Digite o ID do usuário:");
                userId = input.nextInt();
                System.out.println("");
                
                if(opt==1){
                    mysql.deleteUser(userId);
                }else{
                    mongo.deleteUser(userId);
                }
                
                System.out.println("");
                break;
            case 6:
                ObjectMapper objectMapper = new ObjectMapper();
                users = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/users"), new TypeReference<List<User>>(){});

                for(User newUser : users){
                    if(opt==1){
                        mysql.createUser(newUser);
                    }else{
                       mongo.createUser(newUser);
                    }
                }
                
                System.out.println("");
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                break;
        }
        
        return select;
    } 
}

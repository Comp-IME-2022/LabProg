/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

/**
 *
 * @author davi
 */

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
        
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("\n*********** User ***********\n");
        sb.append("id: "+getId()+"\n");
        sb.append("name: "+getName()+"\n");
        sb.append("username: "+getUsername()+"\n");
        sb.append("email: "+getEmail()+"\n");
        sb.append(getAddress()+"\n");
        sb.append("phone: "+getPhone()+"\n");
        sb.append("website: "+getWebsite()+"\n");
        sb.append(getCompany()+"\n");
        sb.append("*****************************");

        return sb.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

/**
 * @author davi
 */

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Georeference geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Georeference getGeo() {
        return geo;
    }

    public void setGeo(Georeference geo) {
        this.geo = geo;
    }
    
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("Address:\n");
        sb.append("  street: "+getStreet()+"\n");
        sb.append("  suite: "+getSuite()+"\n");
        sb.append("  city: "+getCity()+"\n");
        sb.append("  zipcode: "+getZipcode()+"\n");
        sb.append(getGeo());

        return sb.toString();
    }
}

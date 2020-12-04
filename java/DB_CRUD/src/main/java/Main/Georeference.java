/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

/**
 * @author davi
 */
public class Georeference {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("  GeoReference:\n");
        sb.append("    Lat: "+getLat()+"\n");
        sb.append("    Lng: "+getLng());

        return sb.toString();
    }
}

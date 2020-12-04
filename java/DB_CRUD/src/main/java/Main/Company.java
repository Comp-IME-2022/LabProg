/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

/**
 * @author davi
 */
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
    
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("company:\n");
        sb.append("  name: "+getName()+"\n");
        sb.append("  catchPhrase: "+getCatchPhrase()+"\n");
        sb.append("  bs: "+getBs());

        return sb.toString();
    }
}

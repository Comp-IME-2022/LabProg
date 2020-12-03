/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author davi
 */
public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;
	
    public int getId() {
            return id;
    }
    public void setId(int id) {
            this.id = id;
    }
    public int getUserId() {
            return userId;
    }
    public void setUserId(int userId) {
            this.userId = userId;
    }
    public String getTitle() {
            return title;
    }
    public void setTitle(String title) {
            this.title = title;
    }
    public String getBody() {
            return body;
    }
    public void setCompleted(String body) {
            this.body = body;
    }

    @Override
    public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("\n*********** Post ***********\n");
            sb.append("ID="+getId()+"\n");
            sb.append("User ID="+getUserId()+"\n");
            sb.append("Title="+getTitle()+"\n");
            sb.append("Body="+getBody()+"\n");
            sb.append("*****************************");

            return sb.toString();
    }
}

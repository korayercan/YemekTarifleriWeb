import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.DataSource;

import javax.annotation.Resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

@ManagedBean( name="comment" )
@SessionScoped
public class Comment{
    private Vector<Comment> v = new Vector();
    private Comment reqcomment;
    private int commentid;
    private int userid;
    private int recipeid;
    private String content;
    private String username;
    

    public Comment getReqcomment(){
        return reqcomment;
    }
    
    public Vector getV(){
        return v;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername( String username ){
        this.username = username;
    }
    public int getRecipeid(){
        return recipeid;
    } 
    public void setRecipeid( int recipeid ){
        this.recipeid = recipeid;
    } 

    public int getCommentid(){
        return commentid;
    }
    public void setCommentid( int commentid ){
        this.commentid = commentid;
    } 
    
    public int getUserid(){
        return userid;
    }
    public void setUserid( int userid ){
        this.userid = userid;
    }
    public String getContent(){
        return content;
    }
    public void setContent( String content ){
        this.content = content;
    }
    
    public void contentlistener(ValueChangeEvent e){
        this.content = e.getNewValue().toString();
    }
    
    public void comments(int id) throws SQLException{
        Connection baglanti=null;
        
        try{
            baglanti=DriverManager.getConnection("jdbc:derby://localhost:1527/YemekTarifiSitesiDB", "admin1", "admin");
            Statement showstate=baglanti.createStatement();
            String s= "SELECT USERNAME, COMMENT_ID , RECIPE_ID, CONTENT FROM ADMIN1.COMMENTS C INNER JOIN ADMIN1.USERS U ON C.USER_IDD = U.USER_ID ";
            ResultSet resultSet1=showstate.executeQuery(s);
             while(resultSet1.next()){
                if(resultSet1.getInt("RECIPE_ID") == id ){
                    Comment comment1 = new Comment();
                    comment1.setRecipeid(resultSet1.getInt("RECIPE_ID"));
                    comment1.setCommentid(resultSet1.getInt("COMMENT_ID"));
                    
                    comment1.setContent(resultSet1.getString("CONTENT"));
                    comment1.setUsername(resultSet1.getString("USERNAME"));
                    v.add(comment1);
                } 
            }
        } 
        finally{
            baglanti.close(); 
        } 
    }
    public void v_delete(){
        v.clear();
    }
    public String yorumyap(int userid) throws SQLException{
        Connection baglanti=null;

        try{
            baglanti=DriverManager.getConnection("jdbc:derby://localhost:1527/YemekTarifiSitesiDB", "admin1", "admin");
            Statement showstate=baglanti.createStatement();
            showstate.executeUpdate("INSERT INTO ADMIN1.COMMENTS " + "(RECIPE_ID,CONTENT,USER_IDD) VALUES ("+v.get(0).recipeid+ " , '"+content+"', "+userid+")");

            return "detay"; // go back to detay.xhtml page
        } // end try
        finally{
            baglanti.close(); 
        } 
    }
}
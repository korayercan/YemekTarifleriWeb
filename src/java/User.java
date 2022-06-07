import javax.faces.event.ValueChangeEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean( name="user" )
@SessionScoped 
public class User {
    private int userid;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String email;
    private String profileimage;
    
    
    public int getUserid(){
        return userid;
    } 
    public void setUserid( int userid ){
        this.userid = userid;
    } 

    public String getFname(){
        return fname;
    }
    public void setFname( String fname ){
        this.fname = fname;
    } 
    
    public String getLname(){
        return lname;
    }
    public void setLname( String lname ){
        this.lname = lname;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername( String username ){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    public void setPassword( String password ){
        this.password = password;
    } 

    public String getEmail(){
        return email;
    }
    public void setEmail( String email ){
        this.email = email;
    } 

    public String getProfileimage(){
        return profileimage;
    }
    public void setProfileimage( String profileimage ){
        this.profileimage = profileimage;
    }
    public void usernamelistener(ValueChangeEvent e){
        this.username = e.getNewValue().toString();
    }
    public void fnamelistener(ValueChangeEvent e){
        this.fname = e.getNewValue().toString();
    }
    public void lnamelistener(ValueChangeEvent e){
        this.lname = e.getNewValue().toString();
    }
    public void emaillistener(ValueChangeEvent e){
        this.email = e.getNewValue().toString();
    }
    public void passwordlistener(ValueChangeEvent e){
        this.password = e.getNewValue().toString();
    }
    public String giris() throws SQLException{
        Connection baglanti=null;
        
        try{
            baglanti=DriverManager.getConnection("jdbc:derby://localhost:1527/YemekTarifiSitesiDB", "admin1", "admin");
            Statement showstate=baglanti.createStatement();
            String s= "SELECT * FROM ADMIN1.USERS" ;
            ResultSet resultSet1=showstate.executeQuery(s);
            while(resultSet1.next()){
                if(password.equals(resultSet1.getString("PASSWORD")) && username.equals(resultSet1.getString("USERNAME"))){
                    this.userid = resultSet1.getInt("USER_ID");
                    this.fname = resultSet1.getString("FNAME");
                    this.lname = resultSet1.getString("LNAME");
                    this.username = resultSet1.getString("USERNAME");
                    this.password = resultSet1.getString("PASSWORD");
                    this.email = resultSet1.getString("EMAİL");
                    this.profileimage = resultSet1.getString("PROFILE_IMAGE");
                    return "main";
                }
            }
            return "index";
        } 
        finally{
            baglanti.close(); 
        } 
    }
    public String kaydol() throws SQLException{
        Connection baglanti=null;

        try{
            baglanti=DriverManager.getConnection("jdbc:derby://localhost:1527/YemekTarifiSitesiDB", "admin1", "admin");
            Statement showstate=baglanti.createStatement();
            showstate.executeUpdate("INSERT INTO ADMIN1.USERS " + "(FNAME,LNAME,USERNAME,PASSWORD ,EMAIL) VALUES ('"+fname+ "' , '"+lname+"', '"+username+"' , '"+password+"', '"+email+"')");

            return "index"; // go back to index.xhtml page
        } // end try
        finally{
            baglanti.close(); 
        } 
    }
}


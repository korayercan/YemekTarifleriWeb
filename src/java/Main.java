import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.DataSource;

import javax.annotation.Resource;

import javax.faces.bean.ManagedBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

@ManagedBean( name="main" )

public class Main{
    private String recipeıd;
    private String name;
    private String shortdesc;
    private String detail;
    private String ingridients;
    private String image;
    private String categoryıd;

    DataSource dataSource;

    public Main(){
        try{
        Context ctx = new InitialContext();
        // kullandığımız database adı addressbook.
            NamingEnumeration<NameClassPair> names = (NamingEnumeration<NameClassPair>) ctx.list("");
            while(names.hasMore()){
                System.out.println("OK"+names.next());
            } 
        }
        catch (NamingException e){
            e.printStackTrace();
        }
    }

    public String getRecipeıd(){
        return recipeıd;
    } 
    public void setRecipeıd( String recipeıd ){
        this.recipeıd = recipeıd;
    } 

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    } 

    public String getShortdesc(){
        return shortdesc;
    }

    public void setPassword( String shortdesc ){
        this.shortdesc = shortdesc;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail( String detail ){
        this.detail = detail;
    } 

    public String getIngridients(){
        return ingridients;
    }

    public void setIngridients( String ingridients ){
        this.ingridients = ingridients;
    } 

    public String getImage(){
        return image;
    }

    public void setImage( String image ){
        this.image = image;
    }

    public String getCategoryıd(){
        return categoryıd;
    }

    public void setCategoryıd( String categoryıd ){
        this.categoryıd = categoryıd;
    }

    public ResultSet getAddresses() throws SQLException{

        if ( dataSource == null )
        throw new SQLException( "Unable to obtain DataSource" );

        Connection connection = dataSource.getConnection();

        if ( connection == null )
        throw new SQLException( "Unable to connect to DataSource" );

        try{
            PreparedStatement object1 = connection.prepareStatement(
            "SELECT FIRSTNAME, LASTNAME, STREET, CITY, STATE, ZIP " +
            "FROM ADDRESSES ORDER BY LASTNAME, FIRSTNAME" );
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate( object1.executeQuery() );
            return resultSet1;
        } 
        finally{
            connection.close(); 
        } 
    } 
}
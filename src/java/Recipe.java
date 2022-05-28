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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

@ManagedBean( name="main" )
@SessionScoped
public class Recipe{
    private Vector<Recipe> v = new Vector();
    private Recipe reqrecipe;
    private int recipeid;
    private String name;
    private String shortdesc;
    private String detail;
    private String ingridients;
    private String image;
    private int categoryıd;

    public Recipe getReqrecipe(){
        return reqrecipe;
    }
    
    public Vector getV(){
        return v;
    }
    
    public int getRecipeid(){
        return recipeid;
    } 
    public void setRecipeid( int recipeıd ){
        this.recipeid = recipeıd;
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
    public void setShortdesc( String shortdesc ){
        this.shortdesc = shortdesc;
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

    public int getCategoryıd(){
        return categoryıd;
    }

    public void setCategoryıd( int categoryıd ){
        this.categoryıd = categoryıd;
    }

    public ResultSet getTarifler() throws SQLException{
        Connection baglanti=null;
        
        try{
            baglanti=DriverManager.getConnection("jdbc:derby://localhost:1527/YemekTarifiSitesiDB", "admin1", "admin");
            Statement showstate=baglanti.createStatement();
            String s= "SELECT * FROM ADMIN1.RECIPES";
            ResultSet resultSet1=showstate.executeQuery(s);
            while(resultSet1.next()){
                Recipe recipe1 = new Recipe();
                recipe1.setRecipeid(resultSet1.getInt("RECIPE_ID"));
                recipe1.setName(resultSet1.getString("NAME"));
                recipe1.setShortdesc(resultSet1.getString("SHORT_DESC"));
                recipe1.setDetail(resultSet1.getString("DETAIL"));
                recipe1.setIngridients(resultSet1.getString("INGRIDIENTS"));
                recipe1.setImage(resultSet1.getString("IMAGE"));
                recipe1.setCategoryıd(resultSet1.getInt("CATEGORY_ID"));
                v.add(recipe1);
            }
            return resultSet1;
        } 
        finally{
            baglanti.close(); 
        } 
    }
    
    public String showReq(int id) throws SQLException{
        for(int i = 0; i < v.size();i++){
            if(id == v.get(i).recipeid){
                reqrecipe = new Recipe();
                reqrecipe.categoryıd = v.get(i).categoryıd;
                reqrecipe.detail = v.get(i).detail;
                reqrecipe.image = v.get(i).image;
                reqrecipe.ingridients = v.get(i).ingridients;
                reqrecipe.name = v.get(i).name;
                reqrecipe.shortdesc = v.get(i).shortdesc;
                reqrecipe.recipeid = v.get(i).recipeid;
                return "detay";
            }
        }
        return null;
    }

}
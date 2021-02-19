package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Jordi Hernandez i Gerard Lopez */

public class Article {

    private int id;
    private String code;
    private String name;
    private float price;

    public Article(int id, String code, String name, float price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
    
    public Article() {
    	
    }
    
    public Article(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getInt("article_id"));
		this.setCode(resultSet.getString("code"));
		this.setName(resultSet.getString("name"));
		this.setPrice(resultSet.getFloat("price"));
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
    	return code;
    }
    
    public void setCode(String code) {
    	this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

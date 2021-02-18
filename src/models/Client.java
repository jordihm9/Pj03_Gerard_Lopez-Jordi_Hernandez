package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

/* Jordi Hernandez i Gerard Lopez */

public class Client {

    private int id;
    private String nif;
    private String name;
    private String lastname;
    private String address;
    private String town;
    
    public Client() {
    	
    }

    public Client(int id, String nif, String name, String lastname, String adress, String town) {
        this.id = id;
        this.nif = nif;
        this.name = name;
        this.lastname = lastname;
        this.address = adress;
        this.town = town;
    }
    
    public Client(ResultSet resultSet) throws SQLException {
    	this.setId(resultSet.getInt("id"));
    	this.setNif(resultSet.getString("nif"));
    	this.setName(resultSet.getString("lastname"));
    	this.setAddress(resultSet.getString("addess"));
    	this.setTown(resultSet.getString("town"));
    }
    
    public Client(HttpServletRequest request) {
    	this.setId(Integer.parseInt(request.getParameter("id")));
    	this.setNif(request.getParameter("nif"));
    	this.setName(request.getParameter("name"));
    	this.setLastname(request.getParameter("lastname"));
    	this.setAddress(request.getParameter("address"));
    	this.setTown(request.getParameter("town"));
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}

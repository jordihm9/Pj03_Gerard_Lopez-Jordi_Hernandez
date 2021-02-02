package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Jordi Hernandez i Gerard Lopez */

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

public class Invoice {

    private int id;
    private Date date;
    private boolean paid;
    private float taxable_base;
    private float iva;
    private float discount;
    private float total;
    private int client_id;

    public Invoice() {
    	
    }
    
    public Invoice(int id, Date date, boolean paid, float taxable_base, float iva, float discount, float total, int client_id) {
        this.id = id;
        this.date = date;
        this.paid = paid;
        this.taxable_base = taxable_base;
        this.iva = iva;
        this.discount = discount;
        this.total = total;
        this.client_id = client_id;
    }
    
    public Invoice(ResultSet resultset) throws SQLException {
    	this.setId(resultset.getInt("id"));
    	this.setDate(resultset.getDate("date"));
    	this.setPaid(resultset.getBoolean("paid"));
    	this.setTaxable_base(resultset.getFloat("taxable_base"));
    	this.setIva(resultset.getFloat("iva"));
    	this.setDiscount(resultset.getFloat("discount"));
    	this.setTotal(resultset.getFloat("total"));
    	this.setClient_id(resultset.getInt("client_id"));
    }
    
    public Invoice(HttpServletRequest request){
    	this.setId(Integer.parseInt(request.getParameter("id")));
    	this.setDate(Date.valueOf(request.getParameter("date")));
    	this.setPaid(Boolean.getBoolean(request.getParameter("paid")));
    	this.setTaxable_base(Float.parseFloat(request.getParameter("taxable_base")));
    	this.setIva(Float.parseFloat(request.getParameter("iva")));
    	this.setDiscount(Float.parseFloat(request.getParameter("discount")));
    	this.setTotal(Float.parseFloat(request.getParameter("total")));
    	this.setClient_id(Integer.parseInt(request.getParameter("client_id")));  	
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public float getTaxable_base() {
        return taxable_base;
    }

    public void setTaxable_base(float taxable_base) {
        this.taxable_base = taxable_base;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}

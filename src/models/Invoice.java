package models;

/* Jordi Hernandez i Gerard Lopez */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import dao.ClientDAO;

public class Invoice {

    private int id;
    private Date date;
    private boolean paid;
    private float taxableBase;
    private float iva;
    private float ivaImport;
    private float discount;
    private float discountImport;
    private float total;
    private Client client;

    public Invoice() {
    	
    }
    
    public Invoice(int id, Date date, boolean paid, float taxableBase, float iva, float ivaImport, float discount, float discountImport, float total, Client client) {
        this.id = id;
        this.date = date;
        this.paid = paid;
        this.taxableBase = taxableBase;
        this.iva = iva;
        this.ivaImport = ivaImport;
        this.discount = discount;
        this.discountImport = discountImport;
        this.total = total;
        this.client = client;
    }
    
    public Invoice(ResultSet resultset) throws SQLException {
    	try {    		
    		this.setId(resultset.getInt("invoice_id"));
    	} catch (NumberFormatException e) {
    		this.id = 0;
    	}
    	this.setDate(resultset.getDate("date"));
    	this.setPaid(resultset.getBoolean("paid"));
    	this.setTaxableBase(resultset.getFloat("taxable_base"));
    	this.setIva(resultset.getFloat("iva"));
    	this.setIvaImport(resultset.getFloat("iva_import"));
    	this.setDiscount(resultset.getFloat("discount"));
    	this.setDiscountImport(resultset.getFloat("discount_import"));
    	this.setTotal(resultset.getFloat("total"));
        
    	this.client = new Client(resultset);
    }
    
    public Invoice(HttpServletRequest request) throws SQLException {
    	try {    		
    		this.setId(Integer.parseInt(request.getParameter("invoiceId")));
    	} catch (NumberFormatException e) {
    		this.id = 0;
    	}
    	this.setDate(Date.valueOf(request.getParameter("invoiceDate")));
    	this.setPaid(Boolean.getBoolean(request.getParameter("paid")));
    	this.setTaxableBase(Float.parseFloat(request.getParameter("taxableBase")));
    	this.setIva(Float.parseFloat(request.getParameter("iva")));
    	this.setIvaImport(Float.parseFloat(request.getParameter("ivaImport")));
    	this.setDiscount(Float.parseFloat(request.getParameter("discount")));
    	this.setDiscountImport(Float.parseFloat(request.getParameter("discountImport")));
    	this.setTotal(Float.parseFloat(request.getParameter("total")));

        // int client_id = Integer.parseInt(request.getParameter("client_id"));
        // this.client = ClientDAO.selectById(client_id);
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

    public float getTaxableBase() {
        return taxableBase;
    }

    public void setTaxableBase(float taxableBase) {
        this.taxableBase = taxableBase;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }
    
    public float getIvaImport() {
    	return ivaImport;
    }

    public void setIvaImport(float ivaImport) {
    	this.ivaImport = ivaImport;
    }
    
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    
    public float getDiscountImport() {
        return discountImport;
    }

    public void setDiscountImport(float discountImport) {
        this.discountImport = discountImport;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

package models;

/* Jordi Hernandez i Gerard Lopez */

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.ArticleDAO;
import dao.InvoiceDAO;


public class InvoiceDetail {

    private int id;
    private int line_number;
    private int total_articles;
    private float line_price;
    private Article article;
    private Invoice invoice;
      
    public InvoiceDetail() {
    	
    }

    public InvoiceDetail(int id, int line_number, int total_articles, float line_price, Article article, Invoice invoice) {
        this.id = id;
        this.line_number = line_number;
        this.total_articles = total_articles;
        this.line_price = line_price;
        this.article = article;
        this.invoice = invoice;
    }
    
    public InvoiceDetail(ResultSet resultSet) throws SQLException {
    	this.setId(resultSet.getInt("invoice_detail_id"));
    	this.setLine_number(resultSet.getInt("line_number"));
    	this.setTotal_articles(resultSet.getInt("total_articles"));
    	this.setLine_price(resultSet.getFloat("line_price"));

        this.article = new Article(resultSet);
        this.invoice = new Invoice(resultSet);
    }
    
    public InvoiceDetail(HttpServletRequest request) throws SQLException {
    	this.setId(Integer.parseInt(request.getParameter("id")));
    	this.setLine_number(Integer.parseInt(request.getParameter("line_number")));
    	this.setTotal_articles(Integer.parseInt(request.getParameter("total_articles")));
    	this.setLine_price(Float.parseFloat(request.getParameter("line_price")));
    	
    	int article_id = Integer.parseInt(request.getParameter("article_id"));
    	this.article = ArticleDAO.selectById(article_id);
    	
    	int invoice_id = Integer.parseInt(request.getParameter("invoice_id"));
    	this.invoice = InvoiceDAO.selectById(invoice_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine_number() {
        return line_number;
    }

    public void setLine_number(int line_number) {
        this.line_number = line_number;
    }

    public int getTotal_articles() {
        return total_articles;
    }

    public void setTotal_articles(int total_articles) {
        this.total_articles = total_articles;
    }

    public float getLine_price() {
        return line_price;
    }

    public void setLine_price(float line_price) {
        this.line_price = line_price;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}

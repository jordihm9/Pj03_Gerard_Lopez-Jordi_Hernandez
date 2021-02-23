package models;

/* Jordi Hernandez i Gerard Lopez */

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.ArticleDAO;
import dao.InvoiceDAO;


public class InvoiceDetail {

    private int id;
    private int lineNumber;
    private int totalArticles;
    private float linePrice;
    private Article article;
    private Invoice invoice;
      
    public InvoiceDetail() {
    	
    }

    public InvoiceDetail(int id, int lineNumber, int totalArticles, float linePrice, Article article, Invoice invoice) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.totalArticles = totalArticles;
        this.linePrice = linePrice;
        this.article = article;
        this.invoice = invoice;
    }
    
    public InvoiceDetail(ResultSet resultSet) throws SQLException {
    	this.setId(resultSet.getInt("invoice_detail_id"));
    	this.setLineNumber(resultSet.getInt("line_number"));
    	this.setTotalArticles(resultSet.getInt("total_articles"));
    	this.setLinePrice(resultSet.getFloat("line_price"));

        this.article = new Article(resultSet);
        this.invoice = new Invoice(resultSet);
    }
    
    public InvoiceDetail(HttpServletRequest request) throws SQLException {
    	this.setLineNumber(Integer.parseInt(request.getParameter("lineNumber")));
    	this.setTotalArticles(Integer.parseInt(request.getParameter("totalArticles")));
    	this.setLinePrice(Float.parseFloat(request.getParameter("linePrice")));
    	
    	String articleCode = request.getParameter("articleCode");
    	this.article = ArticleDAO.selectByCode(articleCode);
    	
    	int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
    	this.invoice = InvoiceDAO.selectById(invoiceId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int line_number) {
        this.lineNumber = line_number;
    }

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int total_articles) {
        this.totalArticles = total_articles;
    }

    public float getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(float line_price) {
        this.linePrice = line_price;
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

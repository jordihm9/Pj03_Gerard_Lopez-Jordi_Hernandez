/* Jordi Hernandez i Gerard Lopez */

public class InvoiceDetail {

    private int id;
    private int line_number;
    private int total_articles;
    private float line_price;
    private int article_id;
    private int invoice_id;

    public InvoiceDetail(int id, int line_number, int total_articles, float line_price, int article_id, int invoice_id) {
        this.id = id;
        this.line_number = line_number;
        this.total_articles = total_articles;
        this.line_price = line_price;
        this.article_id = article_id;
        this.invoice_id = invoice_id;
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

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }
}

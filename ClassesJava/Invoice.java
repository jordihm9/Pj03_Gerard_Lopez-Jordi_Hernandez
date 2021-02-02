/* Jordi Hernandez i Gerard Lopez */

import java.util.Date;

public class Invoice {

    private int id;
    private Date date;
    private boolean paid;
    private float taxable_base;
    private float iva;
    private float discount;
    private float total;
    private int client_id;

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

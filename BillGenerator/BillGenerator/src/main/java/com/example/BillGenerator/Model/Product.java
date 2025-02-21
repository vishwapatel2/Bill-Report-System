package com.example.BillGenerator.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private int pid;
    @Column(name = "product_name")
    private String pName;
    @Column(name = "mrp")
    private  Double mrp;
    @Column(name = "GST")
    private double gst;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Order> order;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Bill> bills;


    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}

package com.example.BillGenerator.Model;

import jakarta.persistence.*;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    @Column(name = "GST")
    private double gst;
    @Column(name = "mrp")
    private double mrp;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne     //one customer has many bills
    private Customer customer;  //cid
    @OneToOne       //one order has only one bill
    private Order order;    //oid
    @ManyToOne      //one product has many bills
    private Product product; //pid

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

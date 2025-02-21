package com.example.BillGenerator.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "oid")
    private int oid;
    @Column(name = "quantity")
    private int quantity;
//    @Column(name = "mrp")
//    private double mrp;
//    @Column(name = "gst")
//    private double gst;
//    @Column(name = "final_price")
//    private double finalPrice;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;

//    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
//    private List<Bill> bills;

//    public List<Bill> getBills() {
//        return bills;
//    }
//
//    public void setBills(List<Bill> bills) {
//        this.bills = bills;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



//    public double getMrp() {
//        return mrp;
//    }
//
//    public void setMrp(double mrp) {
//        this.mrp = mrp;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


//    public double getGst() {
//        return gst;
//    }
//
//    public double setGst(double gst) {
//        this.gst = gst;
//        return gst;
//    }
//
//    public double getFinalPrice() {
//        return finalPrice;
//    }
//
//    public void setFinalPrice(double finalPrice) {
//        this.finalPrice = finalPrice;
//    }
}

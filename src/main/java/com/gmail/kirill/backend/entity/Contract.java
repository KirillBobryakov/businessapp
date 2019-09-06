package com.gmail.kirill.backend.entity;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Date;

@NodeEntity(label = "CONTRACT")
public class Contract extends Entity{

    private String number;
    private Date date;
    private String currency;
    private Float amount;

    @Property(name = "expire_date")
    private Date expireDate;
    private String ucn;

    private byte[] image;


    public Contract() {
//        date = new Date(System.currentTimeMillis());
//        expireDate = new Date(System.currentTimeMillis());
//        amount = Float.valueOf("0.00");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getUcn() {
        return ucn;
    }

    public void setUcn(String ucn) {
        this.ucn = ucn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

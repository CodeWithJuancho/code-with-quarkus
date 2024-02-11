package com.vass.domain;

import jakarta.persistence.*;

import java.time.Instant;
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Integer price;

    public Integer priceList;

    public Integer priority;

    @Enumerated(value = EnumType.STRING)
    public CurrencyEnum currency;

    public Instant startDate;

    public Instant endDate;

    @ManyToOne
    public Product product;

    @ManyToOne
    public Brand brand;

    public Price(Integer value, Integer priceList, Integer priority, CurrencyEnum currency, Instant startDate, Instant endDate, Product product, Brand brand) {
        this.price = value;
        this.priceList = priceList;
        this.priority = priority;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
        this.brand = brand;
    }

    public Price() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer value) {
        this.price = value;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}

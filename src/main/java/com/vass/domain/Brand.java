package com.vass.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(value = EnumType.STRING)
    public ProviderEnum provider;

    @OneToMany(mappedBy = "brand")
    @Transient
    public List<Product> products;

    public Brand(ProviderEnum provider) {
        this.provider = provider;
    }

    public Brand() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProviderEnum getProvider() {
        return provider;
    }

    public void setProvider(ProviderEnum provider) {
        this.provider = provider;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

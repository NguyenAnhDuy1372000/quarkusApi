package org.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "productduy")
public class Product extends PanacheEntityBase {
    @Id
    @NotNull
    @Column(name = "id")
    public long id;
    @NotNull
    @Column(name = "brand")
    public String brand;
    @NotNull
    @Column(name = "name")
    public String name;
    @Column(name = "price")
    public double price;

    public Product() {
    }

    public Product(long id, String name, String brand, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

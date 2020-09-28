package com.company.domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name="product_nummer")
    private int nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @Transient
    private List<OVChipkaart> ovChipkaarten;

    @Override
    public String toString() {
        return "Product{" +
                "nummer=" + nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}

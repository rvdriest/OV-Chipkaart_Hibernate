package com.company.domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartnummer;
    @Column(name = "geldig_tot")
    private Date geldigTot;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = { @JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = { @JoinColumn(name = "product_nummer")}
    )
    private List<Product> producten;

    public OVChipkaart() {}

    public OVChipkaart(int kaartnummer, Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.kaartnummer = kaartnummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        producten = new ArrayList<>();
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product) {
        this.producten.add(product);
    }

    @Override
    public String toString() {
        String infoString = String.format("OVChipkaart {#%d Geldig tot: %s, Saldo: %.2f, Klasse: %d, Reiziger {#%d %s. %s %s, geb. %s}}",
                kaartnummer,
                geldigTot,
                saldo,
                klasse,
                reiziger.getId(),
                reiziger.getVoorletters(),
                reiziger.getTussenvoegsel() == null ? "" : reiziger.getTussenvoegsel(),
                reiziger.getAchternaam(),
                reiziger.getGeboorteDatum()).replace("  ", " ");
        if(this.producten != null) {
            for (Product product : this.producten) {
                infoString += String.format("\n  Product {#%d, naam: %s, beschrijving: %s, prijs: %.2f}",
                        product.getNummer(),
                        product.getNaam(),
                        product.getBeschrijving(),
                        product.getPrijs());
            }
        }
        return infoString;
    }
}

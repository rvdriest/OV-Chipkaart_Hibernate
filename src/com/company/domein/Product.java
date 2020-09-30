package com.company.domein;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovChipkaarten;

    public int getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    @Override
    public String toString() {
        String infoString = String.format("Product {#%d, naam: %s, beschrijving: %s, prijs: %.2f}",
                this.nummer,
                this.naam,
                this.beschrijving,
                this.prijs);
        if(this.ovChipkaarten != null) {
            for (OVChipkaart ovChipkaart : this.ovChipkaarten) {
                infoString += String.format("\n  OVChipkaart {#%d Geldig tot: %s, Saldo: %.2f, Klasse: %d, Reiziger {#%d %s. %s %s, geb. %s}}\n",
                        ovChipkaart.getKaartnummer(),
                        ovChipkaart.getGeldigTot(),
                        ovChipkaart.getSaldo(),
                        ovChipkaart.getKlasse(),
                        ovChipkaart.getReiziger().getId(),
                        ovChipkaart.getReiziger().getVoorletters(),
                        ovChipkaart.getReiziger().getTussenvoegsel(),
                        ovChipkaart.getReiziger().getAchternaam(),
                        ovChipkaart.getReiziger().getGeboorteDatum());
            }
        }
        return infoString;
    }
}

package com.company.domein;

import javax.persistence.*;

@Entity
public class Adres {
    @Id
    @Column(name="adres_id")
    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public int getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    @Override
    public String toString() {
        if(reiziger != null)
            return String.format("Adres {#%d %s-%s, Reiziger {#%d %s. %s %s, geb. %s}}",
                id,
                postcode,
                huisnummer,
                reiziger.getId(),
                reiziger.getVoorletters(),
                reiziger.getTussenvoegsel() == null ? "" : reiziger.getTussenvoegsel(),
                reiziger.getAchternaam(),
                reiziger.getGeboorteDatum());

        return String.format("Adres {#%d %s-%s}",
                id,
                postcode,
                huisnummer);
    }
}

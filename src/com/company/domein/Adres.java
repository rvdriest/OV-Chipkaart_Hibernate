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

    @OneToOne(mappedBy = "adres")
    private Reiziger reiziger;

    @Override
    public String toString() {
        return String.format("Adres {#%d %s-%s, Reiziger {#%d %s. %s %s, geb. %s}}",
                id,
                postcode,
                huisnummer,
                reiziger.getId(),
                reiziger.getVoorletters(),
                reiziger.getTussenvoegsel() == null ? "" : reiziger.getTussenvoegsel(),
                reiziger.getAchternaam(),
                reiziger.getGeboorteDatum());
    }
}

package com.company.domein;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @Column(name="reiziger_id")
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboorteDatum;

    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> ovChipkaarten;

    @OneToOne(mappedBy = "reiziger")
    private Adres adres;

    /* Getters */
    public int getId() {
        return id;
    }
    public Date getGeboorteDatum() {
        return geboorteDatum;
    }
    public String getAchternaam() {
        return achternaam;
    }
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public String getVoorletters() {
        return voorletters;
    }

    @Override
    public String toString() {
        String infoString = "";
        if(adres != null) {
            infoString = String.format("Reiziger {#%d %s. %s %s, geb. %s, Adres {#%d %s-%s}",
                    id,
                    voorletters,
                    tussenvoegsel == null ? "" : tussenvoegsel,
                    achternaam,
                    geboorteDatum.toString(),
                    adres.getId(),
                    adres.getPostcode(),
                    adres.getHuisnummer()
            );
        }else {
            infoString = String.format("Reiziger {#%d %s. %s %s, geb. %s, Adres null",
                    id,
                    voorletters,
                    tussenvoegsel == null ? "" : tussenvoegsel,
                    achternaam,
                    geboorteDatum.toString()
            );
        }

        for(OVChipkaart ovChipkaart : ovChipkaarten) {
            infoString += String.format(", {#%d Geldig tot: %s, Saldo: %.2f, Klasse: %d}",
                    ovChipkaart.getKaartnummer(),
                    ovChipkaart.getGeldigTot(),
                    ovChipkaart.getSaldo(),
                    ovChipkaart.getKlasse());
        }

        return infoString + "}";
    }
}

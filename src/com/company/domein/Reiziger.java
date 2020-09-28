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

    @Transient
    private List<OVChipkaart> ovChipkaarten;

    @OneToOne(mappedBy = "adres")
    private Adres adres;

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
        return String.format("Reiziger {#%d %s. %s %s, geb. %s}",
                id,
                voorletters,
                tussenvoegsel == null ? "" : tussenvoegsel,
                achternaam,
                geboorteDatum.toString()
                );
    }
}

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
    @Transient
    private Reiziger reiziger;
    @Transient
    private List<Product> producten;

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaartnummer=" + kaartnummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                '}';
    }
}

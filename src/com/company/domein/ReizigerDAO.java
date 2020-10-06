package com.company.domein;

import java.util.List;

public interface ReizigerDAO {
    void save(Reiziger reiziger);
    void update(Reiziger reiziger);
    void delete(Reiziger reiziger);

    Reiziger findById(int id);
    List<Reiziger> findByGbDatum(String datum);
    List<Reiziger> findAll();
}

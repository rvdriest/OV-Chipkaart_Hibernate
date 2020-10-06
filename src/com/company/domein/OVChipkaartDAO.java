package com.company.domein;

import java.util.List;

public interface OVChipkaartDAO {
    void save(OVChipkaart ovChipkaart);
    void update(OVChipkaart ovChipkaart);
    void delete(OVChipkaart ovChipkaart);

    List<OVChipkaart> findByReiziger(Reiziger reiziger);
    List<OVChipkaart> findAll();
}

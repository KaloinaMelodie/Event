package com.example.event.artiste;

import com.example.event.devis.FicheDevis;
import com.example.event.typePrestation.TypePrestation;
import com.example.event.v_artiste.V_artiste;
import com.example.event.v_prestation.V_prestation;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class ArtisteService {

    public static void create(ObjectBdd obj, Connection co) throws Exception {
        obj.create(co);
    }

    public static void update(ObjectBdd obj, Connection co) throws Exception {
        obj.update(co);
    }

    public static void delete(ObjectBdd obj, Connection co) throws Exception {
        obj.delete(co);
    }

    public static HashMap<String, V_artiste> selectListV_artiste(FicheDevis fiche, Connection co) throws Exception {
        HashMap<String, V_artiste> data = new HashMap<>();
        String sql = "select * from V_artiste where \"idArtiste\" not in (select \"idArtiste\" from sousdevis where \"idDevis\" = '" + fiche.getIdDevis() + "' and \"idArtiste\" is not null)";
        System.out.println(sql);
        ArrayList<V_artiste> list = new V_artiste().select(co, sql);
        for (V_artiste artiste : list) {
            data.put(artiste.getIdArtiste(), artiste);
        }
        return data;
    }
}

package com.example.event.lieu;

import com.example.event.devis.FicheDevis;
import com.example.event.v_artiste.V_artiste;
import com.example.event.v_lieu.V_lieu;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class LieuService {
    public static void create(ObjectBdd obj, Connection co)throws Exception{
        obj.create(co);
    }
    public static void update(ObjectBdd obj, Connection co)throws Exception{
        obj.update(co);
    }
    public static void delete(ObjectBdd obj, Connection co)throws Exception{
        obj.delete(co);
    }
    public static HashMap<String, V_lieu> selectListV_lieu(FicheDevis fiche, Connection co) throws Exception {
        HashMap<String, V_lieu> data = new HashMap<>();
        ArrayList<V_lieu> list = new V_lieu().select(co, null);
        for (V_lieu lieu : list) {
            data.put(lieu.getIdLieu(), lieu);
        }
        return data;
    }
}

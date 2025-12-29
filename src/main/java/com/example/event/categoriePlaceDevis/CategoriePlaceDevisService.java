package com.example.event.categoriePlaceDevis;

import java.sql.Connection;
import java.util.ArrayList;
import source.ObjectBdd;

/**
 *
 * @author kaloina.melo
 */
public class CategoriePlaceDevisService {
    public static void create(ObjectBdd obj, Connection co)throws Exception{
        obj.create(co);
    }
    public static void update(ObjectBdd obj, Connection co)throws Exception{
        obj.update(co);
    }
    public static void delete(ObjectBdd obj, Connection co)throws Exception{
        obj.delete(co);
    }
    // check if exist idDevis and idCategoriePlace
    public static void insererModifier(CategoriePlaceDevis obj)throws Exception {
        String sql = "select * from categorieplacedevis where \"idDevis\" = '"+obj.getIdDevis()+"' and \"idCategoriePlace\" = '"+obj.getIdCategoriePlace()+"'";
        System.out.println(sql);
        ArrayList<CategoriePlaceDevis> list  = new CategoriePlaceDevis().select(null, sql);
        if(list.size() ==0){
            obj.create(null);
        } else {
            obj.setIdCategoriePlaceDevis(list.get(0).getIdCategoriePlaceDevis());
            if (obj.getPrix()==0) {
                obj.delete(null);
            } else {
                obj.update(null);
            }
        }
    }
    
}

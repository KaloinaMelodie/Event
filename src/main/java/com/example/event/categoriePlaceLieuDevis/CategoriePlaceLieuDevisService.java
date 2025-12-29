package com.example.event.categoriePlaceLieuDevis;

import com.example.event.categoriePlaceDevis.CategoriePlaceDevis;
import com.example.event.categoriePlaceDevis.CategoriePlaceDevisService;
import com.example.event.sousDevis.SousDevis;
import java.sql.Connection;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class CategoriePlaceLieuDevisService {

    public static void create(ObjectBdd obj, Connection co) throws Exception {
        obj.create(co);
    }

    public static void update(ObjectBdd obj, Connection co) throws Exception {
        obj.update(co);
    }

    public static void delete(ObjectBdd obj, Connection co) throws Exception {
        obj.delete(co);
    }

    // check if exist idDevis and idCategoriePlace
    public static void insererModifier(CategoriePlaceLieuDevis obj) throws Exception {
        String sql = "select * from categorieplaceLieudevis where \"idDevis\" = '" + obj.getIdDevis() + "' and \"idCategoriePlace\" = '" + obj.getIdCategoriePlace() + "' and \"idLieu\" = '"+obj.getIdLieu()+"'";
        System.out.println(sql);
        if (new CategoriePlaceLieuDevis().select(null, sql).size() == 0) {
            obj.create(null);
        } else {
            if (obj.getPrix()==0) {
                obj.delete(null);
            } else {
                obj.update(null);
            }
        }
        CategoriePlaceDevis placeDevis = new CategoriePlaceDevis();
        placeDevis.setIdCategoriePlace(obj.getIdCategoriePlace());
        placeDevis.setIdDevis(obj.getIdDevis());
        placeDevis.setPrix(obj.getPrix());
        CategoriePlaceDevisService.insererModifier(placeDevis);
    }
    
    public static String checkNbrePlace(CategoriePlaceLieuDevis categoriePlaceLieuDevis, int nbrePlace)throws Exception{
        String error = null;
        System.out.println("NBRE "+nbrePlace);
        System.out.println("NBRE "+categoriePlaceLieuDevis.getNbrePlaceVendu());
        if (categoriePlaceLieuDevis.getNbrePlaceVendu() > nbrePlace){
            error = "Incoh&eacute;rent ! Le nombre de places vendues("+categoriePlaceLieuDevis.getNbrePlaceVendu()+") est inf&eacute;rieur au nombre de places r&eacute;ellement disponibles("+nbrePlace+").";
        }
        return error;
    }

}

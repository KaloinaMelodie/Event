/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event.stat;

import com.example.event.devis.Devis;
import com.example.event.devis.FicheDevis;
import java.util.ArrayList;

/**
 *
 * @author patricia.razafimboah
 */
public class StatService {
    public static ArrayList<FicheDevis> getListFicheDevis()throws Exception{
        ArrayList<Devis> list = new Devis().select(null, null);
        ArrayList<FicheDevis> retour = new ArrayList<>();
        for(Devis devis : list){
            retour.add(new FicheDevis(devis.getIdDevis()));
        }
        return retour;
    }
    
    
}

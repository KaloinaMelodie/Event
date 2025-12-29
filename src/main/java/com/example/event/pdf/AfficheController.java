/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event.pdf;

import com.example.event.artiste.Artiste;
import com.example.event.categoriePlaceLieuDevis.CategoriePlaceLieuDevis;
import com.example.event.categoriePlaceLieuDevis.CategoriePlaceLieuDevisService;
import com.example.event.devis.FicheDevis;
import com.example.event.lieu.Lieu;
import com.example.event.spectacle.Spectacle;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patricia.razafimboah
 */
    
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
@Controller
@RequestMapping(value = "affiche")
public class AfficheController {

    @RequestMapping(value = "/generate")
    public ModelAndView createe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("generate");
            String idDevis = request.getParameter("idDevis");
            FicheDevis fiche = new FicheDevis(idDevis);  
            Spectacle spec = new Spectacle();
            spec.setIdSpectacle(fiche.getDevis().getIdSpectacle());
            spec = (Spectacle) spec.findById();
            
            String sql = "select * from artiste where \"idArtiste\" in (select \"idArtiste\" from sousdevis where \"idArtiste\" is not null and \"idDevis\"= '"+fiche.getIdDevis()+"')";
            String sql1 = "select * from lieu where \"idLieu\" in (select \"idLieu\" from sousdevis where \"idLieu\" is not null and \"idDevis\"= '"+fiche.getIdDevis()+"')";
            System.out.println(sql);
            System.out.println(sql1);
            mv.addObject("ficheDevis",fiche);
            mv.addObject("date",AfficheService.dateHeure(spec.getDateHeure())[0]);
            mv.addObject("heure",AfficheService.dateHeure(spec.getDateHeure())[1]);
            mv.addObject("titre",spec.getTitre());
            mv.addObject("listeArtiste",new Artiste().select(null, sql));
            mv.addObject("listeLieu",new Lieu().select(null, sql1));
            
            String but = "/affichage.jsp";
            mv.setViewName( but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    
}

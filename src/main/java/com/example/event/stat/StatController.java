/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event.stat;

import com.example.event.devis.FicheDevis;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import script.Champ;
import script.Form;

/**
 *
 * @author patricia.razafimboah
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
@Controller
@RequestMapping(value = "stat")
public class StatController {
    @RequestMapping("/listeSpectacle")
    public ModelAndView paceListeSpectacle(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            ArrayList<FicheDevis> listeFicheDevis = StatService.getListFicheDevis();
            mv.addObject("listeFicheDevis", listeFicheDevis);

            String but = "views/stat/listeSpectacle.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
}

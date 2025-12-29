package com.example.event.devis;

import com.example.event.artiste.ArtisteService;
import com.example.event.employe.Employe;
import com.example.event.employe.EmployeService;
import com.example.event.lieu.LieuService;
import com.example.event.spectacle.Spectacle;
import exception.NotAuthorizedException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import script.Form;
import source.Base;

/**
 *
 * @author kaloina.melo
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
@Controller
@RequestMapping(value = "devis")
public class DevisController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("create");
            Devis obj = new Devis();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            DevisService.create(obj, null);
            mv.addObject("attributesname", "");
            mv.setViewName("/main/back.do");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updatee(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("update");
            Devis obj = new Devis();
            obj.setFieldsVal(request.getParameterMap(), true, id);
            obj.setIdDevis(id);
            DevisService.update(obj, null);

            mv.addObject("attributesname", "");
            mv.setViewName("/main/back.do");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletee(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("delete");
            Devis obj = new Devis();
            obj.setIdDevis(id);
            DevisService.delete(obj, null);

            mv.addObject("attributesname", "");
            mv.setViewName("/main/back.do");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    @RequestMapping("/generalDevis")
    public ModelAndView pagegeneraldevis(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            String sql = "select * from Devis where 1<2 ";
            Devis objfiltre = new Devis();
            // itadidina ilay valeur efa nipetraka tao, tsy mety RAHA checkbox
            objfiltre.setFieldsVal(request.getParameterMap(), false, "filtre");

            Form form = new Form(objfiltre, null);
            form.setPrefix("filtre");
            form.setAdddef(true);

            form.initChampsmainform();
            form.getChamps().remove(objfiltre.getIdName());

            form.addCol("col-sm-4");

            sql += objfiltre.getFiltresql(form, request.getParameterMap());

            ArrayList list = new Devis().select(null, sql);

            mv.addObject("list", list);
            mv.addObject("objfiltre", objfiltre);
            mv.addObject("searchform", form);

            String but = "views/general/generalDevis.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    
        @RequestMapping(value = "/createAuthorize", method = RequestMethod.POST)
    public ModelAndView createeAuthorize(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("createAuthorize");
            Devis obj = new Devis();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            System.out.println("Request createAuthorize " + request.getParameterMap());
            session.removeAttribute("devisParameter");
            //check authorization
            EmployeService.checkConnex(session);
            String currEmp = (String) session.getAttribute("idemploye");
            obj.setIdEmploye(currEmp);
            obj.generatePk();
            DevisService.create(obj, null);

            mv.setViewName("redirect: /Event/devis/ficheDevis/"+obj.getIdDevis()+".do");
        } catch (NotAuthorizedException noex) {
            mv.addObject("error", noex.getMessage());
            noex.printStackTrace();
            session.setAttribute("devisParameter", Base.cloneMap(request.getParameterMap()));
            System.out.println(request.getParameterMap().size());
            mv.setViewName("redirect:/views/utilisateur/login.jsp?but=/devis/insertionDevis.do");
            return mv;
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    
    @RequestMapping("/insertionDevis")
    public ModelAndView pagecreer(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            Devis obj = new Devis();
            // itadidina ilay valeur efa nipetraka tao, tsy mety RAHA checkbox
            if(session.getAttribute("devisParameter")!=null){
                System.out.println("Tsyyy nullll");
            }
            Map<String, String[]> map = (session.getAttribute("devisParameter") != null) ? (Map<String, String[]>)session.getAttribute("devisParameter") : request.getParameterMap();
            System.out.println(map.size());
            obj.setFieldsVal(map, false, "");
            System.out.println(request.getParameterMap());

            mv.addObject("obj", obj);

            String but = "views/devis/insertionDevis.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    
    @RequestMapping(value = "/ficheDevis/{id}", method = RequestMethod.GET)
    public ModelAndView fiche(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("fiche");
            FicheDevis fiche = new FicheDevis(id);    
            
            mv.addObject("ficheDevis",fiche);
            mv.addObject("selectListTypeV_prestation", DevisService.selectListTypeV_prestation(fiche, null));
            mv.addObject("selectListV_artiste", ArtisteService.selectListV_artiste(fiche, null));
            mv.addObject("selectListV_lieu", LieuService.selectListV_lieu(fiche, null));
            mv.addObject("selectListTypeV_placeLieuDevis", DevisService.selectListTypeV_placeLieuDevis(fiche, null));
            
            mv.setViewName("/index.jsp?but=views/devis/ficheDevis.jsp");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    @RequestMapping(value = "/bis/{id}", method = RequestMethod.GET)
    public ModelAndView biss(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("fiche");
            FicheDevis fiche = new FicheDevis(id);    
            
            mv.addObject("ficheDevis",fiche);
            
            mv.setViewName("/index.jsp?but=views/devis/bis.jsp");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    @RequestMapping(value = "/doBis/{id}", method = RequestMethod.POST)
    public ModelAndView doBiss(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("fiche");
            FicheDevis fiche = new FicheDevis(id);  
            // maka dateheure
            Spectacle spectacle = new Spectacle();
            spectacle.setFieldsVal(request.getParameterMap(), false, "filtre");
            Devis devis = DevisService.doBis(fiche, spectacle);
            FicheDevis newFiche = new FicheDevis(devis.getIdDevis());  
            System.out.println(spectacle);
            
//            
            mv.setViewName("redirect:/devis/ficheDevis/"+devis.getIdDevis()+".do");
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
//        return null;
    }
}

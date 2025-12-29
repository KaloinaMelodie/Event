package com.example.event.categoriePlaceLieuDevis;

import com.example.event.categoriePlaceDevis.CategoriePlaceDevis;
import com.example.event.categoriePlaceDevis.CategoriePlaceDevisService;
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
 * @author patricia.razafimboah
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
@Controller
@RequestMapping(value = "categoriePlaceLieuDevis")
public class CategoriePlaceLieuDevisController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("create");
            CategoriePlaceLieuDevis obj = new CategoriePlaceLieuDevis();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            CategoriePlaceLieuDevisService.create(obj, null);
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
            CategoriePlaceLieuDevis obj = new CategoriePlaceLieuDevis();
            obj.setFieldsVal(request.getParameterMap(), true, id);
            obj.setIdCategoriePlaceLieuDevis(id);
            CategoriePlaceLieuDevisService.update(obj, null);

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
            CategoriePlaceLieuDevis obj = new CategoriePlaceLieuDevis();
            obj.setIdCategoriePlaceLieuDevis(id);
            CategoriePlaceLieuDevisService.delete(obj, null);

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

    @RequestMapping("/generalCategoriePlaceLieuDevis")
    public ModelAndView pagegeneralcategoriePlaceLieuDevis(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            String sql = "select * from CategoriePlaceLieuDevis where 1<2 ";
            CategoriePlaceLieuDevis objfiltre = new CategoriePlaceLieuDevis();
            // itadidina ilay valeur efa nipetraka tao, tsy mety RAHA checkbox
            objfiltre.setFieldsVal(request.getParameterMap(), false, "filtre");

            Form form = new Form(objfiltre, null);
            form.setPrefix("filtre");
            form.setAdddef(true);

            form.initChampsmainform();
            form.getChamps().remove(objfiltre.getIdName());

            form.addCol("col-sm-4");

            sql += objfiltre.getFiltresql(form, request.getParameterMap());

            ArrayList list = new CategoriePlaceLieuDevis().select(null, sql);

            mv.addObject("list", list);
            mv.addObject("objfiltre", objfiltre);
            mv.addObject("searchform", form);

            String but = "views/general/generalCategoriePlaceLieuDevis.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/insererModifier", method = RequestMethod.POST)
    public ModelAndView insererModif(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("insererModifier");
            CategoriePlaceLieuDevis obj = new CategoriePlaceLieuDevis();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            String errorPlace = CategoriePlaceLieuDevisService.checkNbrePlace(obj, Integer.valueOf(request.getParameter("nbrePlace")));
            if (errorPlace != null) {
                mv.addObject("errorPlace", errorPlace);
                mv.addObject("attributesname", "errorPlace");
                mv.setViewName("/main/back.do");
                return mv;
            }
            CategoriePlaceLieuDevisService.insererModifier(obj);
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
}

package com.example.event.sousDevis;

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
@RequestMapping(value = "sousDevis")
public class SousDevisController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("create");
            SousDevis obj = new SousDevis();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            SousDevisService.create(obj, null);
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
            SousDevis obj = new SousDevis();
            obj.setFieldsVal(request.getParameterMap(), true, id);
            obj.setIdSousDevis(id);
            SousDevisService.update(obj, null);

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
            SousDevis obj = new SousDevis();
            obj.setIdSousDevis(id);
            SousDevisService.delete(obj, null);

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

    @RequestMapping("/generalSousDevis")
    public ModelAndView pagegeneralsousDevis(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            String sql = "select * from SousDevis where 1<2 ";
            SousDevis objfiltre = new SousDevis();
            // itadidina ilay valeur efa nipetraka tao, tsy mety RAHA checkbox
            objfiltre.setFieldsVal(request.getParameterMap(), false, "filtre");

            Form form = new Form(objfiltre, null);
            form.setPrefix("filtre");
            form.setAdddef(true);

            form.initChampsmainform();
            form.getChamps().remove(objfiltre.getIdName());

            form.addCol("col-sm-4");

            sql += objfiltre.getFiltresql(form, request.getParameterMap());

            ArrayList list = new SousDevis().select(null, sql);

            mv.addObject("list", list);
            mv.addObject("objfiltre", objfiltre);
            mv.addObject("searchform", form);

            String but = "views/general/generalSousDevis.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    @RequestMapping(value = "/insererSousDevis/{id}", method = RequestMethod.POST)
    public ModelAndView createe(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("create");
            SousDevis obj = new SousDevis();
            obj.setFieldsVal(request.getParameterMap(), false, id);
            
            
            SousDevisService.create(obj, null);
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

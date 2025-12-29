package com.example.event.admin;

import exception.LogException;
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
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            System.out.println("create");
            Admin obj = new Admin();
            obj.setFieldsVal(request.getParameterMap(), false, "");
            AdminService.create(obj, null);
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
            Admin obj = new Admin();
            obj.setFieldsVal(request.getParameterMap(), true, id);
            obj.setIdAdmin(id);
            AdminService.update(obj, null);

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
            Admin obj = new Admin();
            obj.setIdAdmin(id);
            AdminService.delete(obj, null);

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

    @RequestMapping("/generalAdmin")
    public ModelAndView pagegeneralram(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            String sql = "select * from Admin where 1<2 ";
            Admin objfiltre = new Admin();
            // itadidina ilay valeur efa nipetraka tao, tsy mety RAHA checkbox
            objfiltre.setFieldsVal(request.getParameterMap(), false, "filtre");

            Form form = new Form(objfiltre, null);
            form.setPrefix("filtre");
            form.setAdddef(true);

            form.initChampsmainform();
            form.getChamps().remove(objfiltre.getIdName());

            form.addCol("col-sm-4");

            sql += objfiltre.getFiltresql(form, request.getParameterMap());

            ArrayList list = new Admin().select(null, sql);

            mv.addObject("list", list);
            mv.addObject("objfiltre", objfiltre);
            mv.addObject("searchform", form);

            String but = "views/general/generalAdmin.jsp";
            mv.setViewName("/index.jsp?but=" + but);
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
    
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        ArrayList<String> attributes = new ArrayList<>();
        try {
            System.out.println("login");
            Admin user = new Admin();
            user.setIdentifiant(request.getParameter("identifiant"));
            user.setMdp(request.getParameter("mdp"));           
            user = user.login();

            // set session id & token
            session.setAttribute("idadmin", user.getIdAdmin());
            session.setAttribute("admin", user);// raha ilaina
            session.setAttribute("admintoken", user.getToken());// raha ilaina
            System.out.println(session.getAttribute("idadmin"));

            System.out.println(request.getParameter("but"));
            mv.setViewName("redirect:/" + request.getParameter("but"));
            if(request.getParameter("indexpage")!=null) mv.setViewName("redirect:/index.jsp?but=" + request.getParameter("but"));
            //mv.setViewName("redirect:/index.jsp?but=/views/central/accueil.jsp");
        } catch (LogException logex) {
            mv.addObject("error", logex.getMessage());
            attributes.add("error");
            logex.printStackTrace();
            mv.addObject("attributesname", String.join(",", attributes));
            mv.setViewName("/main/back.do");
            return mv;
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/logout/{id}.do", method = RequestMethod.GET)
    public ModelAndView logout(@PathVariable("id") String id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        ArrayList<String> attributes = new ArrayList<>();
        try {
            System.out.println("logout");
            Admin user = new Admin();
            user.setIdAdmin(id);
            user.logout();
            // clear session id & token
            System.out.println(session.getAttribute("idadmin"));
            session.invalidate();
            mv.addObject("attributesname", "");
            mv.setViewName("redirect:/index.jsp?central");
        } catch (LogException logex) {
            mv.addObject("error", logex.getMessage());
            attributes.add("error");
            logex.printStackTrace();
            mv.addObject("attributesname", String.join(",", attributes));
            mv.setViewName("/main/back.do");
            return mv;
        } catch (Exception ex) {
            mv.addObject("error", ex.getMessage());
            ex.printStackTrace();
            mv.setViewName("/index.jsp?but=views/defaulterror.jsp");
            return mv;
        }
        return mv;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patricia.razafimboah
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
@Controller
@RequestMapping(value = "main")
public class MainController {

    @RequestMapping(value = "/back")
    public ModelAndView backe(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String referer = request.getHeader("Referer");
        System.out.println("Referer "+referer);
        System.out.println(request.getAttribute("attributesname"));
        if(request.getAttribute("removeattributes")!=null&&!request.getAttribute("attributesname").toString().equals("")){
            for (String attr : request.getAttribute("removeattributes").toString().split(",")) {
                if (referer.contains(attr+"=")) {
                    String updatedReferer = referer.replaceFirst("\\b" + attr + "=[^&]*", "");
                    referer =  updatedReferer;
                    System.out.println(referer);
                }
            }
        }
        if (!request.getAttribute("attributesname").toString().equals("")) {
            for (String attr : request.getAttribute("attributesname").toString().split(",")) {
//                if (referer.contains(attr+"=")) { // example efa nisy parameter error teo aloha dia mila soloina
//                    String updatedReferer = referer.replaceFirst("\\b" + attr + "=[^&]*", attr + "="+request.getAttribute(attr));
//                    referer =  updatedReferer;
//                    continue;
//                }
                mv.addObject(attr, request.getAttribute(attr));
            }
        }
        mv.setViewName("redirect:" + referer);
        return mv;
    }
}

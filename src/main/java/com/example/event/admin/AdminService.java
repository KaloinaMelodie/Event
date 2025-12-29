package com.example.event.admin;

import java.sql.Connection;
import javax.servlet.http.HttpSession;
import source.ObjectBdd;

/**
 *
 * @author kaloina.melo
 */
public class AdminService {
    public static void create(ObjectBdd obj, Connection co)throws Exception{
        obj.create(co);
    }
    public static void update(ObjectBdd obj, Connection co)throws Exception{
        obj.update(co);
    }
    public static void delete(ObjectBdd obj, Connection co)throws Exception{
        obj.delete(co);
    }
    public static void checkConnex(HttpSession session) throws Exception {
        Admin user = new Admin();
        String token = "tatata";
        if(session.getAttribute("admintoken")!=null){token = session.getAttribute("admintoken").toString();}
        user.setToken(token);
        user.checkconnex();
    }
}

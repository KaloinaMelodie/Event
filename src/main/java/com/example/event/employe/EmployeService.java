package com.example.event.employe;

import com.example.event.admin.Admin;
import java.sql.Connection;
import javax.servlet.http.HttpSession;
import source.ObjectBdd;

/**
 *
 * @author kaloina.melo
 */
public class EmployeService {
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
        Employe user = new Employe();
        String token = "tatata";
        if(session.getAttribute("employetoken")!=null){token = session.getAttribute("employetoken").toString();}
        user.setToken(token);
        user.checkconnex();
    }
}

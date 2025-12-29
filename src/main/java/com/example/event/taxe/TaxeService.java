package com.example.event.taxe;

import java.sql.Connection;
import source.ObjectBdd;

/**
 *
 * @author kaloina.melo
 */
public class TaxeService {
    public static void create(ObjectBdd obj, Connection co)throws Exception{
        obj.create(co);
    }
    public static void update(ObjectBdd obj, Connection co)throws Exception{
        obj.update(co);
    }
    public static void delete(ObjectBdd obj, Connection co)throws Exception{
        obj.delete(co);
    }
    
}

package com.example.event.spectacle;

import java.sql.Connection;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class SpectacleService {
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

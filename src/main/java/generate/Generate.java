/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import com.example.event.v_lieu.V_lieu;
import com.example.event.v_prestation.V_prestation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class Generate {
    //    necessaire: Class, package, ClassListe (maybe view), viewLink
    public static void writeFile(String filePath,String fileName, String content) {
        File dossier = new File(filePath);
        if (!dossier.exists()) {
            boolean creationReussie = dossier.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+fileName))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void createFiles(String cls,Class typeIdCls, String pack, Class classListe, String controllerLink) throws Exception {

        String packFile = "D:\\melodie\\1evaluation\\init2\\Event\\src\\main\\java";
        packFile += "\\"+(pack.replace(".", "\\"))+"\\";
        System.out.println(packFile);
        
        String viewFile = "D:\\melodie\\1evaluation\\init2\\Event\\src\\main\\webapp\\views\\";
        
        // write class
        String cfileName = cls+".java";
        writeFile(packFile, cfileName, GenerateCls.generate(pack, cls, "id"+cls,null));
        
        // write service
        String sfileName = cls+"Service.java";
        writeFile(packFile, sfileName, GenerateService.createFile(cls, pack));
                
        // write controller
        String ctfileName = cls+"Controller.java";
        writeFile(packFile, ctfileName, GenerateController.createFile(cls, typeIdCls , pack, (classListe==null)?null:classListe.getSimpleName()));
        
        classListe = (classListe == null) ? Class.forName(pack+"."+cls) : classListe;
        String strClassListe = (classListe == null) ? cls : classListe.getSimpleName();
        
        
        // write view
        String vfileName = "general\\"+"general"+cls+".jsp";
        writeFile(viewFile, vfileName, GenerateView.createFile(cls, pack, classListe, controllerLink));
        

    }

//    TANDREMO ATO SAO TAFI EXECUTE TAMPOKA FA VERY NY DONNEE
    public static void main(String[] gygy) throws Exception {
        createFiles("Taxe", String.class,"com.example.event.taxe", null, "/Event/taxe");
    }
}

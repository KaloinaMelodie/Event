/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import com.example.event.v_artiste.V_artiste;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class GenerateView {

    //    necessaire: Class, package, ClassListe (maybe view), Thead, Tbody, controllerLink
    public static String readBasicView() throws IOException {
        String filePath = "D:\\melodie\\1evaluation\\init2\\Event\\src\\main\\java\\generate\\basicView.txt";
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
        }

        return contentBuilder.toString();
    }

    public static String createFile(String cls, String pack, Class classListe, String controllerLink) throws Exception {
        classListe = (classListe == null) ? Class.forName(pack+"."+cls) : classListe;
        String strClassListe = (classListe == null) ? cls : classListe.getSimpleName();

        String file = readBasicView();
        file = file.replace("[Class]", cls);
        file = file.replace("[package]", pack);
        file = file.replace("[ClassListe]", strClassListe);
        file = file.replace("[controllerLink]", controllerLink);
        ObjectBdd instance = (ObjectBdd) classListe.newInstance();
        file = file.replace("[Thead]", Vonjy.thead(instance));
        file = file.replace("[Tbody]", Vonjy.tbody(instance, "cast"));

        return file;
    }

    public static void main(String[] gygy) throws Exception {
        System.out.println(createFile("Artiste", "com.example.event.artiste", V_artiste.class, "/Event/artiste"));

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author patricia.razafimboah
 */
public class GenerateController {
//    necessaire: Class, class, TypeIdClass, package, ClassListe (maybe view), toStringID

    public static String readBasicController() throws IOException {
        String filePath = "D:\\melodie\\1evaluation\\init2\\Event\\src\\main\\java\\generate\\basicController.txt";
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

    public static String createFile(String cls, Class typeIdCls, String pack, String classListe) throws Exception {
        classListe = (classListe == null) ? cls : classListe;
        String toStringId = (typeIdCls.equals(String.class)) ? "id" : "Integer.toString(id)";
        String lowerCls = Character.toLowerCase(cls.charAt(0)) + cls.substring(1);

        String file = readBasicController();
        file = file.replace("[class]", lowerCls);
        file = file.replace("[Class]", cls);
        file = file.replace("[package]", pack);
        file = file.replace("[ClassListe]", classListe);
        file = file.replace("[TypeIdClass]", typeIdCls.getSimpleName());
        file = file.replace("[toStringID]", toStringId);

        return file;
    }

    public static void main(String[] gygy) throws Exception {
        System.out.println(createFile("Andrana", String.class, "com.example.event.andrana", "Andrana"));

    }
}

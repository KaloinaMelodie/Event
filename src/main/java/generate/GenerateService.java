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
 * @author kaloina.melo
 */
public class GenerateService {
    // package, Class
    public static String readBasicService() throws IOException {
        String filePath = "D:\\melodie\\1evaluation\\init2\\Event\\src\\main\\java\\generate\\basicService.txt";
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

    public static String createFile(String cls, String pack) throws Exception {

        String file = readBasicService();
        file = file.replace("[Class]", cls);
        file = file.replace("[package]", pack);

        return file;
    }

    public static void main(String[] gygy) throws Exception {
        System.out.println(createFile("Andrana", "com.example.event.andrana"));

    }
}

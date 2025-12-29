/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author patricia.razafimboah
 */
public class PicHandler {

    public static String toBase64(Part filePart) throws Exception {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] fileBytes = byteArrayOutputStream.toByteArray();
        String fileBase64 = Base64.getEncoder().encodeToString(fileBytes);
        return fileBase64;
    }

    public static String getFileExtension(Part filePart) {
        String fileName = filePart.getSubmittedFileName();
        return FilenameUtils.getExtension(fileName);
    }

    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event.pdf;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author kaloina.melo
 */
public class AfficheService {
    public static String[] dateHeure(Timestamp dateheure){
        Timestamp timestamp = dateheure;

        // Transformation en format de date en français
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        String date = dateFormat.format(timestamp);

        // Transformation en format de date en lettres
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.FRENCH);
        String dateInLetters = dateFormatter.format(timestamp);
        String timeInLetters = timeFormatter.format(timestamp);

        // Affichage des résultats
        System.out.println("Date en format français : " + date);
        System.out.println("Date en lettres : " + dateInLetters);
        System.out.println("Heure en lettres : " + timeInLetters);
        return new String[]{dateInLetters, timeInLetters};
    }
}

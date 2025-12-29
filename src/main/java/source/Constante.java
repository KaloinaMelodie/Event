/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kaloina.melo
 */
public class Constante {
    public static ArrayList<Integer> numericTypes = new ArrayList<>(Arrays.asList(
        Types.BIGINT,
        Types.DECIMAL,
        Types.DOUBLE,
        Types.FLOAT,
        Types.INTEGER,
        Types.NUMERIC,
        Types.REAL,
        Types.SMALLINT,
        Types.TINYINT
));
    public static String[] strWithoutQuote = new String[]{
        "CURRENT_DATE",
        "SYSDATE",
        "CURDATE()",
        "CURRENT_TIMESTAMP",
        "SYSTIMESTAMP",
        "NOW()"
    };
}

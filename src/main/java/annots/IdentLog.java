/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annots;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author patricia.razafimboah
 */
@Retention(RetentionPolicy.RUNTIME)  
public @interface IdentLog {
    String error() default "Invalid user identity. Please check it and try again.";
}

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
 * @author kaloina.melo
 */
@Retention(RetentionPolicy.RUNTIME)  
public @interface MdpLog {
    String error() default "Incorrect password. Please check your password and try again.";
    String cryptofunc();
}

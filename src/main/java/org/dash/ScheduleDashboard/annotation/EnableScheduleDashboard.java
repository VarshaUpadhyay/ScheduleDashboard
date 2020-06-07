/**
 * 
 */
package org.dash.ScheduleDashboard.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author Varsha
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface EnableScheduleDashboard {

}

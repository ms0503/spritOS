package com.ms0503.spritos.common.annotation;

import com.ms0503.spritos.common.spritOS;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@author ms0503*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Program {
    String id();
    String name();
    String version() default "1.0.0";
    String require() default "None";
    String requireOS();
    String author();
=======
package com.ms0503.spritos.common.annotation;

import com.ms0503.spritos.common.spritOS;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@author ms0503*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Program {
    String id();
    String name();
    String version() default "1.0.0";
    String require() default "None";
    String requireOS();
    String author();
>>>>>>> origin/master
=======
package com.ms0503.spritos.common.annotation;

import com.ms0503.spritos.common.spritOS;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@author ms0503*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Program {
    String id();
    String name();
    String version() default "1.0.0";
    String require() default "None";
    String requireOS();
    String author();
>>>>>>> origin/master
}
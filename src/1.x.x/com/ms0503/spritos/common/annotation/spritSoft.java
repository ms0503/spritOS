package com.ms0503.spritos.common.annotation;

import com.ms0503.spritos.common.core.spritOSCore;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@author ms0503*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface spritSoft {
    @NotNull String id();
    @NotNull String name();
    @NotNull String version() default "1.0.0";
    @NotNull String require() default "None";
    @NotNull String requireOS() default spritOSCore.latest;
    @NotNull String author();
}
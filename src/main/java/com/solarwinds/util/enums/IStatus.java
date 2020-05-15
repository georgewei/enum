package com.solarwinds.util.enums;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface IStatus {
    int value();
    String desc();
}


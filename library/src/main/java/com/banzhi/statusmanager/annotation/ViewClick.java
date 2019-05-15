package com.banzhi.statusmanager.annotation;

import com.banzhi.statusmanager.enums.LoadType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/11.
 * @desciption :
 * @version :
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ViewClick {
    LoadType value() default LoadType.BOTH;
}

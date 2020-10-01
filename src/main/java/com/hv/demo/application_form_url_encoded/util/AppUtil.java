package com.hv.demo.application_form_url_encoded.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 16/07/2020
 */
public class AppUtil {

    public static String currentDate(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    /**
     * Get Final cause of exception
     * @param t
     * @return Throwable
     */
    public static Throwable getFinalCause(Throwable t){
        if(t.getCause()!=null){
            return getFinalCause(t.getCause());
        }else{
            return t;
        }
    }

    /**
     * Get Class name by depth level
     * @param level
     * @return Class Name
     */
    public static String getClassName(int level) {
        return Thread.currentThread().getStackTrace()[level].getClassName();
    }

    /**
     * Get Class name default depth level 2
     * @return Class Name
     */
    public static String getClassName() {
        return getClassName(2);
    }

    /**
     * Get Method name by depth level
     * @param level
     * @return Method Name
     */
    public static String getMethodName(int level) {
        return Thread.currentThread().getStackTrace()[level].getMethodName();
    }


    /**
     * Get Method name default depth level 2
     * @return Method Name
     */
    public static String getMethodName() {
        return getMethodName(2);
    }
}

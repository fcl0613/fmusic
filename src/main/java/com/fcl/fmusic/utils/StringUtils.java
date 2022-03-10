package com.fcl.fmusic.utils;

public class StringUtils {
    public static boolean isEmpty(Object o){
        if (o == null){
            return true;
        }
        if (o instanceof String){
            if (((String) o).trim().length() == 0){
                return true;
            }
        }else {
            return false;
        }
        return false;
    }

    public static boolean isNotEmpty(Object o){
        return  !isEmpty(o);
    }
}

package com.lly835.bestpay.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

/**
 * Json Utils.
 */
public class JsonUtil {


    private static GsonBuilder gsonBuilder = new GsonBuilder();

    /**
     * Convert target object to json string.
     *
     * @param obj target object.
     * @return converted json string.
     */
    public static String toJson(Object obj) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().toJson(obj);
    }

    public static String toJsonWithUnderscores(Object obj) {
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create().toJson(obj);
    }


}

package com.fengping.demo.utils;

public class StringUtil {
    public static String getExtName(String fileName) {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    public static void main(String[] args) {
        String str = "xxxx.jpg";
        String extName = getExtName(str);
        System.out.println(extName);
    }
}

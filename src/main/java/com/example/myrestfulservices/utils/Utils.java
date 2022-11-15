package com.example.myrestfulservices.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class Utils {
    public static void DISPLAY_REQUEST_HEADER(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            Enumeration<String> values = request.getHeaders(header);
            System.out.println(header);
            while (values.hasMoreElements()) {
                System.out.println("\t" + values.nextElement());
            }
        }
    }
}

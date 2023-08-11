package com.mvc.sample.javacore.mvcmodel.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {
    private ServletUtils() {
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(StringUtils.join(request.getContextPath(), url));
    }

    public static void requestDispatcher(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
        request.getRequestDispatcher(StringUtils.join(request.getContextPath(), url)).forward(request, response);
    }

    public static void apiResponse(HttpServletResponse response, String value) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(value);
        out.close();
    }
}

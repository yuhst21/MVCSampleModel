package com.mvc.sample.javacore.mvcmodel.util;

public class PagingUtils {
    private PagingUtils() {
    }

    public static final int DEFAULT_PAGE_SIZE = 8 ;
    public static int getOffset(int pageNumber) {
        return (pageNumber - 1) * DEFAULT_PAGE_SIZE;
    }
    public static int getTotalPage(int totalItem) {
        return (int) Math.ceil((double) totalItem / DEFAULT_PAGE_SIZE);
    }
}

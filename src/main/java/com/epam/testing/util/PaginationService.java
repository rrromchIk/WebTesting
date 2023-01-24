package com.epam.testing.util;

/**
 * PaginationService util class to
 * calculate parameters for pagination
 *
 */
public class PaginationService {
    private PaginationService() {

    }

    public static Integer getNumberOfPages(Integer limit, Integer totalNumber) {
        return (int)Math.ceil((double)totalNumber / limit);
    }

    public static Integer getOffsetOnCertainPage(Integer limit, Integer page) {
        return limit * (page - 1);
    }

    public static int getValidPageNumber(String page, Integer totalNumber, Integer limit) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if(pageNumber < 1 || pageNumber > getNumberOfPages(limit, totalNumber)) {
            pageNumber = 1;
        }
        return pageNumber;
    }
}

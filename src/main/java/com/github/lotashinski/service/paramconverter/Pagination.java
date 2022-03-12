package com.github.lotashinski.service.paramconverter;

import javax.servlet.http.HttpServletRequest;

public final class Pagination {
    private final int limit;
    private final int offset;


    private Pagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }


    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }


    public static Pagination configureFromRequest(HttpServletRequest req) {
        String offsetStr = req.getParameter("offset");
        String limitStr = req.getParameter("limit");

        int offset = (null == offsetStr) ? 0 : Integer.parseInt(offsetStr);
        int limit = (null == limitStr) ? 10 : Integer.parseInt(limitStr);

        return new Pagination(limit, offset);
    }
}

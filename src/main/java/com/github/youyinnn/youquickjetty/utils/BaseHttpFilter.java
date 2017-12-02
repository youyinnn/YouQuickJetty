package com.github.youyinnn.youquickjetty.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author youyinnn
 */
public abstract class BaseHttpFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse)resp;

        doFilter(request,response,chain);
    }

    public abstract void doFilter(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException;

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        init();
    }

    protected void init() {
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }
}

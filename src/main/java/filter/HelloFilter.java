package filter;

import cn.youyinnn.youQuickJetty.utils.HttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: youyinnn
 * @date: 2017/9/26
 */
public class HelloFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("********");
        chain.doFilter(request,response);
        System.out.println("********");
    }
}

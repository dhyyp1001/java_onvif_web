package service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebViewFilter implements Filter {
    public void init(FilterConfig config){}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && (userAgent.contains("Android") || userAgent.contains("WebView"))) {
            // Allow the request to continue
            chain.doFilter(request, response);
        } else {
            // Redirect to the error page
            response.sendRedirect("/error.html");
        }
    }

    public void destroy() {}
}

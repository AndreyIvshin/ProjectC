package org.example.security;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter implements Filter {

    public static final String AUTHORIZATION_HEADER = "authorization";
    private final String OPTIONS = "OPTIONS";
    private String SECURITY_URI = "/api/security";
    private String API_URI = "/api";

    @Autowired
    private JWTGenerator jwtGenerator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (request.getRequestURI().startsWith(request.getContextPath() + API_URI) && !request.getMethod().equals(OPTIONS)) {
            if (request.getRequestURI().startsWith(request.getContextPath() + SECURITY_URI)) {
                filterChain.doFilter(request, response);
            } else {
                if (authorization != null) {
                    try {
                        jwtGenerator.parse(authorization.split(" ")[1]);
                        filterChain.doFilter(request, response);
                    } catch (JwtException jwtException) {
                        jwtException.printStackTrace();
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        filterChain.doFilter(request, response);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    filterChain.doFilter(request, response);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

package com.gugucoding.club.security.filter;

import com.gugucoding.club.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;
    private JWTUtil jwtUtil;

    public ApiCheckFilter(String pattern, JWTUtil jwtUtil) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (antPathMatcher.match(pattern, request.getRequestURI())) {

            log.info("API CHECK FILTER....");
            log.info("API CHECK FILTER....");
            log.info("API CHECK FILTER....");

            boolean checkHeader = checkAuthHeader(request);
            if (checkHeader) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                //json 리턴 및 한글깨짐
                response.setContentType("application/json;chart=utf-8");
                JSONObject jsonObject = new JSONObject();
                String message = "FAIL CHECK API TOKEN";
                jsonObject.put("code", "403");
                jsonObject.put("message", message);

                PrintWriter out = response.getWriter();
                out.println(jsonObject);

            }
            return;
        }

        filterChain.doFilter(request, response);

    }

    private boolean checkAuthHeader(HttpServletRequest request) {

        boolean result = false;

        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {

            log.info("Authorization exist : " + authHeader);

            try {

                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result : "+email);
                result = email.length() > 0;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}

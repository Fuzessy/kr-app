package hu.kr.server.config.logging;

import hu.kr.server.config.logging.model.CookieModel;
import hu.kr.server.config.logging.model.HttpLogModel;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * ez mindig meghívódik, ha van hozzó kontroller, ha nincs
 */
@Component
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // SKIP
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        RequestResponseLogger logger = new RequestResponseLogger();
        logger.logRequest(requestWrapper);

        filterChain.doFilter(requestWrapper,responseWrapper);

        logger.logResponse(requestWrapper,responseWrapper);
        System.out.println(logger.getLogModel());

        // itt lehetőség lenne módosítani, de csak
        // a logolás miatt használjuk, hogy ki tudjuk olvasni a választ
        responseWrapper.copyBodyToResponse();
    }

    @Override
    public void destroy() {
        // SKIP
    }
}

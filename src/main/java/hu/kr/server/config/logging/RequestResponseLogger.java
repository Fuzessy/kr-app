package hu.kr.server.config.logging;

import hu.kr.server.config.logging.model.CookieModel;
import hu.kr.server.config.logging.model.HttpLogModel;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestResponseLogger {

    private final HttpLogModel logModel;

    public RequestResponseLogger(){
        logModel = new HttpLogModel();
    }

    public void logRequest(HttpServletRequest request) throws IOException {
        logModel.requestDateTime = LocalDateTime.now();
        logModel.httpMethod = request.getMethod();
        logModel.requestPath = request.getRequestURI();
        logModel.queryString = request.getQueryString();

        fillHeaders(request);
        fillCookies(request);
    }

    private void fillCookies(HttpServletRequest request) {
        logModel.cookies = new ArrayList<>();
        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                CookieModel cookieModel = new CookieModel();
                cookieModel.name = cookie.getName();
                cookieModel.value = cookie.getValue();
                cookieModel.maxAge = cookie.getMaxAge();
                logModel.cookies.add(cookieModel);
            }
        }
    }

    private void fillHeaders(HttpServletRequest request) {
        Map<String,String> logHeaders = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            // a cookie-t külön rögzítem a logolás során
            if(!headerName.equals("Cookie")) {
                logHeaders.put(headerName, headerValue);
            }
        }
        logModel.headers = logHeaders;
    }

    public HttpLogModel getLogModel() {
        return this.logModel;
    }

    public void logResponse(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper servletResponse) {
        logModel.responseDateTime = LocalDateTime.now();
        logModel.requestBody = getRequestContent(requestWrapper);
        logModel.responseBody = readResponse(servletResponse);
    }

    private String readResponse(ContentCachingResponseWrapper servletResponse) {
        String response = null;
        try {
            response = new String(servletResponse.getContentAsByteArray(), servletResponse.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getRequestContent(ContentCachingRequestWrapper requestWrapper) {
        try {
            if(!requestWrapper.getInputStream().isFinished()) {
                requestWrapper.getReader().lines().forEach( s -> {});
            }
            return new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

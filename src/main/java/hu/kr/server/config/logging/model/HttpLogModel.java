package hu.kr.server.config.logging.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class HttpLogModel {
    public LocalDateTime requestDateTime;
    public Map<String,String> headers;
    public ArrayList<CookieModel> cookies;
    public String httpMethod;
    public String requestPath;
    public String queryString;
    public String requestBody;
    public String responseBody;
    public LocalDateTime responseDateTime;

    @Override
    public String toString() {
        return "HttpLogModel{" + System.lineSeparator() +
                "   , requestPath='" + requestPath + '\'' + System.lineSeparator() +
                "   , httpMethod='" + httpMethod + '\'' + System.lineSeparator() +
                "   , queryString='" + queryString + '\'' + System.lineSeparator() +
                "   , requestDateTime=" + requestDateTime + System.lineSeparator() +
                "   , responseDateTime=" + responseDateTime + System.lineSeparator() +
                "   , headers=" + headers + System.lineSeparator() +
                "   , cookies=" + cookies + System.lineSeparator() +
                "   , requestBody='" + requestBody + '\'' + System.lineSeparator() +
                "   , responseBody='" + responseBody + '\'' + System.lineSeparator() +
                '}';
    }

}

package hu.kr.server.config.logging.model;

public class CookieModel {
    public String name;
    public String value;
    public int maxAge;

    @Override
    public String toString() {
        return "CookieModel{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", maxAge=" + maxAge +
                '}';
    }
}

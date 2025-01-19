package code.shubham.commons.configurations;

import org.springframework.stereotype.Component;

@Component
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDataSource(String dataSourceKey) {
        contextHolder.set(dataSourceKey);
    }

    public static String getCurrentDataSource() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
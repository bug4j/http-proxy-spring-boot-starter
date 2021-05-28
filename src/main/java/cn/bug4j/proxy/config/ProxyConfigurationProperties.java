package cn.bug4j.proxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ProxyConfigurationProperties
 * @Date: 2021/5/27 16:48
 * @Author: bug4j/CYS
 * @Description: 基础配置类
 */
@Validated
@ConfigurationProperties(prefix = "bug4j.proxy", ignoreInvalidFields = true )
public class ProxyConfigurationProperties {

    /**
     * 代理servlet的拦截路径
     */
    private String apiPrefix = "/proxy/*";
    /**
     * 代理api转发到的服务器地址;
     */
    @NotEmpty(message = "必须配置转发的目标服务")
    private String targetServerUrl;
    /**
     * 是否打印代理记录日志;
     */
    private Boolean enableLog = false;
    /**
     * 自定义请求头，如果需要删除请求头，将值配成 false
     */
    private Map<String, String> requestHeaders = new HashMap<>();
    /**
     * 自定义响应头，如果需要删除响应头，将值配成 false
     */
    private Map<String, String> responseHeaders = new HashMap<>();
        
    public void setEnableLog(boolean enableLog) { this.enableLog = enableLog; };

    public void setApiPrefix(String apiPrefix) {
        apiPrefix += apiPrefix.endsWith("/") ? "*" : "/*";
        this.apiPrefix = apiPrefix;
    }

    public void setTargetServerUrl(String targetServerUrl) {
        targetServerUrl += targetServerUrl.endsWith("/") ? targetServerUrl.substring(0, targetServerUrl.length() - 1) : "";
        this.targetServerUrl = targetServerUrl;
    }

    public void setRequestHeaders(Map<String, String> headerConfigs) {
        this.requestHeaders = headerConfigs == null ? new HashMap<>() : headerConfigs;
    }

    public void setResponseHeaders(Map<String, String> headerConfigs) {
        this.responseHeaders = headerConfigs == null ? new HashMap<>() : headerConfigs;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public String getTargetServerUrl() {
        return targetServerUrl;
    }

    public Boolean getEnableLog() {
        return enableLog;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }
}

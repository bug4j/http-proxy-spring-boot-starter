package cn.bug4j.proxy.config;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: WutosProxyServlet
 * @Date: 2021/5/27 9:57
 * @Author: bug4j/CYS
 * @Description: TODO
 */
public class Bug4jProxyServlet extends ProxyServlet {

    protected final ProxyConfigurationProperties configurationProperties;

    public Bug4jProxyServlet(ProxyConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Override
    public HttpResponse doExecute(HttpServletRequest servletRequest, HttpServletResponse servletResponse, HttpRequest proxyRequest) throws IOException {
        processRequestHeaders(proxyRequest);
        HttpResponse httpResponse = super.doExecute(servletRequest, servletResponse, proxyRequest);
        processResponseHeaders(httpResponse);
        return httpResponse;
    }

    protected void processRequestHeaders(HttpRequest request) {
        Map<String, String> requestHeaders = configurationProperties.getRequestHeaders();
        for(String key : requestHeaders.keySet()) {
            String value = requestHeaders.get(key);
            if(isHeaderValueValid(value)) {
                request.setHeader(key, value);
            } else {
                request.removeHeaders(key);
            }
        }
    }

    protected void processResponseHeaders(HttpResponse response) {
        Map<String, String> responseHeaders = configurationProperties.getResponseHeaders();
        for(String key : responseHeaders.keySet()) {
            String value = responseHeaders.get(key);
            if(isHeaderValueValid(value)) {
                response.setHeader(key, value);
            } else {
                response.setHeader(key,null);
            }
        }
    }

    private boolean isHeaderValueValid(Object value) {
        if(value == null) return false;
        if(value instanceof String) {
            String val = ((String) value).trim().toLowerCase();
            return !("false".equals(val) || "".equals(val));
        }
        return true;
    }
}

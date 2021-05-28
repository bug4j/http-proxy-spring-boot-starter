package cn.bug4j.proxy.config;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @ClassName: RegisterProxyServlet
 * @Date: 2021/5/27 10:05
 * @Author: bug4j/CYS
 * @Description: TODO
 */
@Import(Bug4jProxyServlet.class)
public class RegisterProxyServlet {

    private final ProxyConfigurationProperties configurationProperties;
    private final Bug4jProxyServlet proxyServlet;

    public RegisterProxyServlet(ProxyConfigurationProperties configurationProperties, Bug4jProxyServlet proxyServlet) {
        this.configurationProperties = configurationProperties;
        this.proxyServlet = proxyServlet;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(proxyServlet, configurationProperties.getApiPrefix());
        servletRegistrationBean.addInitParameter(ProxyServlet.P_TARGET_URI, configurationProperties.getTargetServerUrl());
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, configurationProperties.getEnableLog().toString());
        return servletRegistrationBean;
    }

}

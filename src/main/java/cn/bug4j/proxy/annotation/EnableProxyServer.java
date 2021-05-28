package cn.bug4j.proxy.annotation;

import cn.bug4j.proxy.config.ProxyConfigurationProperties;
import cn.bug4j.proxy.config.RegisterProxyServlet;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @ClassName: EnableProxyServer
 * @Date: 2021/5/27 10:03
 * @Author: bug4j/CYS
 * @Description: TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RegisterProxyServlet.class)
@EnableConfigurationProperties(ProxyConfigurationProperties.class)
public @interface EnableProxyServer {
}

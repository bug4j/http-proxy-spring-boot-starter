English DOCs | (中文文档)[./README.md]

# http-proxy-spring-boot-starter
simple http proxy api to transfer any http request to a specific host. 
this just a spring starter for smiley-http-proxy-servlet. see [smiley-http-proxy-servlet](https://github.com/dsmiley/HTTP-Proxy-Servlet) for more;

# usage
you can use like this:

```yml
  bug4j:
    proxy: 
      target-server-url: http://some.host.name:port 
```
```java
    @SpringBootApplication
    @EnableProxyServer // adding the core annoation to you SpringBootApplication class, and 
    public class YourApplication {
        public static void main(String[] args) {
            SpringApplication.run(YourApplication.class, args);
        }
    }
```

then you can get the response you need from you target host by access `http://you.proxy.server.host:port/proxy/your/request/uri`

notice that the request url has a segment `/proxy` by default, you can change it by using the fllowing config:
```yml
bug4j:
    proxy: 
      api-prefix: /prefix 
``` 

you can also use the request-headers config property to customize the request header before the request send to the target server, set header value to false or null to remove target request header, for example:
```yml
    bug4j: 
        proxy: 
            request-headers: 
                Authorization: some token string
                AuthKey: false
```

you can use a simlar way to config response headers too.

# Alternatives
This project is intentionally simple and limited in scope. As such it may not meet your needs, so consider looking at these alternatives:

- [Jetty's ProxyServlet](https://www.eclipse.org/jetty/documentation/9.4.x/proxy-servlet.html) This is perhaps the closest competitor (simple, limited scope, no dependencies), and may very well already be on your classpath.
- [Netflix's Zuul](https://github.com/Netflix/zuul)
- [Charon](https://github.com/mkopylec/charon-spring-boot-starter)
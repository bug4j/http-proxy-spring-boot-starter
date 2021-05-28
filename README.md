**[English DOCs](README-EN.md) | 中文文档**

# http-proxy-spring-boot-starter
本项目提供一个简单的http 代理实现，通过简单的配置就可以给你的项目添加一个代理接口，并且不影响其他接口的正常访问。
本项目只是给`smiley-http-proxy-servlet`添加自动配置功能，使其同其他springboot组件一样开箱即用。访问[smiley-http-proxy-servlet](https://github.com/dsmiley/HTTP-Proxy-Servlet)查看更多信息。


# 使用方式
最简单的使用方式：

pom.xml:
```xml
<dependency>
    <groupId>cn.bug4j</groupId>
    <artifactId>http-proxy-spring-boot-starter</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

application.yml:
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

当你的项目启动后你可以通过请求 `http://you.proxy.server.host:port/proxy/your/request/uri` 来访问你的没目标服务器。

你应该注意到了请求路径里面多了一段 `/proxy`, 如果这不符合你的要求，你可以通过一下配置来修改代理拦截的路径:
```yml
bug4j:
  proxy: 
    api-prefix: /prefix 
``` 

你也可以通过以下配置来修改发往代理服务其的请求的请求头，如果需要去掉某些请求头，可以将值设置为 false 或者 null:
```yml
bug4j: 
  proxy: 
    request-headers: 
      Authorization: some token string
        AuthKey: false
```

同样，你也可以使用类似的配置在代理响应返回给客户端之前修改响应头。

# 其他选择
这个项目的目标是提供一个最基础的代理服务功能，因此，他可能无法满足你的项目需求。如果需要更复杂的功能，请考虑其他方案，比如：

- [Jetty's ProxyServlet](https://www.eclipse.org/jetty/documentation/9.4.x/proxy-servlet.html) : 这个同样也很简单，如果你使用jetty，那你可以直接使用。
- [Netflix's Zuul](https://github.com/Netflix/zuul)：功能强大，能配合其他组件实现很多复杂的需求，前提是你需要这么复杂的功能。
- [Charon](https://github.com/mkopylec/charon-spring-boot-starter)：功能也很强大，依赖精简
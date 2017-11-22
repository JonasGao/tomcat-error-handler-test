spring-tomcat-filter-error-handler
---

测试在使用 `spring-boot-starter-web` 的情况下。通过发布 `war` 包到外部 `Tomcat`后，在 `Filter` 中抛出的异常如何拦截并处理返回自定义内容

### 测试结果

通用的 `Spring` 定义的异常拦截方式，如

- `@ControllerAdvice`
- `HandlerExceptionResolver`

不能有效的进行全局拦截。

`Spring` `Filter` 异常的拦截实现在 `ErrorPageFilter` 的 `doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)` 实现中。

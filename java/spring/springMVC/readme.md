演示SpringMVC执行流程
目录结构
src/main/java
└── com/example/book
├── BookApplication.java       // 启动类
├── controller
│   └── BookController.java    // 控制器(Controller)
├── model
│   └── Book.java              // 数据模型(Model)
└── service
├── BookService.java       // 业务接口
└── BookServiceImpl.java   // 业务实现
src/main/resources
└── templates
└── bookInfo.html          // 视图(View，使用Thymeleaf)

当用户在浏览器中访问 http://localhost:8080/book?id=1 时，Spring MVC 的工作流程如下：
用户发送请求
用户通过浏览器发送 HTTP GET 请求，请求地址包含书籍 ID 参数
DispatcherServlet 接收请求
Spring Boot 自动配置的 DispatcherServlet 拦截所有请求（这一步在 Spring Boot 中是自动配置的，无需手动在 web.xml 中配置）
HandlerMapping 查找处理器
处理器映射器（由@GetMapping注解驱动）根据请求路径/book找到对应的处理器方法BookController.getBookInfo()
HandlerAdapter 执行处理器
处理器适配器解析请求参数id=1，并调用BookController.getBookInfo()方法
Controller 处理业务逻辑
控制器调用BookService的getBookById(1)方法获取书籍信息
服务层返回 ID 为 1 的书籍数据
控制器将书籍数据存入Model对象，并返回视图名称bookInfo
返回 ModelAndView
处理器适配器将包含模型数据和视图名称的ModelAndView返回给DispatcherServlet
ViewResolver 解析视图
Spring Boot 自动配置的 Thymeleaf 视图解析器根据视图名称bookInfo，找到templates/bookInfo.html模板文件
View 渲染
Thymeleaf 视图引擎将模型中的书籍数据填充到 HTML 模板中，生成最终的 HTML 响应内容
响应请求
DispatcherServlet将渲染后的 HTML 返回给浏览器，用户看到书籍详情页面
Spring Boot 与传统 Spring MVC 的区别
在这个 Spring Boot 案例中，我们看不到显式配置的：
前端控制器（DispatcherServlet）：Spring Boot 自动配置
处理器映射器和适配器：通过@GetMapping等注解自动配置
视图解析器：引入 Thymeleaf 依赖后自动配置
但核心的 MVC 工作流程完全一致，Spring Boot 只是通过自动配置简化了开发，让我们可以更专注于业务逻辑实现。
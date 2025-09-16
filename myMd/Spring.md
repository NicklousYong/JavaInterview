// spring题目
1.Spring的IOC和AOP的机制？
    IOC:控制反转,将对象的创建、依赖关系的维护权从业务代码转移到Spring容器，实现了"依赖注入"。
    AOP:面向切面编程：面向切面编程是面向对象编程的补充，旨在把公共的业务代码抽取出来，对这块逻辑进行增强。我们把这块代码称作"切面"。
        核心概念：
            切面：封装横切关注点的类（如LogAspect），包含通知和切入点。
            切入点：定义哪些方法需要增强(如"所有UserService的方法");
            通知：增强的具体逻辑(如日志记录)。
            目标对象：被增强的对象(如UserServiceImpl)。
            代理对象：AOP生成的对象。

2.AOP的实现机制： 
    若目标对象实现了接口，使用JDK动态代理。
    若目标对象未实现接口，使用CGLIB动态代理。


3.Autowired注解和Resource注解有什么不同  
    1.Autowired注解来自Spring框架，按类型注入。
      Resource注解来自Javax规范，默认按名称注入。
    2.注入依据不同
        Autowired按照类型匹配，优先根据class查找容器中的Bean。如果存在多个类型相同的Bean,则需要根据名称进行区分(使用Qualifier注解)
        Resource注解按照名称匹配，如果没有找到符合名称的Bean,则会回到按照类型匹配。
    3.支持注入的位置不同
        @Autowired支持用于构造器、方法、参数上。
        @Resource不支持构造器上的注入。
    4.Autowired注解可以通过required属性来指定是否必须注入。(若required为false,则依赖不存在的时候不会抛出异常)
        @Resource没有required属性，默认必须注入，否则抛出异常。
 
4.依赖注入的方式有几种，各是什么?
    1.构造器注入（Constructor Injection）：通过目标对象的构造器参数，将依赖对象注入到目标对象中。
    2.Setter方法注入：通过目标的setter方法将属性注入到目标对象中。
    3.字段注入:直接通过目标对象的成员字段注入依赖，无需构造器或 Setter 方法，依赖容器通过反射直接给字段赋值。
        这种方法常用，但是依赖于反射，而反射赋值在构造器之后，而final的赋值又在构造器中，所以这种方法赋值的字段无法定义为final;


6.SpringMVC的工作流程
    1.用户发送请求到DispatcherServlet(前端控制器)
    2.前端控制器(DispatcherServlet)接受请求
        DispatcherSerlet是SpringMVC的核心，统一负责所有请求的处理。
    3.处理映射器(HandlerMapping)查找处理器
        DispatcherServlet通过HandlerMapping查找与请求URL匹配的处理器(Controller)。
    4.处理器适配器(HandlerAdapter)调用处理器
        DispatcherServlet通过HandlerAdapter调用找到的处理器方法。
    5.处理器(Controller)方法执行业务逻辑
        处理器方法执行业务逻辑，处理请求参数，并返回ModelAndView对象。
    6.视图解析器(ViewResolver)解析视图
        DispatcherServlet通过ViewResolver将逻辑视图名解析为具体的视图对象(如JSP)。
    7.渲染视图
        视图对象使用Model数据渲染最终的HTML页面。

7.Spring中如何设定重定向和转发的？
    1.重定向：使用"redirect:"前缀
        return "redirect:/newUrl";
        重定向会发送一个HTTP 302响应，通知浏览器发起新的请求。
    2.转发：使用"forward:"前缀
        return "forward:/newUrl";
        转发是在服务器内部完成的，浏览器地址栏不会改变。

8.重定向和转发的区别？
    1.重定向是客户端行为，浏览器地址栏会改变；转发是服务器行为，地址栏不变。
    2.重定向会发起新的请求，转发是在一次请求内完成的。
    3.重定向可以跨域，转发只能在同一应用内。

9.SpringMVC常用的注解有哪些？
    1.@Controller:标识一个控制器类，处理HTTP请求。
    2.@RequestMapping:映射请求URL到处理方法。
    3.@GetMapping/@PostMapping:分别映射GET和POST请求。
    4.@PathVariable:绑定URL中的变量到方法参数。
    5.@RequestParam:绑定请求参数到方法参数。
    6.@ModelAttribute:绑定请求数据到模型对象。
    7.@ResponseBody:将方法返回值作为HTTP响应体返回，常用于RESTful服务。
    8.@RestController:组合了@Controller和@ResponseBody，简化RESTful服务的开发。
    9.@ExceptionHandler:定义异常处理方法。
   10.@InitBinder:定制数据绑定过程。

10.Spring中事务的传播行为有哪些？
    1.REQUIRED(默认):如果存在事务，则加入该事务；如果没有，则创建一个新的事务。
    2.REQUIRES_NEW:总是创建一个新的事务，如果存在事务，则将其挂起。
    3.SUPPORTS:如果存在事务，则加入该事务；如果没有，则以非事务方式执行。
    4.NOT_SUPPORTED:总是以非事务方式执行，如果存在事务，则将其挂起。
    5.MANDATORY:必须在一个已有的事务中执行，如果没有事务则抛出异常。
    6.NEVER:必须以非事务方式执行，如果存在事务则抛出异常。
    7.NESTED:如果存在事务，则在嵌套事务中执行；如果没有，则创建一个新的事务。

11.解释一下Spring bean的生命周期
    1.实例化(Instantiation):Spring容器通过反射创建Bean实例。
    2.属性赋值(Population):Spring容器将配置的属性值注入到Bean实例中。
    3.初始化(Initialization):如果Bean实现了InitializingBean接口，调用afterPropertiesSet方法；如果配置了init-method，调用指定的初始化方法。
    4.使用(Usage):Bean实例被应用程序使用。
    5.销毁(Destruction):如果Bean实现了DisposableBean接口，调用destroy方法；如果配置了destroy-method，调用指定的销毁方法。当容器关闭时，执行销毁操作。
12.Spring中Bean的作用域有哪些？
    1.Singleton(单例，默认):每个Spring IoC容器中只有一个Bean实例。
    2.Prototype(原型):每次请求都会创建一个新的Bean实例。
    3.Request(请求):在Web应用中，每个HTTP请求都会创建一个新的Bean实例。
    4.Session(会话):在Web应用中，每个HTTP会话都会创建一个新的Bean实例。
    5.Application(应用):在Web应用中，整个ServletContext范围内只有一个Bean实例。
    6.WebSocket:在WebSocket会话范围内，每个WebSocket会话都会创建一个新的Bean实例。

13.Spring中如何处理异常？
    使用@ControllerAdvice和@ExceptionHandler注解定义全局异常处理器。

14.spring中用到了哪些设计模式
    1.单例模式(Singleton Pattern):Spring容器默认以单例模式管理Bean实例，确保每个Bean在容器中只有一个实例。
    2.工厂模式(Factory Pattern):Spring使用工厂模式创建Bean实例，通过配置文件或注解定义Bean的创建方式。
    3.代理模式(Proxy Pattern):Spring AOP使用动态代理为目标对象创建代理对象，实现横切关注点的增强。
    4.模板方法模式(Template Method Pattern):Spring的JdbcTemplate和RestTemplate等类使用模板方法模式封装了常见的操作流程，简化了数据库和RESTful服务的访问。
    5.观察者模式(Observer Pattern):Spring的事件机制使用观察者模式，允许对象订阅和监听特定事件的发生。
    7.适配器模式(Adapter Pattern):Spring的HandlerAdapter用于适配不同类型的处理器，使其能够被DispatcherServlet调用。
    8.装饰器模式(Decorator Pattern):Spring的BeanPostProcessor允许在Bean初始化前后对Bean进行增强，类似于装饰器模式。
    9.责任链模式(Chain of Responsibility Pattern):Spring Security使用责任链模式处理请求的认证和授权。
    10.策略模式(Strategy Pattern):Spring的不同事务管理器实现了策略模式，允许根据不同的需求选择合适的事务管理策略。
15.Spring Boot的自动配置原理是什么？
    1.条件注解(Conditional Annotations):Spring Boot使用一系列条件注解（如@ConditionalOnClass, @ConditionalOnMissingBean等）来判断某些类或Bean是否存在，从而决定是否应用特定的配置。
    2.自动配置类(Auto-Configuration Classes):Spring Boot通过@EnableAutoConfiguration注解启用自动配置功能，这个注解会扫描classpath下的META-INF/spring.factories文件，加载所有声明的自动配置类。
    3.配置属性(Configuration Properties):Spring Boot允许通过application.properties或application.yml文件配置应用程序的属性，这些属性可以影响自动配置的行为。
    4.优先级和覆盖(Precedence and Overrides):用户可以通过自定义配置类或@Bean方法覆盖自动配置的Bean，从而实现个性化定制。
    5.启动过程(Startup Process):在应用启动时，Spring Boot会初始化Spring容器，加载自动配置类，并根据条件注解的判断结果创建和配置Bean实例。
    6.简化配置(Simplified Configuration):自动配置大大减少了开发者需要编写的样板代码，使得应用程序可以更快地启动和运行。
    7.扩展性(Extensibility):开发者可以通过创建自定义的自动配置类，扩展Spring Boot的自动配置功能，以满足特定的业务需求。
16.@SpringBootApplication原理
    @SpringBootApplication是一个复合注解，结合了以下三个注解的功能：
    1.@Configuration:标识该类为Spring配置类，允许定义@Bean方法来注册Bean。
    2.@EnableAutoConfiguration:启用Spring Boot的自动配置功能，根据classpath中的依赖和配置自动配置Spring应用程序。
    3.@ComponentScan:启用组件扫描，自动发现并注册同一包及其子包中的Spring组件（如@Controller, @Service, @Repository等）。
    通过使用@SpringBootApplication注解，开发者可以简化配置过程，只需在主应用程序类上添加该注解，即可启用自动配置和组件扫描功能，从而快速构建Spring Boot应用程序。
17.Spring Boot中如何实现配置文件的分环境管理？
    1.使用不同的配置文件：Spring Boot支持根据不同的环境加载不同的配置文件。默认情况下，Spring Boot会加载application.properties或application.yml文件。可以创建多个配置文件，如application-dev.properties, application-prod.properties等，分别对应不同的环境。
    2.激活配置文件：通过设置spring.profiles.active属性来指定当前激活的配置文件。例如，可以在application.properties中设置：
        spring.profiles.active=dev
        这样，Spring Boot会加载application-dev.properties文件中的配置。
    3.命令行参数：可以通过命令行参数来指定激活的配置文件，例如：
        java -jar myapp.jar --spring.profiles.active=prod
    4.环境变量：也可以通过环境变量来设置激活的配置文件，例如在Linux或MacOS中：
        export SPRING_PROFILES_ACTIVE=prod
    5.优先级：Spring Boot会按照一定的优先级加载配置文件，命令行参数 > 环境变量 > application-{profile}.properties > application.properties。因此，可以通过不同的方式覆盖配置。
    6.使用@ConfigurationProperties注解：可以将配置属性绑定到Java类中，方便管理和使用。例如：
        @ConfigurationProperties(prefix="app")
        public class AppProperties {
            private String name;
            private String version;
            // getters and setters
        }
    7.使用@Profile注解：可以在Bean定义上使用@Profile注解，指定该Bean只在特定环境下加载。例如：
        @Bean
        @Profile("dev")
        public DataSource devDataSource() {
            // return dev DataSource
        }
        @Bean
        @Profile("prod")
        public DataSource prodDataSource() {
            // return prod DataSource
        }
    通过以上方法，Spring Boot实现了灵活的配置文件分环境管理，方便开发者根据不同的部署环境进行配置调整。

18.Spring Boot中如何实现热部署？
    1.Spring DevTools:Spring Boot提供了一个名为Spring DevTools的模块，专门用于开发时的热部署。只需在项目的依赖中添加以下依赖：
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
        DevTools会监视类路径上的文件变化，当检测到文件变化时，会自动重启应用程序。
    2.自动重启:DevTools会在类路径上的文件发生变化时，自动重启应用程序。它使用两个类加载器，一个用于加载应用程序类，另一个用于加载第三方库。当应用程序类发生变化时，只重新加载应用程序类，而不重新加载第三方库，从而提高重启速度。
    3.禁用缓存:DevTools会禁用模板引擎（如Thymeleaf）的缓存，以便在开发过程中可以立即看到模板文件的更改效果。
    4.远程调试:DevTools还支持远程调试功能，可以通过配置远程调试端口，实现对远程运行的Spring Boot应用程序进行热部署。
    5.集成IDE:大多数IDE（如IntelliJ IDEA, Eclipse）都支持与Spring DevTools集成，可以通过IDE的构建工具（如Maven, Gradle）自动触发DevTools的热部署功能。
    6.注意事项:热部署主要用于开发环境，不建议在生产环境中使用，因为频繁的重启可能会影响性能和稳定性。

19.Spring Boot中如何实现日志管理？
    1.默认日志框架:Spring Boot默认使用Logback作为日志框架，但也支持其他日志框架，如Log4j2和Java Util Logging。可以通过在项目的依赖中添加相应的日志框架依赖来切换日志框架。
    2.配置文件:可以通过application.properties或application.yml文件配置日志相关的属性。例如：
        logging.level.root=INFO
        logging.level.com.example=DEBUG
        logging.file.name=app.log
        logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
        这些配置可以设置日志级别、日志文件名和日志输出格式等。
    3.自定义配置:可以创建自定义的Logback或Log4j2配置文件（如logback-spring.xml或log4j2-spring.xml），并将其放在classpath下。Spring Boot会自动加载这些配置文件，从而实现更复杂的日志配置。
    4.日志级别:可以通过配置不同包或类的日志级别，实现对不同模块的日志输出进行控制。常见的日志级别包括TRACE, DEBUG, INFO, WARN, ERROR。
    5.日志输出位置:可以配置日志输出到控制台、文件或其他目标（如数据库、远程服务器）。例如，可以通过logging.file.name属性将日志输出到指定文件。
    6.集成第三方工具:可以将Spring Boot的日志与第三方日志管理工具（如ELK Stack, Splunk）集成，实现集中化的日志管理和分析。
    7.动态调整日志级别:在运行时，可以通过JMX或Actuator端点动态调整日志级别，而无需重启应用程序。
    8.使用AOP记录日志:可以使用Spring AOP在方法执行前后记录日志，实现对关键业务操作的跟踪和监控。
20.Spring Boot中如何实现安全管理？
    1.Spring Security:Spring Boot集成了Spring Security框架，提供了强大的安全管理功能。只需在项目的依赖中添加以下依赖：
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        Spring Security会自动配置基本的安全机制，如用户认证和授权。
    2.用户认证:可以通过配置内存中的用户、数据库中的用户或自定义用户服务来实现用户认证。例如，可以在application.properties中配置内存用户：
        spring.security.user.name=user
        spring.security.user.password=pass
    3.授权管理:可以通过配置基于角色的访问控制（RBAC）来实现授权管理。例如，可以使用@PreAuthorize注解在方法上指定访问权限：
        @PreAuthorize("hasRole('ADMIN')")
        public void adminMethod() { ... }
    4.自定义登录页面:可以通过配置SecurityConfig类来自定义登录页面和登录逻辑。例如：
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin().loginPage("/custom-login").permitAll();
        }
    5.密码加密:可以使用Spring Security提供的PasswordEncoder接口对用户密码进行加密存储，增强安全性。例如：
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    6.防止CSRF攻击:Spring Security默认启用CSRF保护，可以通过配置HttpSecurity对象来定制CSRF策略。
    7.集成OAuth2和JWT:可以通过添加相应的依赖，实现基于OAuth2和JWT的认证和授权机制，适用于微服务架构中的安全管理。
    8.使用Actuator监控安全状态:Spring Boot Actuator提供了多个端点，可以监控应用程序的安全状态，如/health和/metrics端点。
    9.日志记录:可以通过配置日志框架记录安全相关的事件，如登录失败、权限拒绝等，便于审计和分析。
    10.定期更新依赖:保持Spring Security及其相关依赖的最新版本，以防止已知的安全漏洞。

21.springboot如何配置一个项目监听多个端口，
22.如何配置一个项目配置多个数据源，并在特定的接口调用中分别调用不同的数据源
23.yml和properties的区别
24.springboot如何根据不同环境加载不同的配置文件
25.springboot如何实现定时任务
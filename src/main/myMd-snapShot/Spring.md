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
    若目标对象实现了接口，使用JDK动态代理。（JDK亲生的反而有缺陷，所以才会有扩展）
    若目标对象未实现接口，使用CGLIB动态代理。
    JDK动态代理出现在运行时，而ASPECTJ动态代理出现在编译时。

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
        也就是查找对应的handler。
    5.处理器(Controller)方法执行业务逻辑
        在这一步之前执行了拦截器的preHandle方法。
        处理器方法执行业务逻辑，处理请求参数，并返回ModelAndView对象。
        zai这一步之后执行拦截器的postHandle方法。
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
    3.初始化前置处理（BeanPostProcessor#postProcessBeforeInitialization):
        容器中的所有BeanPostProcessor（Bean 后置处理器）会对 Bean 执行前置处理，可修改 Bean 的状态或替换 Bean 实例。
        实现 AOP 代理、数据校验等通用逻辑（如 Spring 的事务代理在此阶段可能生效）。
    4. 初始化(Initialization):如果Bean实现了InitializingBean接口，调用afterPropertiesSet方法；如果配置了init-method，调用指定的初始化方法。
            BeanNameAware：注入当前 Bean 在容器中的名称（setBeanName(String name)）。
            BeanFactoryAware：注入当前 BeanFactory 实例（setBeanFactory(BeanFactory factory)）。
            ApplicationContextAware：注入当前 ApplicationContext 实例（setApplicationContext(ApplicationContext context)）。
    5.初始化后置处理(BeanPostProcessor#postProcessAfterInitialization):
        BeanPostProcessor对 Bean 执行后置处理，通常是最终的增强（如 AOP 代理对象在此阶段生成）。
        若此处返回新的代理对象，容器后续将使用代理对象而非原始 Bean。
    6.使用(Usage):Bean实例被应用程序使用。
    7.销毁(Destruction):如果Bean实现了DisposableBean接口，调用destroy方法；如果配置了destroy-method，调用指定的销毁方法。
    当容器关闭时，执行销毁操作。

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
    4.模板方法模式(Template Method Pattern):Spring的JdbcTemplate和RestTemplate等类使用模板方法模式封装了常见的操作流程，
简化了数据库和RESTful服务的访问。
    5.观察者模式(Observer Pattern):Spring的事件机制使用观察者模式，允许对象订阅和监听特定事件的发生。
    7.适配器模式(Adapter Pattern):Spring的HandlerAdapter用于适配不同类型的处理器，使其能够被DispatcherServlet调用。
    8.装饰器模式(Decorator Pattern):Spring的BeanPostProcessor允许在Bean初始化前后对Bean进行增强，类似于装饰器模式。
    9.责任链模式(Chain of Responsibility Pattern):Spring Security使用责任链模式处理请求的认证和授权。
    10.策略模式(Strategy Pattern):Spring的不同事务管理器实现了策略模式，允许根据不同的需求选择合适的事务管理策略。

15.Spring Boot的自动配置原理是什么？
    1.条件注解(Conditional Annotations):Spring Boot使用一系列条件注解
（如@ConditionalOnClass, @ConditionalOnMissingBean等）来判断某些类或Bean是否存在，从而决定是否应用特定的配置。
    2.自动配置类(Auto-Configuration Classes):Spring Boot通过@EnableAutoConfiguration注解启用自动配置功能，
        这个注解会扫描classpath下的META-INF/spring.factories文件，加载所有声明的自动配置类。
    3.配置属性(Configuration Properties):Spring Boot允许通过application.properties或application.yml文件配置应用程序的属性，这些属性可以影响自动配置的行为。
    4.优先级和覆盖(Precedence and Overrides):用户可以通过自定义配置类或@Bean方法覆盖自动配置的Bean，从而实现个性化定制。
    5.启动过程(Startup Process):在应用启动时，Spring Boot会初始化Spring容器，加载自动配置类，并根据条件注解的判断结果创建和配置Bean实例。
    6.简化配置(Simplified Configuration):自动配置大大减少了开发者需要编写的样板代码，使得应用程序可以更快地启动和运行。
    7.扩展性(Extensibility):开发者可以通过创建自定义的自动配置类，扩展Spring Boot的自动配置功能，以满足特定的业务需求。

16.@SpringBootApplication原理
    @SpringBootApplication是一个复合注解，结合了以下三个注解的功能：
        1.@SpringBootConfiguration:标识该类为Spring配置类，允许定义@Bean方法来注册Bean。
        2.@EnableAutoConfiguration:启用Spring Boot的自动配置功能，根据classpath中的依赖和配置自动配置Spring应用程序。
            下面还有一个@Import注解，导入 AutoConfigurationImportSelector.class，这里是自动导入的核心逻辑。
            Spring Boot 在启动时，会通过 SpringFactoriesLoader 工具类读取 
            META-INF/spring.factories 文件（位于 spring-boot-autoconfigure 等 JAR 中）。该文件中声明了
            所有可用的自动配置类（如 WebMvcAutoConfiguration、DataSourceAutoConfiguration 等），每个配置类对应一种场景的自动配置。
            自动配置类并非全部生效，而是通过条件注解（@Conditional 系列）按需启用：
                @ConditionalOnClass：当类路径中存在指定类时生效（如 WebMvcAutoConfiguration 依赖 DispatcherServlet 类）。
                @ConditionalOnMissingBean：当容器中不存在指定 Bean 时生效（允许用户自定义 Bean 覆盖自动配置）。
                @ConditionalOnProperty：根据配置文件中的属性值决定是否生效（如 spring.datasource.enabled=true）。
        3.@ComponentScan:启用组件扫描，自动发现并注册同一包及其子包中的Spring组件（如@Controller, @Service, @Repository等）。
    通过使用@SpringBootApplication注解，开发者可以简化配置过程，
    只需在主应用程序类上添加该注解，即可启用自动配置和组件扫描功能，从而快速构建Spring Boot应用程序。

17.Spring Boot中如何实现配置文件的分环境管理？
    1.使用不同的配置文件：
        Spring Boot支持根据不同的环境加载不同的配置文件。默认情况下，Spring Boot会加载application.properties或application.yml文件。
        可以创建多个配置文件，如application-dev.properties, application-prod.properties等，分别对应不同的环境。
    2.激活配置文件：通过设置spring.profiles.active属性来指定当前激活的配置文件。例如，可以在application.properties中设置：
        spring.profiles.active=dev
        这样，Spring Boot会加载application-dev.properties文件中的配置。
    3.命令行参数：可以通过命令行参数来指定激活的配置文件，例如：
        java -jar myapp.jar --spring.profiles.active=prod
    4.环境变量：也可以通过环境变量来设置激活的配置文件，例如在Linux或MacOS中：
        export SPRING_PROFILES_ACTIVE=prod
    5.优先级：Spring Boot会按照一定的优先级加载配置文件，
            命令行参数 > 环境变量 > application-{profile}.properties > application.properties。因此，可以通过不同的方式覆盖配置。
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
    8.同名的 properties 文件优先级高于 yml 文件
        

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

19.springboot如何配置一个项目监听多个端口？
server:
    ports: 8080, 8081, 8082  # 同时监听 8080、8081、8082 三个端口

20.BeanFactory和FactoryBean有什么区别？
    BeanFactory是一个工厂接口，用于自定义Bean的创建逻辑。实现了FactoryBean接口的类可以作为Bean定义的一部分，
        Spring容器会调用其getObject方法来获取实际的Bean实例。
    BeanFactory是Spring容器的核心接口，负责管理和提供Bean实例。它定义了获取Bean实例的方法，如getBean()，并负责Bean的生命周期管理。
        1.FactoryBean被称作工厂Bean，用于定义Bean创建的细节，如复杂的初始化，动态代理等。
        2.当一个类实现了 FactoryBean 接口时，Spring 容器中注册的 Bean 名称（id）仍然是这个工厂类本身的名称，只是默认情况下，
        通过这个名称获取到的对象是 getObject() 方法返回的目标对象。
            比如：@Component("complexObject") // 这里的名称属于 CustomFactoryBean 本身
                public class CustomFactoryBean implements FactoryBean<ComplexObject> { ... }
            这里当调用 context.getBean("complexObject") 时，返回的是 CustomFactoryBean.getObject() 生成的 ComplexObject 实例（目标对象）。
            若需获取工厂 Bean 自身，需在 ID 前加 & 前缀：
                CustomFactoryBean factory = context.getBean("&complexObject", CustomFactoryBean.class);
    总结：BeanFactory强调Bean的管理，提供Bean的获取和注册。而FactoryBean则主要关注Bean的创建流程。

21.BeanFactory和ApplicationContext有什么区别?
    BeanFactory是Spring的顶层接口，定义了Bean的基本规范。ApplicationContext是BeanFactory的子接口，提供了更丰富的功能。
    1.BeanFactory不会立即创建Bean,只有在getBean()的时候才会创建Bean。而ApplicationContext回一次性创建所有单例Bean,提前暴露配置错误。
    2.ApplicationContext支持国际化消息资源处理,可以通过MessageSource接口获取国际化消息。而BeanFactory不支持。

22.几种ApplicationContext:
    ClassPathXmlApplicationContext:
    AnnotationConfigApplicationContext
    WebApplicationContext

23.介绍下SqlSessionFactoryBean的原理
    
22.如何配置一个项目配置多个数据源，并在特定的接口调用中分别调用不同的数据源
    见multiDataSource;

23.yml和properties的区别
    properties采用键值对结构，格式为 key=value
    yml采用缩进表示层级结构(不允许使用tab,通常使用两个空格)
    yml支持数组，用"-"表示
    
24.springboot如何根据不同环境加载不同的配置文件
    Spring Boot 规定，多环境配置文件需遵循 application-{profile}.yml（或 .properties）的命名格式，其中 {profile} 是环境标识（如 dev、test、prod）。
    例如：
        application.yml：默认配置文件（所有环境共享的配置）
        application-dev.yml：开发环境配置
        application-test.yml：测试环境配置
        application-prod.yml：生产环境配置
    指定各个环境配置文件的四种方式：
        1.在 application.yml 中通过 spring.profiles.active 指定激活的环境：
            spring:
                profiles:
                    active: dev  # 激活开发环境，可改为 test 或 prod
        2.通过命令行参数指定（部署时常用）
                # 启动开发环境
                java -jar app.jar --spring.profiles.active=dev
                # 启动生产环境
                java -jar app.jar --spring.profiles.active=prod
        3.通过系统环境变量指定
                # Linux/Mac
                export SPRING_PROFILES_ACTIVE=prod
                # Windows（命令提示符）
                set SPRING_PROFILES_ACTIVE=prod
                # 启动应用（无需额外参数）
                java -jar app.jar
        4.通过 JVM 参数指定
                java -Dspring.profiles.active=test -jar app.jar
          优先级从高到低为：
                命令行参数 > JVM 参数 > 系统环境变量 > 配置文件指定
25.springboot如何实现定时任务
        查看schedule包下内容
26.Springboot如何实现幂等
        1.基于数据库唯一索引，防止相同的插入。
            通过在数据库表中为关键字段（如订单号、用户ID等）创建唯一索引，确保同一业务请求只能插入一次，适合创建类操作。
            实现步骤：
                在数据库表中为关键字段添加唯一索引。
                在业务逻辑中捕获唯一约束异常，返回友好提示。
        2.基于Token令牌机制：通过预生成唯一 Token 并验证的方式，确保同一请求仅被处理一次，适合前端表单提交、支付请求等场景。
            实现步骤：
                生成 Token 并返回给前端：
                    前端请求 Token，后端生成唯一令牌（如 UUID）存入 Redis，设置过期时间。
                前端提交时携带 Token：
                    前端将 Token 放入请求头或参数中，后端验证 Token 有效性。
                验证并删除 Token：
                    后端校验 Token 存在后，原子性删除 Token（防止重复提交），再处理业务。
        3.基于分布式锁 ：通过 Redis 或 Zookeeper 实现分布式锁，确保同一时间只有一个请求处理业务，适合库存扣减、转账等场景。
            实现步骤：
                定义唯一锁标识：
                    以业务唯一键（如订单号）作为锁的 key。
                获取锁并处理业务：
                    尝试获取锁，获取成功则处理业务，失败则返回 “操作中”。
        4.基于状态机：
                通过状态流转控制，确保同一业务只能从特定状态变更到目标状态，适合订单状态更新等场景。
            实现示例：
                订单状态流转规则：待支付 → 已支付 → 已发货 → 已完成，通过 SQL 条件确保状态变更的幂等性。
                每次提交时检查状态，满足状态条件时才允许提交。

27.过滤器，拦截器，监听器
        1.过滤器:过滤器是JAVA EE规范的一部分，请求到达Servlet之前，过滤器可以对请求进行预处理，或者对响应进行后处理。
        过滤器通常用于日志记录、权限检查、请求修改等。
            过滤器通过实现javax.servlet.Filter接口来定义，并在web.xml中配置，或者通过注解@WebFilter进行配置。
            可拦截所有请求，包括静态资源请求。只能在请求、响应的生命周期中处理，无法直接访问Spring容器中的bean。
        2.拦截器：Interceptor 是 Spring MVC 提供的组件，运行在 Spring 容器中，用于拦截 Controller 方法的调用，可在方法执行前后进行处理。
            对Controller进行预处理。记录方法执行时间（性能监控）。
            基于JAVA反射机制，属于Spring框架范畴，仅拦截Controller请求，无法拦截静态资源请求。可以调用Spring的bean。
        3.监听器：Listener 是 Java EE 规范的一部分，用于监听特定事件的发生，如请求创建、会话创建等。监听器通过实现 javax.servlet.ServletContextListener、HttpSessionListener 等接口来定义，并在 web.xml 中配置，或者通过注解@WebListener进行��置。
            监听器用于监听应用程序的生命周期事件，如应用启动、关闭，用户会话创建、销毁等。可以在应用启动时初始化资源，在应用关闭时释放资源。
            主要作用：监听应用启动/关闭。监听session的创建于销毁。监听请求对象的创建与销毁。
            常见的监听器接口：
                ServletContextListener：监听ServletContext的生命周期事件。
                HttpSessionListener：监听HttpSession的生命周期事件。
                ServletRequestListener：监听ServletRequest的生命周期事件。
            基于事件驱动，被动触发（当监听的事件发生时执行）。可访问 Servlet 上下文（ServletContext），实现全局数据共享。

28.Spring是默认单例吗？是默认懒加载吗？
    Spring默认是饿汉式加载。如果是单例，则使用饿汉式加载，如果非单例，则在每次从容器中初始化才会创建。Spring默认是单例。
    Spring中非单例都是懒加载，且@Lazy对于非单例无效。
    Spring中无法对非单例进行饿汉加载，因为非单例的Scope(作用域)无法确定在什么作用域使用，无法提前创建。

29.Spring循环依赖问题(待深入研究)
    循环依赖的三种场景：
            1.构造器注入循环依赖
            2.Setter注入循环依赖
    
30.Spring文件加载优先级
    同一名称的配置文件，加载顺序为:
        1.应用程序当前目录下的config目录下。
        2.应用程序当前目录下。
        3.classpath下的config目录下。
        4.classpath下。
    注意：1.通过 @PropertySource 注解引入的配置文件，默认优先级低于 application.properties。
         2.当存在properties和yml两种格式的配置文件时，properties优先级高。

31.Spring中声明式事务失效的场景
    1.方法不是public的:@Transactional注解只对public方法有效
    2.自身调用:当一个没有事务的方法A去调用一个有事务的方法B，方法B的事务会失效。原因是Spring事务基于AOP代理实现，类内部调用不会经过代理对象。
            如何解决：通过Spring容器获取当前类的代理对象，然后通过代理对象调用方法B。
                ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                MyService myService = context.getBean(MyService.class);
                myService.methodB(); // 通过代理对象调用
    3.异常被捕获但是没有被抛出：若事务方法内部捕获了异常，但是没有抛出，那么Spring没有办法感知异常，会正常提交事务，导致失败。
            解决办法：捕获异常后手动抛出（或通过 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly() 强制回滚）：
                catch (Exception e) {
                    e.printStackTrace();
                throw new RuntimeException("操作失败"); // 抛出异常，触发回滚
                }
    4.抛出的异常类型不匹配
            @Transactional 默认只对 RuntimeException 及其子类 和 Error 异常回滚。若抛出的是受检异常（如 IOException、SQLException），事务不会回滚。
            解决办法：通过 rollbackFor 属性指定需要回滚的异常类型：
                @Transactional(rollbackFor = Exception.class) // 对所有异常回滚
    5.数据源没有配置事务管理器
            Spring 事务需要对应的事务管理器（如 DataSourceTransactionManager），若未配置，@Transactional 会失效。
    6.事务传播行为配置错误
            若事务传播行为配置为 SUPPORTS、NOT_SUPPORTED、NEVER 等，可能导致事务不生效。
    7.表引擎不支持事务
            若数据库表使用 MyISAM 引擎（不支持事务），即使 Spring 配置了事务，也不会生效。需改用 InnoDB 引擎（支持事务）。

32.Spring中事务的传播机制
    Spring 中的事务传播机制（Transaction Propagation）定义了当一个事务方法调用另一个事务方法时，两个事务之间如何交互（如是否共用一个事务、是否新建事务等）。它通过 @Transactional 注解的 propagation 属性配置，是解决复杂业务中事务嵌套问题的核心机制。
    Spring 支持以下几种事务传播行为：
        1.REQUIRED（默认）：如果当前存在事务，则加入该事务；如果没有，则创建一个新的事务。
        2.REQUIRES_NEW：总是创建一个新的事务，如果当前存在事务，则将其挂起。
        3.SUPPORTS：如果当前存在事务，则加入该事务；如果没有，则以非事务方式执行。
        4.NOT_SUPPORTED：总是以非事务方式执行，如果当前存在事务，则将其挂起。
        5.MANDATORY：必须在一个已有的事务中执行，如果没有事务则抛出异常。
        6.NEVER：必须以非事务方式执行，如果当前存在务则抛出异常。
        7.NESTED：如果当前存在事务，则在嵌套事务中执行；如果没有，则创建一个新的事务。嵌套事务可以独立提交或回滚，但外层事务回滚时，嵌套事务也会回滚。


33.怎么理解约定大于配置？
        “约定大于配置”（Convention over Configuration，简称 CoC）是软件工程中一种重要的设计思想，核心是通过预定义 “合理的默认规则（约定）”
        来减少开发者的配置工作，仅在需要偏离默认规则时才进行手动配置。

34.Spring中的Bean是线程安全的吗
        Spring中的单例Bean默认是非线程安全的。因为单例Bean在整个应用上下文中只有一个实例，多个线程同时访问这个实例时，如果Bean内部有可变状态（如成员变量），
就可能导致线程安全问题。
        解决方法：
            1.使用无状态的Bean：尽量设计无状态的Bean，即不包含任何可变状态的成员变量，这样多个线程访问同一个实例时不会产生冲突。
            2.使用局部变量：将方法中的可变状态定义为局部变量，确保每个线程都有自己的独立副本。
            3.使用同步机制：如果必须使用有状态的Bean，可以通过synchronized关键字或其他并发工具（如ReentrantLock）来控制对共享资源的访问，但这可能会影响性能。
            4.使用原型作用域：将Bean的作用域设置为prototype，这样每次请求都会创建一个新的实例，避免了多线程访问同一个实例的问题，但会增加内存开销。
            5.使用ThreadLocal：对于需要在同一线程内共享数据的场景，可以使用ThreadLocal来存储线程特有的数据，确保数据隔离。
        Prototype作用域的Bean是线程安全的，因为每次请求都会创建一个新的实例，多个线程不会共享同一个实例。
        request作用域：每个 HTTP 请求创建一个实例，仅在当前请求内有效，线程安全（请求内单线程处理）。
        session作用域：每个用户会话创建一个实例，会话内共享，若会话内存在多线程操作（如异步请求），需考虑线程安全。
        application作用域：全局共享（类似单例），线程安全问题同单例 Bean。

35.Spring如何处理线程并发问题
        1.无状态设计：尽量设计无状态的Bean，避免使用可变状态的成员变量，减少线程间的冲突。
        2.局部变量：将方法中的可变状态定义为局部变量，确保每个线程都有自己的独立副本。
        3.同步机制：使用synchronized关键字或并发工具（如ReentrantLock）来控制对共享资源的访问，但需注意性能影响。
        4.原型作用域：将Bean的作用域设置为prototype，每次请求创建一个新的实例，避免多线程访问同一实例的问题。
        5.ThreadLocal：使用ThreadLocal存储线程特有的数据，确保数据隔离，适用于需要在同一线程内共享数据的场景。
        6.并发集合：使用Java提供的并发集合类（如ConcurrentHashMap, CopyOnWriteArrayList）来替代传统集合，提升并发性能。
        7.数据库事务：通过Spring的事务管理机制，确保数据库操作的原子性和一致性，防止数据竞争和不一致问题。
        8.锁机制：在分布式环境中，可以使用分布式锁（如Redis, Zookeeper）来协调多个实例间的资源访问，确保数据一致性。
        9.线程池管理：使用Spring提供的线程池（如TaskExecutor）来管理线程资源，避免频繁创建和销毁线程带来的开销。
        10.异步处理：利用Spring的异步方法（@Async）将耗时操作放到独立线程中执行，提高系统响应速度，同时需注意异步操作的线程安全问题。


36.JDK动态代理和CGLIB动态代理的区别
    JDK动态代理需要目标类至少实现一个接口，代理类会自动生成目标类所有接口的匿名类。
    JDK动态代理基于反射机制，通过 java.lang.reflect.Proxy 类和 InvocationHandler 接口实现，代理类的方法调用会被转发到 InvocationHandler 的 invoke() 方法，再通过反射调用目标类的方法。
    CGLIB无需目标类实现接口，而是动态生成目标类的子类，并重写目标方法实现增强。
    字节码技术：通过 ASM 字节码框架直接操作字节码，生成代理类的 class 文件，性能通常优于 JDK 动态代理。
    

37.什么是切面？
    在 Spring AOP（面向切面编程）中，切面（Aspect）是封装横切关注点（Cross-cutting Concerns）的模块，用于将那些与业务逻辑无关，但却被多个业务模块共同调用的功能（如日志记录、事务管理、权限校验等）抽取出来，实现代码复用和业务逻辑与横切逻辑的解耦。
    切面的核心组成
        一个完整的切面通常包含以下要素：
            切入点（Pointcut）：定义切面要作用的具体位置（哪些类的哪些方法）。例如，“所有 Service 层中以 save 开头的方法”。
            通知（Advice）：定义切面的具体行为和执行时机。例如，“在目标方法执行前打印日志”“在目标方法抛出异常时回滚事务”。
        通知类型包括：
        @Before：目标方法执行前执行
        @After：目标方法执行后执行（无论是否异常）
        @AfterReturning：目标方法正常返回后执行
        @AfterThrowing：目标方法抛出异常后执行
        @Around：环绕目标方法执行（可控制目标方法的执行时机）
            连接点（Joinpoint）：程序执行过程中可以插入切面的所有可能位置（如方法调用、字段赋值等），切入点是连接点的子集（即被选中的连接点）。

38.Spring框架中如何更好地使用JDBC?
    1.使用JdbcTemplate：Spring提供的JdbcTemplate类封装了JDBC的常用操作，简化了数据库访问代码，减少了样板代码，提高了开发效率。
    2.使用NamedParameterJdbcTemplate：相比JdbcTemplate，NamedParameterJdbcTemplate支持命名参数，增强了SQL语句的可读性和维护性。
    3.使用事务管理：通过Spring的声明式事务管理（@Transactional注解）确保数据库操作的原子性和一致性，避免数据不一致问题。
    4.使用RowMapper：实现RowMapper接口将查询结果映射为Java对象，简化结果集处理逻辑。
    5.使用批量操作：利用JdbcTemplate的batchUpdate方法进行批量插入、更新操作，提高性能。
    6.异常处理：Spring将JDBC异常转换为统一的DataAccessException层次结构，简化异常处理逻辑。
    7.连接池配置：结合连接池（如HikariCP, DBCP）优化数据库连接管理，提高应用性能和稳定性。
    8.使用Spring Data JPA或MyBatis等ORM框架：对于复杂的数据库操作，可以考虑使用ORM框架，进一步简化数据访问层代码。

39.JDBCTemplate是什么？
    JDBCTemplate是Spring框架对JDBC的封装工具类，它简化了JDBC操作的复杂性，消除了大量样板代码。
    JDBC的样板代码包括：获取连接、创建语句、执行查询、处理结果集、关闭资源等。
    JDBCTemplate通过提供一系列便捷的方法，帮助开发者更专注于业务逻辑，而不是繁琐的JDBC细节。
    主要功能：
        1.简化数据库操作：提供了多种方法执行SQL语句（如查询、更新、批量操作等），并自动处理资源的获取和释放。
        2.异常处理：将JDBC异常转换为Spring的DataAccessException层次结构，简化异常处理逻辑。
        3.结果集映射：通过RowMapper接口将查询结果映射为Java对象，方便数据处理。
        4.事务支持：与Spring的事务管理无缝集成，支持声明式事务。

40.Spring中默认使用什么代理？
        Spring2.X开始使用CGLIB动态代理，JDK动态代理需要实现接口，具有使用条件，但是CGLIB动态代理没有使用条件，而且Spring把CGLIB动态代理纳入维护，很可控。

41.如果在一个单例对象中注入了一个非线程安全的对象，它会发生什么？怎么解决？
        这样会导致线程安全问题，问题的本质在于这个非线程安全的资源会在各个线程内共享。
        解决方法：
            1.使用ThreadLocal：将非线程安全的对象存储在ThreadLocal中，确保每个线程都有自己的独立副本，避免线程间的冲突。
                线程安全对象必须全局共享（如缓存、计数器），且并发频率不高（锁会带来性能开销）。
            2.使用原型作用域：将非线程安全的对象的作用域设置为prototype，这样每次请求都会创建一个新的实例，避免了多线程访问同一个实例的问题，但会增加内存开销。
                非线程安全对象使用成本高，需要复用但是不能共享。
            3.使用同步机制：如果必须使用单例的非线程安全对象，可以通过synchronized关键字或其他并发工具（如ReentrantLock）来控制对共享资源的访问，但这可能会影响性能。
                在线程池场景下，线程会复用，需在任务结束后调用 remove() 清理 ThreadLocal 中的对象，避免内存泄漏。
                非线程安全对象创建成本高，且线程内需要多次复用。
            4.重构设计：尽量避免在单例Bean中注入非线程安全的对象，考虑将其设计为无状态的Bean或局部变量，减少共享状态。
                适用于非线程安全对象创建成本低（如无复杂初始化逻辑），且无需复用的场景。

42.为什么Spring明知道Bean是单例的，还允许注入线程不安全的对象？(了解即可)
        1.框架无法预判对象的线程安全性，因为对象是否线程安全是运行时的问题，而框架本身是静态的。
        2.单例Bean与线程安全的的"职责分离"Spring的核心是管理对象的生命周期和依赖关系，而线程安全是开发者的责任。
        3.单例模式的"利"远大于"弊"，实际开发中Service,Repository等核心Bean通常涉及为无状态。
        4.框架设计需要遵循"最小约束原则"：只提供必要的机制，不强制不必要的限制。

43.Spring缓存为什么是3级？
        Spring缓存通常分为三级缓存，分别是一级缓存、二级缓存和三级缓存。这种设计主要是为了提高数据访问的效率，减少对数据库的直接访问，从而提升应用性能。
        1.一级缓存（Session Cache）：一级缓存是MyBatis中的概念，存在于SqlSession中。每个SqlSession都有自己的一级缓存，默认情况下，当你在同一个SqlSession中多次查询同一条数据时，MyBatis会从一级缓存中获取数据，而不是每次都去数据库查询。一级缓存的生命周期与SqlSession相同，当SqlSession关闭时，一级缓存也会被清空。
        2.二级缓存（Mapper Cache）：二级缓存也是MyBatis中的概念，存在于Mapper级别。二级缓存是跨SqlSession共享的，当多个SqlSession查询同一条数据时，如果该数据已经存在于二级缓存中，则直接从二级缓存中获取，而不需要再次访问数据库。二级缓存需要在MyBatis配置文件中显式开启，并且需要在Mapper XML文件中配置<cache>标签来启用。
        3.三级缓存（Spring Cache）：三级缓存是Spring框架提供的通用缓存机制，可以与多种缓存实现（如EhCache, Redis, Caffeine等）集成。Spring Cache通过注解（如@Cacheable, @CachePut, @CacheEvict）来简化缓存操作，使得开发者可以轻松地将方法的返回结果缓存在指定的存储介质中。三级缓存的生命周期由具体的缓存实现决定，可以是内存中的，也可以是分布式的。
        总结：三级缓存设计通过不同层次的缓存机制，有效地减少了对数据库的访问频率，提高了应用的响应速度和整体性能。同时，不同层次的缓存可以根据具体需求进行配置和优化，满足���同场景下的性能要求。

44.为什么要用ObjectFactory匿名函数提前暴露呢？

45.如果把Bean的作用域设置为prototype,还会进三级缓存吗？
    原型（prototype）作用域的 Bean 不会进入三级缓存。
    原型 Bean 的核心特性是每次请求都会创建新实例（getBean() 调用一次则创建一次），创建后 Spring 容器不会保留其引用，也不负责后续管理（如销毁）。因此：
    原型 Bean 无需缓存：由于每次都是新实例，不存在 “复用” 需求，自然不需要三级缓存（或任何缓存）存储。
    不涉及循环依赖处理：三级缓存的核心作用是解决单例 Bean 的循环依赖（如 A 依赖 B，B 依赖 A）。而原型 Bean 每次创建都是独立实例，循环依赖时会导致无限递归创建（最终抛出 BeanCurrentlyInCreationException），Spring 不会对原型 Bean 的循环依赖做特殊处理，因此无需三级缓存介入。

46.@Transactional是Spring实现的还是数据库实现的？
    @Transactional 注解的功能实现是Spring 框架与数据库共同协作的结果，两者分别承担不同角色:
    1.注解的核心作用是Spring实现的，这部分包括：
        1.事务的开启与关闭；
        2.传播与行为控制；
        3.隔离级别映射；
        4.异常回滚控制；
    2.事务的底层执行是数据库实现的
        1.事务的特性：ACID
        2.物理锁与日志:数据库通过行锁、表锁保证隔离性，通过 undo log（回滚日志）和 redo log（重做日志）实现事务回滚和持久化，这些都是数据库内核的功能。
        3.隔离级别生效；

47.如果有一个实现类接口，但是我强制使用CGLIB动态代理，有什么方法？
在 Spring AOP 中，默认策略是：
        目标类实现接口 → 优先使用 JDK 动态代理（基于接口）。
        目标类未实现接口 → 使用 CGLIB 代理（基于继承）。
        但可以通过配置强制使用 CGLIB 代理（即使目标类实现了接口）：
      强制使用CGLIB动态代理：
        使用XML配置：
        <!-- XML 配置：强制使用 CGLIB -->
        <aop:aspectj-autoproxy proxy-target-class="true"/>
        或者使用注解：
            @Configuration
            @EnableAspectJAutoProxy(proxyTargetClass = true) // 强制使用 CGLIB
            public class AopConfig {
            // ...
            }

# SpringCloud面试题
SpringCloud面试题
1.SpringCloud五大组件

Feign：http客户端，用于优雅调用(简化)Http接口，实现服务与服务之间远程调用。
￼

Ribbon：客户端负载均衡，减少单一服务提供者访问压力，提高可用性。

Nacos：服务注册中心，用于动态服务发现。同时也可用作配置中心

Gateway：网关，整个系统的出入口,用于统一管理各个微服务，比如鉴权，日志记录，跨域处理等。

Sentinel：主打一个保护！！分布式系统的流量防卫兵，通过流量控制，熔断降级等手段保护系统的。
订单访问库存接口的时候可以根据响应配置服务降级。


		2.什么是SpringCloud?
			一个基于Spring集成的应用程序，解决了SpringBoot在微服务场景下的不足：
			后面跟五大组件的功能。

		3.五大组件的协作流程
            1. 服务启动：各微服务向注册中心（Eureka/Nacos）注册自己。
            2. 配置加载：微服务从配置中心（Config Server/Nacos Config）获取配置。
            3. 请求路由：客户端请求通过 API 网关（Gateway/Zuul）转发到目标服务。
            4. 负载均衡：服务消费者通过负载均衡器（Ribbon/LoadBalancer）选择可用实例。
            5. 容错保护：服务调用过程中，断路器（Hystrix/Resilience4j）监控调用状态，防止级联故障。
		
		4.什么是Hystrix?它如何容错？
		https://www.doubao.com/thread/w21524927882c0e12
		Hystrix是一个容错的构件，假设一个业务需要调用多个链路，如果其中一个失败，那会导致流程中的其他节点没办法正常执行。
			此时Hystrix支持三个功能：
			1.服务熔断：当某个服务的错误率超过阈值(默认50%)，Hystrix会自动熔断该服务，快速返回降级结果。
			2.服务降级：当服务调用失败、超时或熔断时，执行预期的降级逻辑，保证系统核心可用。
	   			   示例： 电商系统中，商品详情页的评论服务不可用时，降级显示为”评论加载中“。
			3.线程隔离：
					实现：通过线程池隔离不同服务的调用，避免某个服务断掉之后耗尽其他资源。
					场景：服务A响应缓慢时，仅占用自身线程的资源，不影响其他服务。
			4.超时控制：为服务设置超时时间(默认一秒)，超过时间自动中断调用，防止长时间等待。
			5.熔断恢复：熔断后，Hystrix会间歇性的尝试请求服务，若成功率达标则自动关闭熔断。
			
		5.什么是Feign?它的优缺点是什么？
			Feign是一个HTTP请求客户端，用于简化服务端之间的通信。
			
		6.		




SpringCloud的所有组件(了解)：
核心组件

1. 服务发现与注册
    * Eureka：Netflix 开发的服务注册与发现组件，提供服务注册中心、服务提供者和服务消费者的功能。（已进入维护模式）
    * Consul：HashiCorp 开发的服务网格解决方案，支持服务发现、健康检查、KV 存储等。
    * Nacos：阿里巴巴开源的服务发现、配置管理和服务治理平台，支持多环境、多集群部署。
2. 配置管理
    * Config Server：Spring Cloud 官方配置中心，支持集中化管理应用配置，支持 Git、SVN 等版本控制。
    * Nacos Config：Nacos 提供的配置管理功能，支持动态配置刷新。
    * Apollo：携程开源的分布式配置中心，支持灰度发布、版本管理等高级特性。
3. 负载均衡
    * Ribbon：Netflix 的客户端负载均衡器，配合服务发现组件实现智能负载均衡。（已进入维护模式）
    * Spring Cloud LoadBalancer：Spring Cloud 官方提供的轻量级负载均衡器，替代 Ribbon。
    * Nginx：可作为服务网关的负载均衡器，与 Spring Cloud 结合使用。
4. 服务网关
    * Zuul：Netflix 的 API 网关，提供路由、过滤、限流等功能。（已进入维护模式）
    * Gateway：Spring Cloud 官方网关，基于 WebFlux 构建，支持异步非阻塞，性能更高。
    * Kong：开源 API 网关，可与 Spring Cloud 集成。
5. 熔断器 / 断路器
    * Hystrix：Netflix 的熔断器，防止级联故障，提供熔断、降级、限流等功能。（已进入维护模式）
    * Resilience4j：轻量级容错框架，替代 Hystrix，提供熔断、限流、重试等功能。
    * Sentinel：阿里巴巴开源的流量控制、熔断降级组件，支持实时监控和规则配置。
6. 服务调用
    * OpenFeign：声明式 REST 客户端，简化服务间调用，支持负载均衡和熔断器。
    * RestTemplate：Spring 提供的 HTTP 客户端，简单易用，配合 Ribbon 实现负载均衡。
7. 分布式链路追踪
    * Sleuth：Spring Cloud 官方分布式链路追踪工具，收集请求链路信息。
    * Zipkin：分布式追踪系统，可视化展示请求链路和性能指标。
    * Skywalking：开源 APM 系统，支持服务间调用追踪、性能监控等。
8. 消息总线
    * Bus：Spring Cloud Bus 使用消息代理（如 RabbitMQ、Kafka）连接分布式系统的节点，实现配置更新的广播。
9. API 文档
    * Springdoc OpenAPI：自动生成 REST API 文档，支持 OpenAPI 3.0 规范。
    * Swagger：生成和展示 API 文档的工具，与 Spring Cloud 集成。
10. 安全
    * Spring Cloud Security：提供安全认证和授权支持，集成 OAuth2、JWT 等。
      其他组件
* 分布式事务：
    * Seata：阿里巴巴开源的分布式事务解决方案。
* 任务调度：
    * Scheduled Tasks：Spring 内置的定时任务框架。
    * Elastic-Job：分布式任务调度框架，支持分片和高可用。
* API 网关增强：
    * Spring Cloud Gateway Plus：扩展 Gateway 功能，提供限流、认证等增强特性。
* 服务网格：
    * Spring Cloud Alibaba：整合 Nacos、Sentinel 等组件，支持服务网格。
    * Istio：开源服务网格，可与 Spring Cloud 集成。
* 监控与告警：
    * Prometheus：开源监控系统，收集和存储指标数据。
    * Grafana：可视化监控数据，支持多种数据源。
      注意事项
* 版本兼容性：Spring Cloud 组件之间需要注意版本兼容性，推荐使用官方提供的版本管理工具（如 Spring Cloud Release Train）。
* 组件替代：部分 Netflix 组件（如 Eureka、Hystrix）已进入维护模式，官方推荐使用替代方案（如 Nacos、Resilience4j）。
* 生态扩展：Spring Cloud 生态不断扩展，新组件和集成方案会持续增加。
							
			
			
					

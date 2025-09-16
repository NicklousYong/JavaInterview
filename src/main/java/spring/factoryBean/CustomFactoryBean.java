package spring.factoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


// 实现 FactoryBean 接口，泛型指定要创建的 Bean 类型
@Component("complexObject1")
public class CustomFactoryBean implements FactoryBean<ComplexObject> {

    // 自定义 Bean 的创建逻辑（核心方法）
    @Override
    public ComplexObject getObject() throws Exception {
        // 模拟复杂对象的创建过程（如初始化配置、设置依赖等）
        ComplexObject obj = new ComplexObject();
        obj.init(); // 自定义初始化逻辑
        Dog dog = new Dog();
        return dog;

    }

    // 返回创建的 Bean 类型
    @Override
    public Class<?> getObjectType() {
        return ComplexObject.class;
    }

    // 是否单例（默认 true）
    @Override
    public boolean isSingleton() {
        return true;
    }
}

// 目标 Bean 类
//class ComplexObject {
//    public void init() {
//        // 复杂初始化逻辑
//        System.out.println("ComplexObject 初始化完成");
//    }
//}

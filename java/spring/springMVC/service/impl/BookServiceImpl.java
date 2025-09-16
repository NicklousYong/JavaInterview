package spring.springMVC.service.impl;


import org.springframework.stereotype.Service;
import spring.springMVC.model.Book;
import spring.springMVC.service.BookService;

import java.util.HashMap;
import java.util.Map;

// @Service注解标识这是一个服务组件
@Service
public class BookServiceImpl implements BookService {

    // 模拟数据库存储书籍信息
    private static final Map<Integer, Book> books = new HashMap<>();

    // 静态初始化一些书籍数据
    static {
        books.put(1, new Book(1, "Spring Boot实战", "张三", "Spring Boot入门教程", 59.9));
        books.put(2, new Book(2, "Java编程思想", "李四", "Java经典教材", 108.0));
        books.put(3, new Book(3, "设计模式", "王五", "软件设计模式详解", 79.0));
    }

    @Override
    public Book getBookById(Integer id) {
        // 模拟从数据库查询书籍
        return books.get(id);
    }
}

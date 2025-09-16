package spring.springMVC.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.springMVC.model.Book;
import spring.springMVC.service.BookService;

// @Controller注解标识这是一个控制器组件
@Controller
public class BookController {

    // 自动注入BookService
    @Autowired
    private BookService bookService;

    // @GetMapping注解映射GET请求，处理路径为"/book"的请求
    @GetMapping("/book")
    public String getBookInfo(@RequestParam("id") Integer bookId, Model model) {
        Logger logger = LoggerFactory.getLogger(BookController.class);
        // 调用服务层方法获取书籍信息
        Book book = bookService.getBookById(bookId);
        logger.info("已经调取到getBookInfo方法");
        // 将书籍信息存入Model，供视图使用
        model.addAttribute("book", book);

        // 返回视图名称，Thymeleaf会自动查找对应的模板
        return "bookInfo";
    }
}

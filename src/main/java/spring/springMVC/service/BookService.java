package spring.springMVC.service;

import spring.springMVC.model.Book;

public interface BookService {
    // 根据ID获取书籍信息
    Book getBookById(Integer id);
}

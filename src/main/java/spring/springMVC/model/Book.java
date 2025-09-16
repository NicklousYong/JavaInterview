package spring.springMVC.model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Double price;

    // 构造方法
    public Book(Integer id, String title, String author, String description, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    // getter和setter方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}

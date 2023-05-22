package com.example.milestone.book;

public class PostBookRequest {
    // 本のタイトル
    private String title;

    // 本の著者
    private String author;
    
    // 本のページ数
    private int numOfPages;
    
    // 本の分類番号
    private int classification;    

    public PostBookRequest() {
        this.title = "";
        this.author = "";
        this.numOfPages = 0;
        this.classification = 0;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getNumOfPages() {
        return this.numOfPages;
    }
    
    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }
    
    public int getClassification() {
        return this.classification;
    }
    
    public void setClassification(int classification) {
        this.classification = classification;
    } 
}

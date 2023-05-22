package com.example.milestone;

import java.util.UUID;

public class Book {
    // 本のID（UUID）
    private String id;

    // 本のタイトル
    private String title;
  
    // 本の著者
    private String author;
  
    // 本のページ数
    private int numOfPages;
  
    // 本の分類
    private BookClassification classification;
  
    // コンストラクタ
    public Book(String title, String author, int numOfPages, BookClassification classification) {
      this.id = UUID.randomUUID().toString();
      this.title = title;
      this.author = author;
      this.numOfPages = numOfPages;
      this.classification = classification;
    }
  
    // コンストラクタ
    public Book(String title, String author, int numOfPages, int classificationNumber) {
      this(title, author, numOfPages, BookClassification.fromNumber(classificationNumber));
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  
    public String getTitle() {
      return title;
    }
  
    public void setTitle(String title) {
      this.title = title;
    }
  
    public String getAuthor() {
      return author;
    }
  
    public void setAuthor(String author) {
      this.author = author;
    }
  
    public int getNumOfPages() {
      return numOfPages;
    }
  
    public void setNumOfPages(int numOfPages) {
      this.numOfPages = numOfPages;
    }
  
    public BookClassification getClassification() {
      return classification;
    }
  
    public void setClassification(BookClassification classification) {
      this.classification = classification;
    }
  
    @Override
    public String toString() {
      return id + ": " + author + "『" + title + "』(" + numOfPages + "ページ, " + classification + ")";
    }
  }
  
package com.example.milestone.book;

public enum BookClassification {
    GENERAL_WORKS("総記"),
    PHILOSOPHY("哲学"),
    HISTORY("歴史"),
    SOCIAL_SCIENCES("社会科学"),
    SCIENCE("自然科学"),
    TECHNOLOGY("技術"),
    INDUSTRY("産業"),
    ARTS("芸術"),
    LANGUAGE("言語"),
    LITERATURE("文学"),
    ;
  
    private final String jpName;
  
    private BookClassification(String jpName) {
      this.jpName = jpName;
    }
  
    public static BookClassification fromNumber(int number) {
      for (BookClassification classification : BookClassification.values()) {
        if (classification.ordinal() == number) {
          return classification;
        }
      }
      return null;
    }
  
    @Override
    public String toString() {
      return this.jpName;
    }
    
    public String getJpName() {
        return this.jpName;
    }
  }

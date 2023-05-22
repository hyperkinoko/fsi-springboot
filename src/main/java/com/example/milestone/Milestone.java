package com.example.milestone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Milestone {
  // UUID
  private String id;

  // タイトル
  private String title;

  // 詳細
  private String description;

  // 期日
  private LocalDate deadline;

  // デフォルトデータのコンストラクタ
  public Milestone() {
    this.id = UUID.randomUUID().toString();
    this.title = "タイトル";
    this.description = "詳細情報";
    this.deadline = LocalDate.now();
  }

  // (UUID以外の)フルデータを設定できるコンストラクタ
  public Milestone(String title, String description, LocalDate deadline) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
    this.description = description;
    this.deadline = deadline;
  }

  // UUIDを含むフルデータを設定できるコンストラクタ
  public Milestone(String id, String title, String description, LocalDate deadline) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.deadline = deadline;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public String toString() {
    return String.format("%s[%sまで]", this.title, this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
  }
}

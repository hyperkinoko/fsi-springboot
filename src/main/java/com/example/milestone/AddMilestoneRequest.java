package com.example.milestone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AddMilestoneRequest {
  // タイトル
  private String title;

  // 詳細
  private String description;

  // 期日
  private String deadline;

  // デフォルトデータのコンストラクタ
  public AddMilestoneRequest() {
    this.title = "タイトル";
    this.description = "詳細情報";
    this.deadline = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  // (UUID以外の)フルデータを設定できるコンストラクタ
  public AddMilestoneRequest(String title, String description, String deadline) {
    this.title = title;
    this.description = description;
    this.deadline = deadline;
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

  public String getDeadline() {
    return deadline;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }

  public String toString() {
    return String.format("==REQUEST==\ntitle: %s\ndetail:%s\ndeadline:%s====", this.title, this.description, this.deadline);
  }
}

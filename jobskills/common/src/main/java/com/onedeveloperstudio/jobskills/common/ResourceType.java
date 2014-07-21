package com.onedeveloperstudio.jobskills.common;

/**
 * User: User
 * Date: 20.07.14
 */
public enum ResourceType {
  BOOK("Книга"),
  LESSON("Уроки"),
  TUTORIAL("Туториал"),
  DOCUMENTATION("Документация"),
  TESTS("Тесты");

  private String caption;

  private ResourceType(String caption) {
    this.caption = caption;
  }

  @Override
  public String toString() {
    return caption;
  }
}

package com.onedeveloperstudio.jobskills.common;

/**
 * User: User
 * Date: 20.07.14
 */
public enum GRADE {
  GRADE_1("Высокий уровень"),
  GRADE_2("Средний уровень"),
  GRADE_3("Начальный уровень");

  private String caption;

  private GRADE(String caption) {
    this.caption = caption;
  }

  @Override
  public String toString() {
    return caption;
  }
}

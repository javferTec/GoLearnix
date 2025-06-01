package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

  private Integer id;
  private String title;
  private int order;
  private String content;
  private List<Progress> progress;

  public void addProgresses(List<Progress> progress) {
    this.progress.addAll(progress);
  }

}

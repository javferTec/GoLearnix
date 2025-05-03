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
  private String videoUrl;
  private Integer duration;
  private int order;
  private List<Progress> progress;

  public void addProgresses(List<Progress> progress) {
    this.progress.addAll(progress);
  }

}

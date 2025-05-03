package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

  private Integer id;
  private String title;
  private int order;
  private List<Lesson> lessons;

}

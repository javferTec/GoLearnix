package com.golearnix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

  private UUID id;
  private String title;
  private String videoUrl;
  private Integer duration;
  private int order;
  private List<Progress> progress;

}

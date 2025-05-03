package com.golearnix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

  private UUID id;
  private String title;
  private int order;
  private List<Lesson> lessons;

}

package com.golearnix.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCommandDto {

  private String title;
  private String description;
  private UUID instructorId;
  private UUID categoryId;

}

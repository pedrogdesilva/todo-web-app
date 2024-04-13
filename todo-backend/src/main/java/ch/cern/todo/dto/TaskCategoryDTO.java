package ch.cern.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskCategoryDTO {

    private String name;

    private String description;
}

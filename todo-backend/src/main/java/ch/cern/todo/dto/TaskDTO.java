package ch.cern.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime deadline;

    private String category;
}

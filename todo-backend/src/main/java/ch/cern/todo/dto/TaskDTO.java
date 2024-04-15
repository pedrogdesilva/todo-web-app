package ch.cern.todo.dto;

import ch.cern.todo.validator.annotation.TaskCategoryExistsConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    @NotNull @NotBlank
    private String name;

    private String description;

    @NotNull
    private LocalDateTime deadline;

    @NotNull @TaskCategoryExistsConstraint
    private String category;
}

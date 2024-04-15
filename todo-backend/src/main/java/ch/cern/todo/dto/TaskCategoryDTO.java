package ch.cern.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskCategoryDTO {

    private Long id;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String description;
}

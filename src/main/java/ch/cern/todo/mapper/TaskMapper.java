package ch.cern.todo.mapper;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.persistence.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity getTask(TaskDTO dto) {

        TaskEntity entity = new TaskEntity();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDeadline(dto.getDeadline());

        return entity;
    }


    public static TaskDTO getTask(TaskEntity entity) {

        return new TaskDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDeadline(),
                entity.getCategory() == null ? null : entity.getCategory().getName()
        );
    }
}

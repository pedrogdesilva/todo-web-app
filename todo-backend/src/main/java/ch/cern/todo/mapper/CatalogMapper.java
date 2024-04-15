package ch.cern.todo.mapper;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.persistence.entity.TaskCategoryEntity;

import java.util.List;

public class CatalogMapper {

    public static TaskCategoryEntity getCategory(TaskCategoryDTO dto) {

        TaskCategoryEntity entity = new TaskCategoryEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }


    public static TaskCategoryDTO getCategory(TaskCategoryEntity entity) {

        return new TaskCategoryDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    public static List<TaskCategoryDTO> getCategories(List<TaskCategoryEntity> entities) {
        return entities.stream().map(CatalogMapper::getCategory).toList();
    }
}

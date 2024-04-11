package ch.cern.todo.persistence.repository;

import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCategoryRepository extends CrudRepository<TaskCategoryEntity, Long> {

    Optional<TaskCategoryEntity> findByName(String name);
}

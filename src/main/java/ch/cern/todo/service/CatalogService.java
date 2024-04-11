package ch.cern.todo.service;

import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import ch.cern.todo.persistence.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CatalogService {

    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    public CatalogService (TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    public List<TaskCategoryEntity> getTaskCategories() {
        List<TaskCategoryEntity> categories = new ArrayList<>();
        taskCategoryRepository.findAll().forEach(categories::add);
        return categories;
    }


}

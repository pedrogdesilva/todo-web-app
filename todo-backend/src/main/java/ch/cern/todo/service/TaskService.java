package ch.cern.todo.service;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import ch.cern.todo.persistence.entity.TaskEntity;
import ch.cern.todo.persistence.repository.TaskCategoryRepository;
import ch.cern.todo.persistence.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskCategoryRepository taskCategoryRepository) {
        this.taskRepository = taskRepository;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public TaskEntity createTask(TaskDTO taskDTO) {

        TaskEntity task = TaskMapper.getTask(taskDTO);

        // Task must exist (validation at controller level)
        Optional<TaskCategoryEntity> findRes = taskCategoryRepository.findByName(taskDTO.getCategory());
        task.setCategory(findRes.get());

        return taskRepository.save(task);
    }

    public Optional<TaskEntity> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Transactional
    public TaskEntity updateTask(TaskDTO taskDTO) {
        Optional<TaskEntity> taskToUpdateOpt = taskRepository.findById(taskDTO.getId());

        TaskEntity taskToUpdate = taskToUpdateOpt.get();

        taskToUpdate.setName(taskDTO.getName());
        taskToUpdate.setDescription(taskDTO.getDescription());
        taskToUpdate.setDeadline(taskDTO.getDeadline());

        // Task must exist (validation at controller level)
        Optional<TaskCategoryEntity> taskCategoryOpt = taskCategoryRepository.findByName(taskDTO.getCategory());
        taskToUpdate.setCategory(taskCategoryOpt.get());

        return taskRepository.save(taskToUpdate);
    }

    public List<TaskEntity> getAllTasks() {
        List<TaskEntity> allTasks = new ArrayList<>();
        Iterable<TaskEntity> iterator = taskRepository.findAll();
        iterator.iterator().forEachRemaining(allTasks::add);
        return allTasks;
    }
}

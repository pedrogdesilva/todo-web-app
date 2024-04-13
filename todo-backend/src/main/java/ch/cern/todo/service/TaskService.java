package ch.cern.todo.service;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import ch.cern.todo.persistence.entity.TaskEntity;
import ch.cern.todo.persistence.repository.TaskCategoryRepository;
import ch.cern.todo.persistence.repository.TaskRepository;
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

    public TaskEntity createTask(TaskDTO taskDTO) {

        TaskEntity task = TaskMapper.getTask(taskDTO);

        Optional<TaskCategoryEntity> findRes = taskCategoryRepository.findByName(taskDTO.getCategory());
        if (findRes.isEmpty()) {
            //TODO: error input should be validated at controller level
        } else {
            task.setCategory(findRes.get());
        }

        return taskRepository.save(task);
    }

    public Optional<TaskEntity> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public TaskEntity updateTask(TaskDTO taskDTO) {
        Optional<TaskEntity> taskToUpdateOpt = taskRepository.findById(taskDTO.getId());

        if (taskToUpdateOpt.isEmpty()) {
            // TODO : invalid state
        }

        TaskEntity taskToUpdate = taskToUpdateOpt.get();

        taskToUpdate.setName(taskDTO.getName());
        taskToUpdate.setDescription(taskDTO.getDescription());
        taskToUpdate.setDeadline(taskDTO.getDeadline());

        Optional<TaskCategoryEntity> taskCategoryOpt = taskCategoryRepository.findByName(taskDTO.getCategory());
        if (taskCategoryOpt.isEmpty()) {
            //TODO: error input should be validated at controller level
        } else {
            taskToUpdate.setCategory(taskCategoryOpt.get());
        }

        return taskRepository.save(taskToUpdate);
    }

    public List<TaskEntity> getAllTasks() {
        List<TaskEntity> allTasks = new ArrayList<>();
        Iterable<TaskEntity> iterator = taskRepository.findAll();
        iterator.iterator().forEachRemaining(allTasks::add);
        return allTasks;
    }
}

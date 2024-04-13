package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.persistence.entity.TaskEntity;
import ch.cern.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequestMapping("/rest/task")
@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    private TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<TaskDTO> getTask(@RequestParam(name = "id") Long id) {
        Optional<TaskEntity> newTask = taskService.getTask(id);

        if(newTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(TaskMapper.getTask(newTask.get()));
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskEntity> tasks = taskService.getAllTasks();
        List<TaskDTO> tasksDTO = tasks.stream().map((task) -> TaskMapper.getTask(task)).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok().body(tasksDTO);
    }

    @CrossOrigin
    @PutMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task) {
        TaskEntity newTask = taskService.createTask(task);
        return ResponseEntity.ok().body(TaskMapper.getTask(newTask));
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task) {
        Optional<TaskEntity> newTask = taskService.getTask(task.getId());

        if(newTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity updatedTask = taskService.updateTask(task);

        return ResponseEntity.ok().body(TaskMapper.getTask(updatedTask));
    }
}

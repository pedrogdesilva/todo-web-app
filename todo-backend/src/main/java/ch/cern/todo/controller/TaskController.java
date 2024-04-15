package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.persistence.entity.TaskEntity;
import ch.cern.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO task) {
        TaskEntity newTask = taskService.createTask(task);
        return ResponseEntity.ok().body(TaskMapper.getTask(newTask));
    }

    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskDTO task) {

        Optional<TaskEntity> newTask = taskService.getTask(task.getId());

        if(newTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity updatedTask = taskService.updateTask(task);

        return ResponseEntity.ok().body(TaskMapper.getTask(updatedTask));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

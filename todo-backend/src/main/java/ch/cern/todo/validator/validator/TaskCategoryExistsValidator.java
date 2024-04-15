package ch.cern.todo.validator.validator;

import ch.cern.todo.persistence.entity.TaskCategoryEntity;
import ch.cern.todo.persistence.repository.TaskCategoryRepository;
import ch.cern.todo.validator.annotation.TaskCategoryExistsConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskCategoryExistsValidator implements
        ConstraintValidator<TaskCategoryExistsConstraint, String> {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;


    @Override
    public void initialize(TaskCategoryExistsConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String taskId, ConstraintValidatorContext constraintValidatorContext) {
        Optional<TaskCategoryEntity> findRes = taskCategoryRepository.findByName(taskId);
        return findRes.isPresent();
    }

}
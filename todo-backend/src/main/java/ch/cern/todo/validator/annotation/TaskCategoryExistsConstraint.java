package ch.cern.todo.validator.annotation;

import ch.cern.todo.validator.validator.TaskCategoryExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TaskCategoryExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskCategoryExistsConstraint {
    String message() default "Invalid task category.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
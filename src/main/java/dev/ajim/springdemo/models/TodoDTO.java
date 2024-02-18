package dev.ajim.springdemo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoDTO {
    private int id;

    @NotNull(message = "A task has to be provided")
    @NotEmpty(message = "Task cannot be empty")
    private String task;

    @NotNull(message = "Done must not be null")
    private Boolean done;

    public TodoEntity toTodoEntity(){
        return new TodoEntity()
                .builder()
                .id(id)
                .done(done)
                .task(task)
                .build();
    }
}

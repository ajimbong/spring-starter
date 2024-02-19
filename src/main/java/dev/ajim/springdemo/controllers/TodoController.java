package dev.ajim.springdemo.controllers;

import dev.ajim.springdemo.models.TodoDTO;
import dev.ajim.springdemo.models.TodoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    List<TodoEntity> todos;

    public TodoController(ArrayList<TodoEntity> todos){
        todos.add(new TodoEntity(1, "Read some books", false));
        todos.add(new TodoEntity(2, "Write some codes", true));
        todos.add(new TodoEntity(3, "Watch some tuts", true));
        this.todos = todos;
    }

    @Operation(summary = "Get a list of all the todo entities")
    @GetMapping()
    public List<TodoEntity> getTodos(@RequestParam(required = false) String param) {
        if(param != null){
            System.out.println("Found a param: " + param);
        }
        return todos;
    }

    @Operation(summary = "Get a particular todo by ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TodoEntity getTodoByID(@PathVariable String id){
          try{
              int todoId = Integer.parseInt(id);
              TodoEntity todo = null;

              for (TodoEntity item: todos){
                  if (item.getId() == todoId){
                      todo = item;
                      break;
                  }
              }

              if (todo != null)
                  return todo;
              else
                  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");

          } catch (NumberFormatException e) {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ID is not of right format");
          }

    }

    @Operation(summary = "Create a new Todo")
    @PostMapping()
    public ResponseEntity<TodoEntity> createTodo(@Valid @RequestBody TodoDTO todo){
            todo.setId(todos.size() + 1);
            TodoEntity newTodo = todo.toTodoEntity();
            todos.add(newTodo);

            return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing Todo by ID")
    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> updateTdo(@Valid @RequestBody TodoDTO todo, @PathVariable String id) {

            int todoId = Integer.parseInt(id);
            todo.setId(todoId);
            int found = 0;

            for (TodoEntity item: todos){
                if (item.getId() == todoId){
                    found = 1;
                    item.setDone(todo.getDone());
                    item.setTask(todo.getTask());
                    break;
                }
            }

            if (found != 0)
                return new ResponseEntity<>(todo.toTodoEntity(), HttpStatus.OK);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");

    }
}

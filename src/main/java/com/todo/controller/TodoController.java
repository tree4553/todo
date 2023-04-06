package com.todo.controller;

import com.todo.dto.TodoDto;
import com.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoDto.PostDto postDto) {
        TodoDto.ResponseDto responseDto = service.postTodo(postDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") long todoId) {
        TodoDto.ResponseDto responseDto = service.findTodo(todoId);
        if(responseDto != null) {
            return ResponseEntity.ok(responseDto);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAllTodo() {
        TodoDto.ResponseListDto responseListDto = service.findAllTodo();
        return ResponseEntity.ok(responseListDto);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") long todoId,
                                    @RequestBody TodoDto.PatchDto patchDto) {
        TodoDto.ResponseDto responseDto = service.updateTodo(todoId, patchDto);
        if(responseDto != null) {
            return ResponseEntity.ok(responseDto);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId) {
        service.deleteTodo(todoId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTodo() {
        service.deleteAllTodo();
        return new ResponseEntity(HttpStatus.OK);
    }
}

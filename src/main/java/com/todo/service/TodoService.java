package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.mapper.TodoMapper;
import com.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoMapper mapper;
    private final TodoRepository repository;

    public TodoService(TodoMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public TodoDto.ResponseDto postTodo(TodoDto.PostDto postDto) {
        Todo todo = mapper.postDtoToTodo(postDto);
        repository.save(todo);
        TodoDto.ResponseDto responseDto = mapper.todoToResponseDto(todo);
        return responseDto;
    }

    public TodoDto.ResponseDto findTodo(long id) {
        Optional<Todo> todo = repository.findById(id);
        if(todo.isPresent()) {
            TodoDto.ResponseDto responseDto = mapper.todoToResponseDto(todo.get());
            return responseDto;
        }
        else {
            return null;
        }
    }

    public TodoDto.ResponseListDto findAllTodo() {
        List<Todo> todoList = new ArrayList<>();
        todoList = repository.findAll();
        TodoDto.ResponseListDto responseListDto = mapper.todoListToResponsesDto(todoList);
        return responseListDto;
    }

    public TodoDto.ResponseDto updateTodo(long id, TodoDto.PatchDto patchDto) {
        Optional<Todo> todo = repository.findById(id);
        if(todo.isPresent()) {
            Todo updateTodo = todo.get();
            updateTodo.setTitle(patchDto.getTitle());
            updateTodo.setTodo_order(patchDto.getTodo_order());
            updateTodo.setCompleted(patchDto.isCompleted());
            Todo saveResultTodo = repository.save(updateTodo);
            TodoDto.ResponseDto responseDto = mapper.todoToResponseDto(saveResultTodo);
            return responseDto;
        }
        else {
            return null;
        }
    }

    public void deleteTodo(long id) {
        repository.deleteById(id);
    }

    public void deleteAllTodo() {
        repository.deleteAll();
    }
}

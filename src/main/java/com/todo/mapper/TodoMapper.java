package com.todo.mapper;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {
    public Todo postDtoToTodo(TodoDto.PostDto postDto) {
        Todo todo = new Todo();
        todo.setTitle(postDto.getTitle());
        todo.setTodo_order(postDto.getTodo_order());
        todo.setCompleted(postDto.isCompleted());

        return todo;
    }

    public Todo patchDtoToTodo(long id, TodoDto.PatchDto patchDto) {
        Todo todo = new Todo(
                id,
                patchDto.getTitle(),
                patchDto.getTodo_order(),
                patchDto.isCompleted()
        );

        return todo;
    }

    public TodoDto.ResponseDto todoToResponseDto(Todo todo) {
        TodoDto.ResponseDto responseDto = new TodoDto.ResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getTodo_order(),
                todo.isCompleted()
        );

        return responseDto;
    }

    public TodoDto.ResponseListDto todoListToResponsesDto(List<Todo> todoList) {
        List<TodoDto.ResponseDto> responseDtoList = new ArrayList<>();

        for (int i = 0; i < todoList.size(); i++) {
            Todo todo = todoList.get(i);
            TodoDto.ResponseDto responseDto = new TodoDto.ResponseDto(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getTodo_order(),
                    todo.isCompleted()
            );
            responseDtoList.add(responseDto);
        }

        TodoDto.ResponseListDto responsesDto = new TodoDto.ResponseListDto(responseDtoList);
        return responsesDto;
    }
}

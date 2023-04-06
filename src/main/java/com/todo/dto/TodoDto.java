package com.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class PostDto {
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class PatchDto {
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseDto {
        private Long id;
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseListDto {
        List<ResponseDto> responsesDto;
    }
}

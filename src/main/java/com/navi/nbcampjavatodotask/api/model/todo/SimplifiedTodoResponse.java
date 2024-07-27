package com.navi.nbcampjavatodotask.api.model.todo;

import java.time.LocalDateTime;

public record SimplifiedTodoResponse(
        Long id,
        String title,
        LocalDateTime createdAt
) {
}

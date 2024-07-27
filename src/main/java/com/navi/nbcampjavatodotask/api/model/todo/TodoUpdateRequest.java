package com.navi.nbcampjavatodotask.api.model.todo;

public record TodoUpdateRequest(
        String title,
        String content,
        String assignee,
        String password
) {
}

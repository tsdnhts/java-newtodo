package com.navi.nbcampjavatodotask.api.model.todo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TodoUpdateRequest(
        @NotBlank
        String title,
        @NotBlank
        String content,
        @NotBlank
        @Email
        String assignee,
        @NotBlank
        String password
) {
}

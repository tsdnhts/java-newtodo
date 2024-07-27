package com.navi.nbcampjavatodotask.api.model.todo;

import jakarta.validation.constraints.NotBlank;

public record TodoDeleteRequest(
        @NotBlank
        String password
) {
}

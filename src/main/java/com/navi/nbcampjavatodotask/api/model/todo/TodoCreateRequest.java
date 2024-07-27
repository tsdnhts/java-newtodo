package com.navi.nbcampjavatodotask.api.model.todo;

import java.time.LocalDateTime;

public record TodoCreateRequest(
        // Record란? 코틀린의 data class 같은 역할을 하는 것이 java 14 이후 나온 record!
        // 코틀린의 data class와 달리 java의 record는 상속이 되지 않는다는 점에 유의하자
        String title,
        String content,
        String assignee,
        String password
        // LocalDateTime createdAt의 경우 받을 필요 X
        // 조작해서 넣을 수 있는 기능이기 때문에 컨트롤러에서 직접 찍어서 만들어 줄 예정
) {
}

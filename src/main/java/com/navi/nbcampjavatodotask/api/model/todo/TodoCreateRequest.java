package com.navi.nbcampjavatodotask.api.model.todo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public record TodoCreateRequest(
        // Record란? 코틀린의 data class 같은 역할을 하는 것이 java 14 이후 나온 record!
        // 코틀린의 data class와 달리 java의 record는 상속이 되지 않는다는 점에 유의하자
        @NotBlank
        String title,
        @NotBlank
        String content,
        @NotBlank
        @Email
        String assignee,
        // @NotBlank와 @NotNull의 차이는 NotNull은 널인지만 검사
        // NotBlank는 Null인지 or 빈 String인지 같은것들 같이 검사
        // @Email은 이메일인지 검증을 위해 추가함
        // not blank를 여기에만 붙이면 끝나는 것이 아닌, createdRequest가 사용된 CreateTodo에도 @Valid를 붙여줘야 함
        // class 단위에 붙이는 validated라는 어노테이션도 있지만 Valid = java / Validated = Spring에 있는 것
        // 둘은 검증이 실패했을때 반환하는 exception도 다르며, 사용하는 용도도 다르다
        // Valid의 경우 메소드 파라미터에서 dto를 검증할때 사용한다.
        // Validated의 경우 dto 컨트롤러 레이어가 아니라, 서비스 레이어에서 벨리데이션을 사용하는 경우
        // 다른 레이어에서 벨리데이션 적용을 하는 경우에 벨리데이트 어노테이션을 사용하게 된다
        // valid는 다른 레이어에서는 동작하지 않거나, 목적에 맞지 않는걸로 기억한다고 하심
        @NotBlank
        String password
        // LocalDateTime createdAt의 경우 받을 필요 X
        // 조작해서 넣을 수 있는 기능이기 때문에 컨트롤러에서 직접 찍어서 만들어 줄 예정
) {
}

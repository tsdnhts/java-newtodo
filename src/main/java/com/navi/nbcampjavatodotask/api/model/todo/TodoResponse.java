package com.navi.nbcampjavatodotask.api.model.todo;

import com.navi.nbcampjavatodotask.database.entity.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TodoResponse(
        Long id,
        String title,
        String content,
        String assignee,
        LocalDateTime createdAt
//        String password request가 아닌, 응답을 주는 response이며 패스워드는 응답해 주지 않으므로 제거한다
        // 그 이외 createdAt과, id를 추가해 준다
) {
    public static TodoResponse of(Todo entity){
        // 그 이외 질문, 팩토리 메서드를 만들어야 하나요? A : 의존성 방향이 문제가 안된다면 만드는 것을 선호한다
        // of라는 메소드를 만들어서, entity를 받아 DTO로 변환을 하는 static 메소드(팩토리 메소드)를 한개 생성함
        // todo -> todoResponse로 바꾸는 경우는 많이 사용이 될 것 같아 한번 정의
        // 반면에, createRequset에서 TodoEntity를 만드는 경우는 따로 정의를 하지 않는다.
        // 왜냐하면, CreateRequest라는 한개에서만 사용되고, 추후 UpdateRequest가 생기면 거기서 다시 정리를 해줘야 하기 때문에
        // 그 반대가 되는 의존성은 정의를 하지 않음
        return new TodoResponse(
                //TodoResponse를 생성하는 생성자를 만들어 준다
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAssignee(),
                entity.getCreatedAt()
        );
        // 이렇게 팩토리 메소드를 하나 만들어 주었다.
    }
}

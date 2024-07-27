package com.navi.nbcampjavatodotask.api.service;

import com.navi.nbcampjavatodotask.database.entity.Todo;
import com.navi.nbcampjavatodotask.database.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
// 이것도 왜 사용했는지 강의에서 나왔는데 바로 안떠오름, 추가로 확인할 것
public class TodoService {
    private final TodoRepository todoRepository;
    //의존성으로는 레포지토리를 받아야 하므로 생성자 주입 방식으로 추가
    public Todo createTodo(
            // createTodo 메소드 생성
            String title,
            String content,
            String assignee,
            String password,
            LocalDateTime createdAt
            // 메소드에서 필요한 값들을 파라미터로 받아줌
            // createdAt 같은 경우 서비스에서 만들어도 된다, 예를 들자면
            // 아래에서 now를 호출해서 생성해도 좋지만
            // LocalDatetime.now() 아래에서 now를 호출해서 만드는 경우의 예
            // 외부에서 오는 값은 가장 최 상단에서부터 받으려고 해서 컨트롤러에서 createdAt을 만들어서 주입되도록 함
            // 최상단에서 주입받는 이유는 추후 테스트 짜는 경우도 고려했기 때문임
            // 해당 내용을 서비스 내부에서 만들게 되면, 해당 메소드를 테스트 할 때, createdAt 값이
            // 매번 바뀌는 값인 now로 들어가기 때문에, 해당 값을 외부에서 받는다면 해당 필드에 무슨 값이 들어갈지
            // 테스트에서도 지정을 해줄수가 있다.
            // 따라서 외부에서 받아오고, 최상단에서 만들어서 하나씩 넣어주는 방식으로 흘러간다고 볼 것
    ){
        Todo todo = Todo.builder()
                .title(title)
                .content(content)
                .assignee(assignee)
                .password(password)
                .createdAt(createdAt)
                .build();
                // 빌더와 관련한 이야기가 있었기 때문에, 빌더를 사용해서 제작할 계획
        // 빌더 패턴을 사용하는 기준
        // 현재 예시는 String type이 4개나 있기 때문에, 값이 바뀌어서 들어가도 쉽게 알 수 없다(대부분 String이라서)
        // 이처럼 type이 같은 필드가 많을 때, 자주 사용을 한다.
        // 코틀린에서는 builder 패턴을 자주 사용하지 않는데, named 파라미터가 가능하기 때문
        // Todo(title = title, content = content)같은 형식으로 넣어주는 것이 가능했으나 java는 문법적으로 미지원
        // 따라서 잘못된 타입으로 들어갈 수 있는 가능성이 커지기 때문에
        // 그 외 많이 물어본 질문
        // 1. dto를 사용할 때 인풋값과 아웃풋 값에 대한 질문
        // controller에서 사용하는 dto를 그대로 서비스에 넣어도 되는지?
        // 넣어도 된다고 생각하지만, 프로젝트의 규모가 커질수록 컨트롤러에서 쓰는 dto와 서비스에서 쓰는 dto를 나눈다
        // 겹치는 내용이 많아지니까 왜 나누지? 생각할 수 있지만, 서비스의 규모가 커지는 경우 나누는 것이 좋다
        // 위의 예시는 파라미터를 다 분해를 해서 만들어 둔 경우이다(String~ 하고 5가지 파라미터가 다 들어가 있음)
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id){
        //아이디 기반으로 todo 레포지토리에서 조회를 하는 메소드
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo Not Exist"));
                //exception 관리를 위해 id기반 조회시 해당 todo를 발견할 수 없으면 nothing 리액션을 던지도록 선언
    }

    public List<Todo> getTodos(){
        return todoRepository.findAllByOrderByCreatedAtDesc();
        // 레포지토리에 만들어 둔거 그냥 그대로 가져오면 끝~
        // 다만 상세 조회 DTO와 전체 조회 DTO를 나눠두자
    }


}

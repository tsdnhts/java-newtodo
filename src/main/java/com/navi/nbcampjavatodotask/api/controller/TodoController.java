package com.navi.nbcampjavatodotask.api.controller;

import com.navi.nbcampjavatodotask.api.model.todo.TodoCreateRequest;
import com.navi.nbcampjavatodotask.api.model.todo.TodoResponse;
import com.navi.nbcampjavatodotask.api.service.TodoService;
import com.navi.nbcampjavatodotask.database.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    // 의존성을 주입받기 위한 필수 argument를 받는 어노테이션을 붙여준다
    // requestMapping을 써서 컨트롤러에서 받는 api들의 프리픽스를 지정해준다(api 주소)
    private final TodoService todoService;

    @PostMapping
    // 생성이므로 postmapping
    //TodoResponse를 반환하고, requestbody로 TodoCreateRequest를 받는다
    public TodoResponse createTodo(@RequestBody TodoCreateRequest request){
        Todo todo = todoService.createTodo(
                request.title(),
                request.content(),
                request.assignee(),
                request.password(),
                LocalDateTime.now()
                // 현재는 필드가 많지 않아 따로 서비스 dto를 만들지 않았으나, 많아진다고 하면 컨트롤러dto/ 서비스 dto를 나눠 주는 것이 좋다.
                // 컨트롤러 레이어에서 localDateTime.now를 찍어서 생성 시간을 주입해 준다.
                // 지금 이 팩토리 method에서 request.title, content 등의 위치가 바뀌면, 출력값도 바뀐다
                // title 다음 assignee를 넣는 다면
                // 프로그램 실행 후 출력 창에 content : 담당자 이런 식으로 표기되고
                // assignee : 내용 이런 식으로 잘못된 표기 뒤에 값이 들어간다.
        );
        return TodoResponse.of(todo);
    }

    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable("id") Long id){
        Todo todo = todoService.getTodoById(id);
        return TodoResponse.of(todo);
        // 왜 PathVariable 쓰는지 기존 다른 강의에서 설명 했었음 다시 볼 것
        // id를 Pathvariable로 받아서 주입을 받고, todo서비스에 있는 getTodoById에 호출을 해서
        // 해당 내용을 TodoResponse로 바꿔서 return을 해준다.
    }
}

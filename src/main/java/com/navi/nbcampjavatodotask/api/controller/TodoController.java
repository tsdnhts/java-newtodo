package com.navi.nbcampjavatodotask.api.controller;

import com.navi.nbcampjavatodotask.api.model.todo.*;
import com.navi.nbcampjavatodotask.api.service.TodoService;
import com.navi.nbcampjavatodotask.database.entity.Todo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    // 의존성을 주입받기 위한 필수 argument를 받는 어노테이션을 붙여준다
    // requestMapping을 써서 컨트롤러에서 받는 api들의 프리픽스를 지정해준다(api 주소)
    private final TodoService todoService;

    @PostMapping
    @Operation(summary = " Todo 생성 API ")
    // 생성이므로 postmapping
    //TodoResponse를 반환하고, requestbody로 TodoCreateRequest를 받는다
    public TodoResponse createTodo(@RequestBody @Valid TodoCreateRequest request){
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
    @GetMapping
    public List<SimplifiedTodoResponse> getTodos(){
        List<Todo> todos = todoService.getTodos();
        // 리스트를 반환한 뒤 스트림을 사용하여 변환한다.
        return todos.stream()
                // 리스트에 있는 것들을 하나하나 SimplifiedTodoResponse로 만들어 줘야 하니까
                // id, title, createdAt을 넣어서 해당하는 값으로 변형을 하기 위한 메소드를 사용해 준다.
                .map(it-> new SimplifiedTodoResponse(it.getId(), it.getTitle(), it.getCreatedAt()))
                // 다시 리스트로 바꿔서 리턴해야 되므로 toList를 사용한다.
                // SimplifiedTodoResponse DTO 같은 경우는 필드도 별로 없고(id, title, createdat) 심플하며
                // 사용하는 부분이 getTodos 메소드 한개밖에 없어서 따로 팩토리 메소드를 생성하지 않았다.
                .toList();
    }

    @PatchMapping("/{id}")
    // PATCH -> 수정을 하긴 하되, 일부만 수정할때 사용
    // PUT -> 수정을 하긴 하되, 전체를 수정할때 -> FILES put /images /files 같은 경우 사용한다고 함
    // 관련 키워드 : 멱등성 -> 어떤 행동을 할 때, 어떤 상황에서 몇번째로 하든 같은 결과를 보장하는 것
    public TodoResponse updateTodo(
            @PathVariable("id") Long id,
            @RequestBody @Valid TodoUpdateRequest request
            // id 값을 주입받기 위해 PathVariable
            // 나머지는 업데이트 할 내용을 받아야 하므로 RequestBody로 받는다
    ){
        Todo todo = todoService.updateTodo(
                id,
                request.title(),
                request.content(),
                request.assignee(),
                request.password()
        );
        return TodoResponse.of(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(
            @PathVariable("id") Long id,
            @RequestBody @Valid TodoDeleteRequest request
    ){
       todoService.deleteTodo(id, request.password());
    }
}

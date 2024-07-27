package com.navi.nbcampjavatodotask.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 이거 단계별로 뭐가 다른지 찾아볼것
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String title;
    private String description;
    private String assignee;
    private String password;
    private LocalDateTime createdAt;

    //생성할 걸 고려하여 id를 제외한 나머지 필드 받는 생성자를 하나 만들어 줌
    public Todo(String title, String description, String assignee, String password, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.password = password;
        this.createdAt = createdAt;
    }
}

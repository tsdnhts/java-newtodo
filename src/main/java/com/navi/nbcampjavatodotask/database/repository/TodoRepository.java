package com.navi.nbcampjavatodotask.database.repository;

import com.navi.nbcampjavatodotask.database.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 작성일 기준 내림차순 구현을 위해 레포지토리에 해당 기능을 구현하는 메소드를 추가
    public List<Todo> findAllByOrderByCreatedAtDesc();
}

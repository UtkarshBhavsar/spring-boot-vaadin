package com.example.springvaadin.repository;

import com.example.springvaadin.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByDone(Boolean done);
}

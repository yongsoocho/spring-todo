package org.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todo.app.model.TodoEntitiy;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntitiy, Long> {
}

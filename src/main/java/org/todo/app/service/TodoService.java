package org.todo.app.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.todo.app.model.TodoEntitiy;
import org.todo.app.model.TodoRequest;
import org.todo.app.repository.TodoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;

	public TodoEntitiy add(TodoRequest request) {

		TodoEntitiy todoEntitiy = TodoEntitiy.builder()
				.title(request.getTitle())
				.order(request.getOrder())
				.completed(request.getCompleted())
				.build();

		return this.todoRepository.save(todoEntitiy);
	}

	public TodoEntitiy searchById(Long id) {
		return this.todoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<TodoEntitiy> searchAll() {
		return this.todoRepository.findAll();
	}

	public TodoEntitiy updateById(Long id, TodoRequest request) {
		TodoEntitiy todoEntitiy = this.searchById(id);
		if (request.getTitle() != null) {
			todoEntitiy.setTitle(request.getTitle());
		}
		if (request.getOrder() != null) {
			todoEntitiy.setOrder(request.getOrder());
		}
		if (request.getCompleted() != null) {
			todoEntitiy.setCompleted(request.getCompleted());
		}
		return this.todoRepository.save(todoEntitiy);
	}

	public void deleteById(Long id) {
		this.todoRepository.deleteById(id);
	}

	public void deleteAll() {
		this.todoRepository.deleteAll();
	}
}

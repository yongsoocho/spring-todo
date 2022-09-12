package org.todo.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.todo.app.model.TodoEntitiy;
import org.todo.app.model.TodoRequest;
import org.todo.app.model.TodoResponse;
import org.todo.app.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
	private final TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
		if (ObjectUtils.isEmpty(request.getTitle())) {
			return ResponseEntity.badRequest().build();
		}
		if (ObjectUtils.isEmpty(request.getOrder())) {
			request.setOrder(0L);
		}
		if (ObjectUtils.isEmpty(request.getCompleted())) {
			request.setCompleted(false);
		}
		TodoEntitiy result = this.todoService.add(request);
		return ResponseEntity.ok(new TodoResponse(result));
	}

	@GetMapping("{id}")
	public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
		TodoEntitiy result = this.todoService.searchById(id);
		return ResponseEntity.ok(new TodoResponse(result));
	}

	@GetMapping
	public ResponseEntity<List<TodoResponse>> readAll() {
		List<TodoEntitiy> list = this.todoService.searchAll();
		List<TodoResponse> response = list.stream().map(TodoResponse::new).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@PatchMapping("{id}")
	public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
		TodoEntitiy result = this.todoService.updateById(id, request);
		return ResponseEntity.ok(new TodoResponse(result));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Long id) {
		this.todoService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAll() {
		this.todoService.deleteAll();
		return ResponseEntity.ok().build();
	}
}

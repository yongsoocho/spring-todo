package org.todo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
	private Long id;
	private String title;
	private Long order;
	private Boolean completed;
	private String url;
}

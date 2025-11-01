package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo Controller - REST API endpoints
 * 
 * @RestController: Combines @Controller + @ResponseBody
 * @RequestMapping: Base URL for all endpoints in this controller
 * 
 * REST API Design:
 * - GET /api/todos - Get all todos
 * - GET /api/todos/{id} - Get a specific todo
 * - POST /api/todos - Create a new todo
 * - PUT /api/todos/{id} - Update a todo
 * - DELETE /api/todos/{id} - Delete a todo
 */
@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5173") // Allow Vue.js frontend to access
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    /**
     * GET /api/todos
     * Get all todos
     */
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }
    
    /**
     * GET /api/todos/{id}
     * Get a specific todo by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/todos
     * Create a new todo
     * 
     * @Valid: Validates the request body using annotations in Todo class
     */
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }
    
    /**
     * PUT /api/todos/{id}
     * Update an existing todo
     */
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        try {
            Todo updatedTodo = todoService.updateTodo(id, todo);
            return ResponseEntity.ok(updatedTodo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/todos/{id}
     * Delete a todo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * PATCH /api/todos/{id}/toggle
     * Toggle todo completion status
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleTodo(@PathVariable Long id) {
        try {
            Todo updatedTodo = todoService.toggleTodo(id);
            return ResponseEntity.ok(updatedTodo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


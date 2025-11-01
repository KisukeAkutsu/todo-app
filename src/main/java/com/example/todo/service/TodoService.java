package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Todo Service - Business logic layer
 * 
 * Separates business logic from controller
 * Makes code more testable and maintainable
 */
@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    /**
     * Get all todos
     */
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    /**
     * Get a todo by ID
     */
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    /**
     * Create a new todo
     */
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    /**
     * Update an existing todo
     */
    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.getCompleted());
        
        return todoRepository.save(todo);
    }
    
    /**
     * Delete a todo
     */
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    
    /**
     * Toggle todo completion status
     */
    public Todo toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        todo.setCompleted(!todo.getCompleted());
        return todoRepository.save(todo);
    }
}


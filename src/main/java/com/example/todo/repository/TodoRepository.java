package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Todo Repository - Handles database operations for Todo entities
 * 
 * JpaRepository provides:
 * - save(entity): Save or update a todo
 * - findById(id): Find todo by ID
 * - findAll(): Get all todos
 * - deleteById(id): Delete todo by ID
 * - And many more methods automatically!
 * 
 * Spring Data JPA automatically implements this interface at runtime
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    /**
     * Custom query method - Spring Data JPA automatically implements this
     * Finds all todos that are completed (or not completed)
     */
    List<Todo> findByCompleted(Boolean completed);
}


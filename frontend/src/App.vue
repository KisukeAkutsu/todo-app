<template>
  <div class="todo-container">
    <h1>📝 My Todo List</h1>
    
    <!-- Error Message -->
    <div v-if="error" class="error">
      {{ error }}
    </div>
    
    <!-- Todo Form -->
    <form @submit.prevent="handleSubmit" class="todo-form">
      <input
        v-model="newTodo.title"
        type="text"
        placeholder="What needs to be done?"
        class="todo-input"
        required
      />
      <input
        v-model="newTodo.description"
        type="text"
        placeholder="Description (optional)"
        class="todo-input"
      />
      <button type="submit" class="todo-button" :disabled="loading">
        {{ editingTodo ? 'Update' : 'Add Todo' }}
      </button>
      <button 
        v-if="editingTodo" 
        type="button" 
        class="todo-button" 
        @click="cancelEdit"
        style="background: #6c757d;"
      >
        Cancel
      </button>
    </form>
    
    <!-- Loading State -->
    <div v-if="loading && todos.length === 0" class="loading">
      Loading todos...
    </div>
    
    <!-- Todo List -->
    <ul v-else-if="todos.length > 0" class="todo-list">
      <li v-for="todo in todos" :key="todo.id" class="todo-item">
        <input
          type="checkbox"
          :checked="todo.completed"
          @change="toggleTodo(todo.id)"
          class="todo-checkbox"
        />
        <div class="todo-content">
          <div 
            class="todo-title" 
            :class="{ completed: todo.completed }"
          >
            {{ todo.title }}
          </div>
          <div v-if="todo.description" class="todo-description">
            {{ todo.description }}
          </div>
          <div class="todo-description" style="font-size: 12px; color: #999;">
            Created: {{ formatDate(todo.createdAt) }}
          </div>
        </div>
        <div class="todo-actions">
          <button
            @click="editTodo(todo)"
            class="todo-button-small button-edit"
          >
            Edit
          </button>
          <button
            @click="deleteTodo(todo.id)"
            class="todo-button-small button-delete"
          >
            Delete
          </button>
        </div>
      </li>
    </ul>
    
    <!-- Empty State -->
    <div v-else class="empty-state">
      <div class="empty-state-icon">📋</div>
      <p>No todos yet. Add one above to get started!</p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import axios from 'axios'

/**
 * Vue 3 Composition API Example
 * 
 * This component demonstrates:
 * - Reactive data with ref()
 * - Lifecycle hooks (onMounted)
 * - Event handling
 * - HTTP requests with axios
 * - Two-way data binding with v-model
 * - Conditional rendering with v-if/v-else
 * - List rendering with v-for
 */

export default {
  name: 'App',
  
  // Composition API - modern Vue 3 way
  setup() {
    
    // Reactive state
    const todos = ref([])
    const loading = ref(false)
    const error = ref('')
    const editingTodo = ref(null)
    
    const newTodo = ref({
      title: '',
      description: ''
    })
    
    // API base URL
    const API_URL = 'http://localhost:8080/api/todos'
    
    /**
     * Fetch all todos from backend
     */
    const fetchTodos = async () => {
      loading.value = true
      error.value = ''
      try {
        const response = await axios.get(API_URL)
        todos.value = response.data
      } catch (err) {
        error.value = 'Failed to load todos: ' + err.message
        console.error('Error fetching todos:', err)
      } finally {
        loading.value = false
      }
    }
    
    /**
     * Create a new todo
     */
    const createTodo = async () => {
      loading.value = true
      error.value = ''
      try {
        const response = await axios.post(API_URL, {
          title: newTodo.value.title,
          description: newTodo.value.description,
          completed: false
        })
        todos.value.push(response.data)
        newTodo.value = { title: '', description: '' }
      } catch (err) {
        error.value = 'Failed to create todo: ' + err.message
        console.error('Error creating todo:', err)
      } finally {
        loading.value = false
      }
    }
    
    /**
     * Update an existing todo
     */
    const updateTodo = async () => {
      loading.value = true
      error.value = ''
      try {
        const response = await axios.put(`${API_URL}/${editingTodo.value.id}`, {
          title: newTodo.value.title,
          description: newTodo.value.description,
          completed: editingTodo.value.completed
        })
        const index = todos.value.findIndex(t => t.id === editingTodo.value.id)
        if (index !== -1) {
          todos.value[index] = response.data
        }
        cancelEdit()
      } catch (err) {
        error.value = 'Failed to update todo: ' + err.message
        console.error('Error updating todo:', err)
      } finally {
        loading.value = false
      }
    }
    
    /**
     * Delete a todo
     */
    const deleteTodo = async (id) => {
      if (!confirm('Are you sure you want to delete this todo?')) {
        return
      }
      
      loading.value = true
      error.value = ''
      try {
        await axios.delete(`${API_URL}/${id}`)
        todos.value = todos.value.filter(t => t.id !== id)
      } catch (err) {
        error.value = 'Failed to delete todo: ' + err.message
        console.error('Error deleting todo:', err)
      } finally {
        loading.value = false
      }
    }
    
    /**
     * Toggle todo completion status
     */
    const toggleTodo = async (id) => {
      loading.value = true
      error.value = ''
      try {
        const response = await axios.patch(`${API_URL}/${id}/toggle`)
        const index = todos.value.findIndex(t => t.id === id)
        if (index !== -1) {
          todos.value[index] = response.data
        }
      } catch (err) {
        error.value = 'Failed to toggle todo: ' + err.message
        console.error('Error toggling todo:', err)
      } finally {
        loading.value = false
      }
    }
    
    /**
     * Edit a todo
     */
    const editTodo = (todo) => {
      editingTodo.value = todo
      newTodo.value = {
        title: todo.title,
        description: todo.description || ''
      }
    }
    
    /**
     * Cancel editing
     */
    const cancelEdit = () => {
      editingTodo.value = null
      newTodo.value = { title: '', description: '' }
    }
    
    /**
     * Handle form submission
     */
    const handleSubmit = () => {
      if (editingTodo.value) {
        updateTodo()
      } else {
        createTodo()
      }
    }
    
    /**
     * Format date for display
     */
    const formatDate = (dateString) => {
      if (!dateString) return ''
      const date = new Date(dateString)
      return date.toLocaleString()
    }
    
    // Lifecycle hook - runs when component is mounted
    onMounted(() => {
      fetchTodos()
    })
    
    // Return all the data and functions that template needs
    return {
      todos,
      loading,
      error,
      editingTodo,
      newTodo,
      fetchTodos,
      createTodo,
      updateTodo,
      deleteTodo,
      toggleTodo,
      editTodo,
      cancelEdit,
      handleSubmit,
      formatDate
    }
  }
}
</script>


# 📖 Learning Path for Todo Application

## 🎯 Your First Action: **RUN THE APPLICATION**

Before diving into code, **see it work first!** This gives you context for everything you'll learn.

### Step 1: Start Backend (Terminal 1)
```bash
cd /Users/scrooge/develop/spring
mvn spring-boot:run
```
**Wait for**: "Started TodoApplication" message
**Then visit**: http://localhost:8080/api/todos (should show `[]`)

### Step 2: Start Frontend (Terminal 2)
```bash
cd /Users/scrooge/develop/spring/frontend
npm run dev
```
**Then visit**: http://localhost:5173
**Try it**: Add a todo, complete it, delete it!

---

## 📚 Learning Path (Follow This Order)

### Phase 1: Understand the Big Picture (Day 1)

#### 1.1 How Data Flows
```
User clicks "Add Todo" 
  → Vue.js sends HTTP POST request
  → Spring Boot receives request
  → Controller processes it
  → Service handles business logic
  → Repository saves to database
  → Response sent back
  → Vue.js updates the UI
```

**Action**: Trace one operation:
- Open browser DevTools (F12) → Network tab
- Add a todo
- Watch the HTTP request/response
- See the data flow!

#### 1.2 File Structure Overview
```
Backend (Java):
  - Todo.java → Data model (what a todo looks like)
  - TodoRepository.java → Database operations
  - TodoService.java → Business logic
  - TodoController.java → HTTP endpoints

Frontend (Vue.js):
  - App.vue → Main component (the UI)
  - main.js → Entry point
  - style.css → Styling
```

---

### Phase 2: Backend Learning (Days 2-3)

#### 2.1 Start with: **Todo.java** (The Model)
**Location**: `src/main/java/com/example/todo/model/Todo.java`

**Learn about**:
- `@Entity` - Makes it a database table
- `@Id` - Primary key
- `@Column` - Maps to database columns
- Getters/Setters - Java bean pattern

**Exercise**: Add a new field like `priority` (String) and see what happens!

#### 2.2 Next: **TodoRepository.java** (Database Access)
**Location**: `src/main/java/com/example/todo/repository/TodoRepository.java`

**Learn about**:
- `JpaRepository` - What methods it provides automatically
- `findByCompleted()` - Custom query methods
- Spring Data JPA magic ✨

**Exercise**: Add a method `findByTitle(String title)` and use it!

#### 2.3 Then: **TodoService.java** (Business Logic)
**Location**: `src/main/java/com/example/todo/service/TodoService.java`

**Learn about**:
- `@Service` - Marks it as a service component
- `@Autowired` - Dependency injection
- Business logic separation

**Exercise**: Add validation (e.g., title must be < 100 characters)

#### 2.4 Finally: **TodoController.java** (REST API)
**Location**: `src/main/java/com/example/todo/controller/TodoController.java`

**Learn about**:
- `@RestController` - Creates REST endpoints
- `@GetMapping`, `@PostMapping`, etc. - HTTP methods
- `@RequestMapping` - URL mapping
- `@PathVariable` - URL parameters
- `@RequestBody` - Request body parsing

**Exercise**: Add a new endpoint `GET /api/todos/completed` to get only completed todos

---

### Phase 3: Frontend Learning (Days 4-5)

#### 3.1 Start with: **App.vue Template** (The HTML)
**Location**: `frontend/src/App.vue` (lines 1-90)

**Learn about**:
- `<template>` - HTML structure
- `v-model` - Two-way data binding
- `v-for` - Looping through todos
- `v-if` - Conditional rendering
- `@click`, `@submit` - Event handling

**Exercise**: Add a "Clear All" button that deletes all todos

#### 3.2 Next: **App.vue Script** (The Logic)
**Location**: `frontend/src/App.vue` (lines 92-280)

**Learn about**:
- `setup()` - Composition API entry point
- `ref()` - Reactive variables
- `onMounted()` - Lifecycle hook
- `axios` - HTTP requests
- `async/await` - Asynchronous programming

**Exercise**: Add error handling with user-friendly messages

#### 3.3 Then: **API Integration**
**Learn about**:
- How Vue calls the backend API
- HTTP methods (GET, POST, PUT, DELETE)
- Error handling
- Loading states

**Exercise**: Add a loading spinner while fetching todos

---

### Phase 4: Advanced Concepts (Days 6-7)

#### 4.1 Dependency Injection
**Where**: `TodoController.java` line 31, `TodoService.java` line 20

**Learn**:
- How Spring creates and injects dependencies
- Why it's useful (loose coupling, testability)
- The dependency chain: Controller → Service → Repository

#### 4.2 REST API Design
**Learn**:
- RESTful principles
- HTTP status codes (200, 201, 404, etc.)
- JSON data format
- CORS (Cross-Origin Resource Sharing)

#### 4.3 Database (H2)
**Learn**:
- In-memory database concept
- JPA/Hibernate ORM
- `@PrePersist` and `@PreUpdate` lifecycle callbacks

---

## 🛠️ Hands-On Exercises

### Beginner Exercises:
1. ✅ Add a "priority" field to todos (low/medium/high)
2. ✅ Add filtering (show only completed/incomplete)
3. ✅ Add search functionality
4. ✅ Improve error messages in the UI

### Intermediate Exercises:
1. ✅ Add due dates to todos
2. ✅ Add categories/tags
3. ✅ Add sorting (by date, priority, etc.)
4. ✅ Add pagination

### Advanced Exercises:
1. ✅ Switch from H2 to PostgreSQL
2. ✅ Add user authentication
3. ✅ Add unit tests
4. ✅ Add API documentation with Swagger

---

## 📝 Study Tips

### 1. Read Code Top to Bottom
Start with `TodoApplication.java` → Follow the flow → Understand each layer

### 2. Use Debugger
Set breakpoints in:
- Controller methods (see incoming requests)
- Service methods (see business logic)
- Repository methods (see database calls)

### 3. Experiment!
- Change code → See what breaks → Understand why
- Add new features → Learn by doing
- Break things intentionally → Then fix them!

### 4. Read Error Messages
- Spring Boot errors are helpful!
- Vue.js errors point to the problem
- Learn to read and understand them

### 5. Use Browser DevTools
- **Console**: See JavaScript errors
- **Network**: See API calls
- **Elements**: Inspect HTML structure

---

## 🎓 Recommended Learning Order

1. **Day 1**: Run app → Understand data flow → Play with UI
2. **Day 2**: Study backend model → Repository → Service
3. **Day 3**: Study Controller → REST API → Test endpoints
4. **Day 4**: Study Vue template → Understand HTML structure
5. **Day 5**: Study Vue script → Understand JavaScript logic
6. **Day 6**: Study dependency injection → Advanced Spring concepts
7. **Day 7**: Build a new feature → Apply everything learned!

---

## 📚 Resources

### Spring Boot:
- Official Docs: https://spring.io/projects/spring-boot
- Spring Boot Tutorial: https://spring.io/guides

### Vue.js:
- Official Docs: https://vuejs.org/
- Vue 3 Guide: https://vuejs.org/guide/

### REST API:
- REST API Tutorial: https://restfulapi.net/

---

## ❓ Common Questions

**Q: Where does the code start running?**
A: `TodoApplication.java` → Spring Boot starts the server

**Q: How does Vue connect to Spring Boot?**
A: Through HTTP requests → axios library → REST API endpoints

**Q: Where is the database?**
A: H2 in-memory database → Created automatically → Data lost on restart

**Q: How does dependency injection work?**
A: Spring creates objects → Sees `@Autowired` → Injects dependencies automatically

---

## 🚀 Next Steps After Learning

1. Build your own feature
2. Add tests
3. Deploy to cloud (Heroku, AWS, etc.)
4. Add authentication
5. Use a real database (PostgreSQL, MySQL)
6. Add more advanced features

---

**Remember**: Learning is iterative! Read → Run → Modify → Understand → Repeat!

**Start Now**: Run the application and explore! 🎉


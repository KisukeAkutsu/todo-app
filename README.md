# Todo Web Application

A full-stack Todo web application built with **Vue.js 3** (frontend) and **Spring Boot** (backend). This project demonstrates modern web development practices and serves as a learning resource.

## 🏗️ Architecture Overview

```
┌─────────────────┐         HTTP/REST API         ┌─────────────────┐
│                 │◄──────────────────────────────►│                 │
│  Vue.js Frontend│                               │  Spring Boot    │
│  (Port 5173)    │                               │  Backend        │
│                 │                               │  (Port 8080)    │
└─────────────────┘                               └─────────────────┘
                                                           │
                                                           ▼
                                                    ┌──────────────┐
                                                    │  H2 Database │
                                                    │  (In-Memory) │
                                                    └──────────────┘
```

## 📚 What You'll Learn

### Backend (Spring Boot)
1. **Spring Boot Basics**: Creating REST APIs
2. **JPA/Hibernate**: Database persistence
3. **RESTful API Design**: CRUD operations
4. **Dependency Injection**: Spring's IoC container
5. **MVC Pattern**: Model-View-Controller architecture
6. **CORS Configuration**: Cross-origin resource sharing

### Frontend (Vue.js)
1. **Vue 3 Composition API**: Modern reactive programming
2. **Component Structure**: Single File Components (.vue)
3. **HTTP Requests**: Using axios for API calls
4. **Reactive State**: Managing component state
5. **Event Handling**: User interactions
6. **Conditional Rendering**: v-if, v-for directives

## 🚀 Getting Started

### Prerequisites

- **Java 17 or higher** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi))
- **Node.js 18+** ([Download](https://nodejs.org/))
- **npm** (comes with Node.js)

### Step 1: Start the Backend (Spring Boot)

1. Open a terminal in the project root directory
2. Run the Spring Boot application:

```bash
# Using Maven wrapper (if available)
./mvnw spring-boot:run

# OR using Maven directly
mvn spring-boot:run
```

3. The backend will start on `http://localhost:8080`
4. You can verify it's running by visiting:
   - API: `http://localhost:8080/api/todos`
   - H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:tododb`)

### Step 2: Start the Frontend (Vue.js)

1. Open a **new terminal** window
2. Navigate to the frontend directory:

```bash
cd frontend
```

3. Install dependencies:

```bash
npm install
```

4. Start the development server:

```bash
npm run dev
```

5. The frontend will start on `http://localhost:5173`
6. Open your browser and navigate to `http://localhost:5173`

## 📁 Project Structure

```
spring/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── todo/
│       │               ├── TodoApplication.java      # Main application class
│       │               ├── controller/
│       │               │   └── TodoController.java   # REST API endpoints
│       │               ├── service/
│       │               │   └── TodoService.java      # Business logic
│       │               ├── repository/
│       │               │   └── TodoRepository.java   # Database operations
│       │               └── model/
│       │                   └── Todo.java             # Entity/Model class
│       └── resources/
│           └── application.properties                # Configuration
├── frontend/
│   ├── src/
│   │   ├── App.vue                                   # Main Vue component
│   │   ├── main.js                                   # Vue app entry point
│   │   └── style.css                                 # Styles
│   ├── index.html                                    # HTML template
│   ├── vite.config.js                                # Vite configuration
│   └── package.json                                  # Frontend dependencies
├── pom.xml                                           # Maven dependencies
└── README.md                                         # This file
```

## 🔧 Understanding the Code

### Backend Architecture

#### 1. **Model Layer** (`Todo.java`)
- Represents a Todo entity in the database
- Uses JPA annotations (`@Entity`, `@Id`, `@Column`)
- Contains validation annotations (`@NotBlank`)
- Automatically handles timestamps with `@PrePersist` and `@PreUpdate`

#### 2. **Repository Layer** (`TodoRepository.java`)
- Extends `JpaRepository` for automatic CRUD operations
- Spring Data JPA automatically implements this interface
- Provides methods like `save()`, `findById()`, `findAll()`, `deleteById()`

#### 3. **Service Layer** (`TodoService.java`)
- Contains business logic
- Uses `@Service` annotation for dependency injection
- Handles exceptions and data transformation

#### 4. **Controller Layer** (`TodoController.java`)
- Handles HTTP requests
- Uses `@RestController` annotation
- Maps URLs to methods using `@GetMapping`, `@PostMapping`, etc.
- Returns JSON responses

### Frontend Architecture

#### 1. **Main Component** (`App.vue`)
- Uses Vue 3 Composition API with `setup()`
- Manages reactive state with `ref()`
- Handles lifecycle with `onMounted()`
- Makes HTTP requests using `axios`

#### 2. **Key Vue Concepts Used**
- **v-model**: Two-way data binding for inputs
- **v-if/v-else**: Conditional rendering
- **v-for**: List rendering
- **@click/@submit**: Event handling
- **ref()**: Reactive references

## 🎯 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/todos` | Get all todos |
| GET | `/api/todos/{id}` | Get a specific todo |
| POST | `/api/todos` | Create a new todo |
| PUT | `/api/todos/{id}` | Update a todo |
| DELETE | `/api/todos/{id}` | Delete a todo |
| PATCH | `/api/todos/{id}/toggle` | Toggle completion status |

### Example API Requests

**Create a todo:**
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Boot","description":"Study the basics","completed":false}'
```

**Get all todos:**
```bash
curl http://localhost:8080/api/todos
```

**Update a todo:**
```bash
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Boot","description":"Advanced topics","completed":true}'
```

**Delete a todo:**
```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

## 🧪 Testing the Application

1. **Start both servers** (backend and frontend)
2. **Open** `http://localhost:5173` in your browser
3. **Try these actions:**
   - Add a new todo
   - Mark a todo as complete (checkbox)
   - Edit a todo
   - Delete a todo
   - View todos in the browser console (Network tab)

## 📖 Key Concepts Explained

### Spring Boot Annotations

- `@SpringBootApplication`: Main application entry point
- `@RestController`: Marks a class as a REST controller
- `@RequestMapping`: Maps URLs to controller methods
- `@GetMapping`, `@PostMapping`, etc.: HTTP method mappings
- `@Autowired`: Dependency injection
- `@Service`: Marks a class as a service component
- `@Repository`: Marks a class as a data repository
- `@Entity`: Marks a class as a JPA entity
- `@Id`: Marks a field as primary key

### Vue.js Concepts

- **Composition API**: Modern way to write Vue components
- **Reactive State**: Data that automatically updates the UI
- **Lifecycle Hooks**: Functions that run at specific component stages
- **Two-way Binding**: Automatic synchronization between data and UI
- **Template Syntax**: `{{ }}` for interpolation, `v-` directives

### REST API Principles

- **Stateless**: Each request contains all needed information
- **Resource-based URLs**: URLs represent resources (`/api/todos`)
- **HTTP Methods**: GET (read), POST (create), PUT (update), DELETE (delete)
- **JSON**: Data format for requests and responses

## 🔍 Debugging Tips

1. **Backend Logs**: Check the terminal where Spring Boot is running
2. **Frontend Console**: Open browser DevTools (F12) → Console tab
3. **Network Requests**: Check DevTools → Network tab to see API calls
4. **H2 Console**: Visit `http://localhost:8080/h2-console` to see database

## 🚀 Next Steps

Try these enhancements:

1. **Add filtering**: Filter todos by completion status
2. **Add search**: Search todos by title/description
3. **Add categories**: Group todos by category
4. **Add due dates**: Set and display due dates
5. **Add persistence**: Switch from H2 to PostgreSQL or MySQL
6. **Add authentication**: Implement user login
7. **Add tests**: Write unit and integration tests

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Vue.js Documentation](https://vuejs.org/)
- [REST API Tutorial](https://restfulapi.net/)
- [JPA Documentation](https://spring.io/projects/spring-data-jpa)

## 🐛 Troubleshooting

**Backend won't start:**
- Check Java version: `java -version` (should be 17+)
- Check Maven: `mvn -version`
- Check port 8080 isn't already in use

**Frontend won't start:**
- Check Node.js version: `node -v` (should be 18+)
- Delete `node_modules` and run `npm install` again
- Check port 5173 isn't already in use

**CORS errors:**
- Make sure backend is running on port 8080
- Check `@CrossOrigin` annotation in `TodoController.java`

**API calls failing:**
- Verify backend is running: `curl http://localhost:8080/api/todos`
- Check browser console for errors
- Verify CORS configuration

## 📝 License

This is a learning project. Feel free to use and modify as needed.

---

Happy coding! 🎉


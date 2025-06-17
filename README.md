# Task Tracking App (Spring Boot + PostgreSQL)

A **Task Management System** built using **Spring Boot** and **PostgreSQL**. This app allows users to create and manage multiple **Task Lists**, each containing **multiple Tasks**, helping project managers and professionals stay organized and productive.

---

## Features

- **Task Lists Management**: Create, update, and delete task lists for different projects.
- **Task Management**: Add, edit, delete tasks with title, description, due date, priority, and status.
- **Progress Tracking**: View how many tasks are completed in each task list.
- **Nested Structure**: Task routes are nested under their respective task lists.
- **RESTful API**: Easy integration with frontend or third-party systems.

---

## Tech Stack

- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA (Hibernate)
- **Build Tool**: Maven

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- PostgreSQL
- Postman or any API testing tool

### Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/ZRishu/task-tracker.git
   cd task-tracker
   ```

2. **Configure DB in `application.properties`**

   ```properties
   spring.datasource.url=DATABASE_URL
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```

3. **Run the App**

   - **Linux/macOS:**

     ```bash
     ./mvnw spring-boot:run
     ```

   - **Windows:**

     ```cmd
     mvnw.cmd spring-boot:run
     ```

4. **Access API**

   - App runs at: `http://localhost:8080`

---

## API Endpoints

### Task List

| Method | Endpoint           | Description         |
| ------ | ------------------ | ------------------- |
| GET    | `/task-lists`      | List all task lists |
| POST   | `/task-lists`      | Create a task list  |
| GET    | `/task-lists/{id}` | Get task list by ID |
| PUT    | `/task-lists/{id}` | Update task list    |
| DELETE | `/task-lists/{id}` | Delete task list    |

### Tasks (Nested under Task List)

| Method | Endpoint                                  | Description    |
| ------ | ----------------------------------------- | -------------- |
| GET    | `/task-lists/{taskListId}/tasks`          | List tasks     |
| POST   | `/task-lists/{taskListId}/tasks`          | Create task    |
| GET    | `/task-lists/{taskListId}/tasks/{taskId}` | Get task by ID |
| PUT    | `/task-lists/{taskListId}/tasks/{taskId}` | Update task    |
| DELETE | `/task-lists/{taskListId}/task/{taskId}`  | Delete task    |

---

## Functional Requirements

- Create/update/delete task lists with title and optional description.
- Create/update/delete tasks with:

  - Title (required)
  - Description (optional)
  - Due date
  - Priority: `HIGH`, `MEDIUM`, `LOW`
  - Status: `OPEN`, `CLOSED`

- View task list progress (% tasks completed).

---

## Frontend Integration Guide

To build a frontend (React, Angular, etc.):

1. **Fetch Task Lists**
   `GET /task-lists`

2. **Show Tasks in Each List**
   `GET /task-lists/{id}/tasks`

3. **Create/Edit/Delete Task Lists/Tasks**
   Use corresponding POST, PUT, DELETE endpoints.

4. **Show Progress**
   Calculate completed task count vs total tasks per list.

---
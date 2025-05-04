### 1. Mostrar todos los cursos

```
GET /courses
```

- **Descripción**: Devuelve el listado de todos los cursos disponibles.
- **Parámetros de query** (opcionales):
  - `categoryId` (UUID): filtrar por categoría
  - `instructorId` (UUID): filtrar por instructor
  - `page` (int), `size` (int): paginación
- **Respuesta (200 OK)**:
  ```json
  [
    {
      "id": "uuid-curso-1",
      "title": "Introducción a Java",
      "description": "Aprende Java desde cero",
      "category": {
        "id": "uuid-categoria",
        "name": "Programación"
      },
      "instructorId": "uuid-instructor",
      "averageRating": 4.7
    },
    { … }
  ]
  ```

---

### 2. Mostrar detalle de un curso

```
GET /courses/{courseId}
```

- **Descripción**: Devuelve toda la información de un curso, incluyendo secciones, lecciones y valoraciones.
- **Parámetros de ruta**:
  - `courseId` (UUID): identificador del curso
- **Respuesta (200 OK)**:
  ```json
  {
    "id": "uuid-curso-1",
    "title": "Introducción a Java",
    "description": "Aprende Java desde cero",
    "category": {
      "id": "uuid-categoria",
      "name": "Programación"
    },
    "instructorId": "uuid-instructor",
    "sections": [
      {
        "id": "uuid-seccion-1",
        "title": "Fundamentos",
        "order": 1,
        "lessons": [
          {
            "id": "uuid-lesson-1",
            "title": "Qué es Java",
            "videoUrl": "https://…",
            "duration": 600,
            "order": 1
          },
          { … }
        ]
      },
      { … }
    ],
    "reviews": [
      {
        "id": "uuid-review-1",
        "userId": "uuid-user-1",
        "rating": 5,
        "comment": "Excelente curso"
      },
      { … }
    ],
    "enrolledCount": 120
  }
  ```

---

### 3. Inscribirse en un curso

```
POST /courses/{courseId}/enrollments
```

- **Descripción**: Crea una inscripción (enrollment) para el usuario autenticado.
- **Headers**: `Authorization: Bearer <token>`
- **Parámetros de ruta**:
  - `courseId` (UUID)
- **Cuerpo (vacío)** _(el usuario viene del token)_  
- **Respuesta (201 Created)**:
  ```json
  {
    "id": "uuid-enrollment-123",
    "courseId": "uuid-curso-1",
    "userId": "uuid-user-1",
    "enrolledAt": "2025-04-30T10:15:00Z"
  }
  ```

---

### 4. Avanzar en el contenido (marcar lección como completada)

```
POST /courses/{courseId}/sections/{sectionId}/lessons/{lessonId}/progress
```

- **Descripción**: Registra (o actualiza) el progreso de la lección para el usuario autenticado.
- **Headers**: `Authorization: Bearer <token>`
- **Parámetros de ruta**:
  - `courseId`, `sectionId`, `lessonId` (UUIDs)
- **Cuerpo**:
  ```json
  {
    "completed": true
  }
  ```
- **Respuesta (200 OK)**:
  ```json
  {
    "id": "uuid-progress-456",
    "userId": "uuid-user-1",
    "lessonId": "uuid-lesson-1",
    "completed": true,
    "updatedAt": "2025-05-01T14:20:00Z"
  }
  ```

---

### 5. Ver progreso global de un curso

```
GET /courses/{courseId}/progress
```

- **Descripción**: Obtiene el progreso de todas las lecciones del curso para el usuario autenticado.
- **Headers**: `Authorization: Bearer <token>`
- **Parámetros de ruta**:
  - `courseId` (UUID)
- **Respuesta (200 OK)**:
  ```json
  {
    "courseId": "uuid-curso-1",
    "userId": "uuid-user-1",
    "totalLessons": 20,
    "completedLessons": 8,
    "percentage": 40,
    "details": [
      {
        "sectionId": "uuid-seccion-1",
        "sectionTitle": "Fundamentos",
        "lessons": [
          {
            "lessonId": "uuid-lesson-1",
            "title": "Qué es Java",
            "completed": true
          },
          {
            "lessonId": "uuid-lesson-2",
            "title": "Instalación",
            "completed": false
          }
        ]
      },
      { … }
    ]
  }
  ```

---

### 6. Registro de usuario (Register)

```
POST /auth/register
```

- **Descripción**: Crea una nueva cuenta de usuario.
- **Cuerpo**:
  ```json
  {
    "email": "usuario@ejemplo.com",
    "password": "MiPasswordSegura123",
    "name": "Nombre Apellido"
  }
  ```
- **Respuesta (201 Created)**:
  ```json
  {
    "userId": "uuid-user-1",
    "email": "usuario@ejemplo.com",
    "name": "Nombre Apellido",
    "createdAt": "2025-05-01T09:00:00Z"
  }
  ```

---

### 7. Inicio de sesión (Login)

```
POST /auth/login
```

- **Descripción**: Autentica al usuario y devuelve un JWT.
- **Cuerpo**:
  ```json
  {
    "email": "usuario@ejemplo.com",
    "password": "MiPasswordSegura123"
  }
  ```
- **Respuesta (200 OK)**:
  ```json
  {
    "token": "<jwt_token>",
    "expiresIn": 3600
  }
  ```

https://docukelo-icesi.onrender.com/docs/computacion-2/semana-5/jpa
./mvnw clean spring-boot:run



https://github.com/Kelocoes/compunet2-202601/blob/week-5/course-finish/src/main/java/com/example/demo/model/User.java#L5

Ejercicios de JPA con Query Methods
Introducción breve y referencias

En este post encontrarás 50 ejercicios que muestran distintas funcionalidades de los Query Methods con Spring Data JPA (métodos derivados por nombre) usando JpaRepository.
Para la referencia oficial sobre la creación de queries a partir de nombres de métodos y la lista de palabras clave soportadas por Spring Data JPA, consulta la documentación oficial de Spring Data JPA.

Fuentes recomendadas:

Spring Data JPA — JPA Query Methods (reference)
Spring Data JPA — Repository query keywords (lista de keywords soportadas)
Baeldung — Spring Data JPA Query Methods
Especificación de los modelos
A continuación se definen las entidades que se usarán como referencia en los ejercicios. Puedes copiarlas directamente en tu proyecto Spring Boot y rellenar sus métodos faltantes usando Lombok.

Especificación del modelo a revisar
A continuación se definen las entidades Java (anotaciones JPA) que usaremos como referencia en los ejercicios.

package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // getters / setters
}

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Timestamp hireDate;
    private boolean active;

    @ManyToOne
    private Department department;
    // getters / setters
}

@Entity
@Table(name = "courses")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code; // e.g. CS101
    private String title;
    private int credits;
    private Timestamp startDate;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Level level; // BEGINNER / INTERMEDIATE / ADVANCED

    @ManyToOne
    private Department department;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
    // getters / setters
}

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double gpa;
    private boolean active;
    private Timestamp registrationDate;

    private String city;
    private String state;

    @ManyToMany
    @JoinTable(name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    private Instructor advisor;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();
    // getters / setters
}

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @Enumerated(EnumType.STRING)
    private Semester semester; // SPRING, FALL, etc.

    private Double grade;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status; // ENROLLED, DROPPED, COMPLETED
    // getters / setters
}

public enum Level {
    BEGINNER, INTERMEDIATE, ADVANCED
}

public enum Semester {
    SPRING, SUMMER, FALL, WINTER
}

public enum EnrollmentStatus {
    ENROLLED, DROPPED, COMPLETED
}

Nota: las entidades previas muestran los campos y relaciones básicos.

Ejercicios de QueryMethods usando JpaRepository
A continuación vienen algunos ejercicios para practicar la creación de query methods usando JpaRepository. Cada ejercicio incluye:

Un subtítulo con el nombre del query method.
Una descripción de lo que se pide.
Una sección <details> con la solución esperada: la firma del método en el Repository y un ejemplo de uso (servicio o test).
findByUsername
Qué se requiere: Encuentra todos los estudiantes cuyo username sea igual a un string dado.

Solución esperada
findByEmailIgnoreCase
Qué se requiere: Busca estudiantes por email, ignorando mayúsculas/minúsculas.

Solución esperada
findByFirstNameAndLastName
Qué se requiere: Busca estudiantes por firstName y lastName.

Solución esperada
List<Student> findByFirstNameAndLastName(String firstName, String lastName);

Uso:

repo.findByFirstNameAndLastName("Alice", "Lopez");

findByAgeGreaterThan
Qué se requiere: Encuentra estudiantes con age mayor a un valor.

Solución esperada
List<Student> findByAgeGreaterThan(Integer age);

Uso:

repo.findByAgeGreaterThan(21);

findByAgeGreaterThanEqual
Qué se requiere: Encuentra estudiantes con age mayor o igual a un valor.

Solución esperada
List<Student> findByAgeGreaterThanEqual(Integer age);

findByGpaBetween
Qué se requiere: Encuentra estudiantes con gpa dentro de un rango (inclusive).

Solución esperada
List<Student> findByGpaBetween(Double min, Double max);

Uso:

repo.findByGpaBetween(3.0, 4.0);

findByActiveTrue
Qué se requiere: Encuentra estudiantes activos (campo boolean active = true).

Solución esperada
List<Student> findByActiveTrue();

Uso:

repo.findByActiveTrue();

findByActiveFalse
Qué se requiere: Encuentra estudiantes inactivos (campo boolean active = false).

Solución esperada
List<Student> findByActiveFalse();

findByRegistrationDateAfter
Qué se requiere: Encuentra estudiantes que se registraron después de una fecha dada.

Solución esperada
List<Student> findByRegistrationDateAfter(Timestamp date);

Uso:

repo.findByRegistrationDateAfter(Timestamp.valueOf(LocalDateTime.of(2024, 1, 1, 0, 0)));

findByRegistrationDateBetween
Qué se requiere: Encuentra estudiantes registrados entre dos fechas.

Solución esperada
List<Student> findByRegistrationDateBetween(Timestamp from, Timestamp to);

Uso:

repo.findByRegistrationDateBetween(Timestamp.valueOf(LocalDateTime.of(2024,1,1,0,0)), Timestamp.valueOf(LocalDateTime.of(2024,12,31,23,59)));


findByCoursesCode
Qué se requiere: Encuentra estudiantes que estén inscritos en un curso con un código dado (join implicito con collection courses).

Solución esperada
List<Student> findByCoursesCode(String code);

Uso:

repo.findByCoursesCode("CS101");

findByCoursesTitleContainingIgnoreCase
Qué se requiere: Busca estudiantes que tengan cursos cuyo título contenga una subcadena (case-insensitive).

Solución esperada
List<Student> findByCoursesTitleContainingIgnoreCase(String titlePart);

Uso:

repo.findByCoursesTitleContainingIgnoreCase("data");

findDistinctByCoursesCode
Qué se requiere: Encuentra estudiantes distintos (sin duplicados) inscritos en el curso code.

Solución esperada
List<Student> findDistinctByCoursesCode(String code);

Uso:

repo.findDistinctByCoursesCode("CS101");

findByAdvisorLastName
Qué se requiere: Encuentra estudiantes cuyo advisor (ManyToOne Instructor) tiene un apellido dado.

Solución esperada
List<Student> findByAdvisorLastName(String lastName);

Uso:

repo.findByAdvisorLastName("Gonzalez");

findByAdvisorId
Qué se requiere: Encuentra estudiantes por el id del advisor.

Solución esperada
List<Student> findByAdvisorId(Long instructorId);

Uso:

repo.findByAdvisorId(42L);

findByAdvisorIsNull
Qué se requiere: Encuentra estudiantes que no tienen advisor asignado.

Solución esperada
List<Student> findByAdvisorIsNull();

Uso:

repo.findByAdvisorIsNull();

existsByEmail
Qué se requiere: Comprueba si existe un estudiante con un email dado.

Solución esperada
boolean existsByEmail(String email);

Uso:

boolean exists = repo.existsByEmail("alice@example.com");

countByActiveTrue
Qué se requiere: Cuenta cuantos estudiantes están activos.

Solución esperada
long countByActiveTrue();

Uso:

long activeStudents = repo.countByActiveTrue();

deleteByUsername
Qué se requiere: Borra estudiantes por username (método reservado: delete...).

Solución esperada
void deleteByUsername(String username);

Uso:

repo.deleteByUsername("old_user");

findTop5ByOrderByGpaDesc
Qué se requiere: Devuelve los 5 estudiantes con mayor GPA (Top / OrderBy).

Solución esperada
List<Student> findTop5ByOrderByGpaDesc();

Uso:

List<Student> top5 = repo.findTop5ByOrderByGpaDesc();

findFirstByOrderByRegistrationDateAsc
Qué se requiere: Encuentra el primer estudiante (el más antiguo) por fecha de registro.

Solución esperada
Optional<Student> findFirstByOrderByRegistrationDateAsc();

Uso:

Optional<Student> first = repo.findFirstByOrderByRegistrationDateAsc();




📚 RESUMEN COMPLETO: Query Methods, Relaciones JPA y Tablas Intermedias
🎯 PARTE 1: QUERY METHODS - RESUMEN
1. Estructura Básica
text
[Prefijo] + By + [Condiciones] + [OrderBy]
2. Prefijos más comunes
Prefijo	Uso	Retorno
findBy	Buscar entidades	List<T> o T
findFirstBy	Primer resultado	Optional<T>
countBy	Contar resultados	long
existsBy	Verificar existencia	boolean
deleteBy	Eliminar entidades	void o long
3. Reglas de Navegación
Sin And (navegación en cadena):
java
// Delivery → drone → code
List<Delivery> findByDroneCode(String droneCode);

// DroneEvent → delivery → drone → code
List<DroneEvent> findByDeliveryDroneCode(String droneCode);
Con And (múltiples condiciones):
java
// Vuelve a la entidad base después de And
List<Delivery> findByDroneCodeAndStatus(String droneCode, String status);
//              └── Delivery → drone → code ──┐
//                                            ├── Y
//              └── Delivery → status ────────┘
4. Palabras clave para condiciones
Palabra	Significado	Ejemplo
Between	Rango	findByRecordedAtBetween(start, end)
GreaterThan	Mayor que	findByAgeGreaterThan(int age)
LessThan	Menor que	findByAgeLessThan(int age)
Like	Patrón	findByNameLike(String pattern)
Containing	Contiene	findByNameContaining(String text)
IsNull	Es nulo	findByDeletedAtIsNull()
5. Reglas de Capitalización
java
// ✅ CORRECTO
OrderByAssignedAtDesc    // Después de OrderBy, cada palabra con MAYÚSCULA
findByDroneName          // Después de By, cada palabra con MAYÚSCULA

// ❌ INCORRECTO
OrderByassignedAtDesc    // assignedAt con 'a' minúscula
findByDronename          // Dronename con 'n' minúscula
🔗 PARTE 2: RELACIONES JPA - RESUMEN
1. Tipos de Relaciones
Relación	Anotación	Dueño de FK	Ejemplo
Muchos a Uno	@ManyToOne	Sí (tiene FK)	Delivery → Drone
Uno a Muchos	@OneToMany	No (usa mappedBy)	Drone → Delivery
Uno a Uno	@OneToOne	Uno de los dos	User → Profile
Muchos a Muchos	@ManyToMany	Tabla intermedia	Role → Permission
2. Reglas de Oro
java
// Lado @ManyToOne (tiene la FK)
@ManyToOne
@JoinColumn(name = "drone_id")  // ← SÍ usa @JoinColumn
private Drone drone;

// Lado @OneToMany (NO tiene la FK)
@OneToMany(mappedBy = "drone")  // ← NO usa @JoinColumn, usa mappedBy
private List<Delivery> deliveries;
📊 PARTE 3: TABLAS INTERMEDIAS CON EMBEDDED
¿Qué es una tabla intermedia?
Es una tabla que conecta dos entidades en una relación ManyToMany y puede contener atributos adicionales de la relación.

Ejemplo clásico:
Usuario ↔ Rol (ManyToMany)

La tabla intermedia user_rol puede tener: assigned_at, assigned_by

🎯 EJEMPLO PRÁCTICO: Usuarios y Roles con Fecha de Asignación
Escenario:
Un usuario puede tener múltiples roles

Un rol puede ser asignado a múltiples usuarios

Necesitamos guardar cuándo se asignó cada rol a cada usuario

Paso 1: Crear la entidad intermedia con @EmbeddedId
UserRoleId.java (Llave compuesta embebida)
java
package com.example.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // Esta clase se incrustará en otra entidad
public class UserRoleId implements Serializable {
    
    private Long userId;
    private Long roleId;
    
    // Constructor por defecto (requerido)
    public UserRoleId() {}
    
    // Constructor con parámetros
    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    
    // Getters y Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
    // equals() y hashCode() son OBLIGATORIOS para llaves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) && 
               Objects.equals(roleId, that.roleId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
UserRole.java (Entidad intermedia con atributos adicionales)
java
package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "user_roles")  // Tabla intermedia
public class UserRole {
    
    // Llave compuesta embebida
    @EmbeddedId
    private UserRoleId id;
    
    // Atributo adicional de la relación
    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;
    
    // Relación con User (usando @MapsId)
    @ManyToOne
    @MapsId("userId")  // Mapea el campo userId del EmbeddedId
    @JoinColumn(name = "user_id")
    private User user;
    
    // Relación con Role (usando @MapsId)
    @ManyToOne
    @MapsId("roleId")  // Mapea el campo roleId del EmbeddedId
    @JoinColumn(name = "role_id")
    private Role role;
    
    // Constructor útil
    public UserRole(User user, Role role, LocalDateTime assignedAt) {
        this.id = new UserRoleId(user.getId(), role.getId());
        this.user = user;
        this.role = role;
        this.assignedAt = assignedAt;
    }
}
User.java (Entidad Usuario)
java
package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    
    // Relación OneToMany hacia la tabla intermedia
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();
    
    // Método helper para agregar un rol con fecha
    public void addRole(Role role, LocalDateTime assignedAt) {
        UserRole userRole = new UserRole(this, role, assignedAt);
        this.userRoles.add(userRole);
        role.getUserRoles().add(userRole);
    }
}
Role.java (Entidad Rol)
java
package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    
    // Relación OneToMany hacia la tabla intermedia
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();
}
Estructura en Base de Datos
sql
-- Tabla users
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255)
);

-- Tabla roles
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description VARCHAR(255)
);

-- Tabla intermedia user_roles (CON atributo adicional)
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    assigned_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, role_id),  -- Llave compuesta
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
📊 Visualización de la Tabla Intermedia
text
┌─────────────────────────────────────────────────────────────────┐
│                         TABLA INTERMEDIA                        │
│                         user_roles                              │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────┐ │
│  │  user_id    │    │  role_id    │    │    assigned_at      │ │
│  │  (FK a User)│    │  (FK a Role)│    │  (ATRIBUTO EXTRA)   │ │
│  ├─────────────┤    ├─────────────┤    ├─────────────────────┤ │
│  │      1      │    │      1      │    │  2026-03-25 10:00   │ │
│  │      1      │    │      2      │    │  2026-03-25 10:05   │ │
│  │      2      │    │      1      │    │  2026-03-25 11:00   │ │
│  │      3      │    │      3      │    │  2026-03-25 12:00   │ │
│  └─────────────┘    └─────────────┘    └─────────────────────┘ │
│                                                                 │
│  La combinación (user_id, role_id) es la LLAVE PRIMARIA        │
└─────────────────────────────────────────────────────────────────┘
🧪 Ejemplo de Uso
java
@Service
public class UserRoleService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public void asignarRolAUsuario() {
        // 1. Obtener usuario y rol
        User user = userRepository.findById(1L).orElseThrow();
        Role role = roleRepository.findById(1L).orElseThrow();
        
        // 2. Asignar rol con fecha actual
        user.addRole(role, LocalDateTime.now());
        
        // 3. Guardar (cascade guarda automáticamente en user_roles)
        userRepository.save(user);
    }
    
    public List<UserRole> getRolesDeUsuario(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getUserRoles();  // Cada UserRole contiene assignedAt
    }
}
📋 ¿Por qué usar @EmbeddedId en lugar de @IdClass?
Aspecto	@EmbeddedId	@IdClass
Legibilidad	Más clara, todo en una clase	Más confusa, llave separada
Encapsulación	La llave es un objeto	La llave son múltiples campos
Uso en consultas	WHERE id.userId = ?	WHERE userId = ?
Recomendación	✅ RECOMENDADA	❌ Menos usada
🎯 Cuándo usar Tabla Intermedia con Embedded
Situación	Solución
Relación ManyToMany sin atributos adicionales	@ManyToMany simple
Relación ManyToMany con atributos adicionales	Tabla intermedia con @EmbeddedId
Relación ManyToMany con muchos atributos	Tabla intermedia como entidad normal (sin Embedded)
💡 Analogía para Recordar
text
EmbeddedId es como una LLAVE que tiene DOS PIEZAS:
┌─────────────────────────────────────┐
│           UserRoleId                │
│  ┌─────────────┬─────────────┐     │
│  │   userId    │   roleId    │     │
│  │  (pieza 1)  │  (pieza 2)  │     │
│  └─────────────┴─────────────┘     │
└─────────────────────────────────────┘
         │
         ▼
   Ambas piezas juntas
   forman la llave primaria
📝 Resumen Final
Concepto	Resumen
Query Methods	El nombre del método ES la consulta
Navegación	Usa . para navegar relaciones: DroneCode = drone.code
@ManyToOne	Tiene la FK, usa @JoinColumn
@OneToMany	No tiene FK, usa mappedBy
Tablas intermedias	Para ManyToMany con atributos adicionales
@EmbeddedId	Llave compuesta como objeto embebido
@MapsId	Mapea campos del EmbeddedId a las relaciones
¿Te quedó claro? ¿Quieres que profundice en algún aspecto específico?

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

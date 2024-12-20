package utez.edu.mx.gestionProyectos.modules.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.gestionProyectos.modules.project.Project;
import utez.edu.mx.gestionProyectos.modules.rol.Rol;

import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name="id_rol",nullable = false)
    private Rol rol;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String username, String password, String name, String surname, String lastname, String email, boolean status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
    }

    public Employee(long id, String username, String password, String name, String surname, String lastname, String email, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
    }

    public Employee(String username, String password, String name, String surname, String lastname, String email, boolean status, Rol rol, List<Project> projects) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
        this.rol = rol;
        this.projects = projects;
    }

    public Employee(long id, String username, String password, String name, String surname, String lastname, String email, boolean status, Rol rol, List<Project> projects) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.status = status;
        this.rol = rol;
        this.projects = projects;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", projects=" + projects +
                '}';
    }

}

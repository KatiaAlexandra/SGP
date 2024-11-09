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

    @Column(name = "name", nullable = false)
    private String name;

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

    public Employee(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Employee(long id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Employee(String name, boolean status, Rol rol, List<Project> projects) {
        this.name = name;
        this.status = status;
        this.rol = rol;
        this.projects = projects;
    }

    public Employee(long id, String name, boolean status, Rol rol, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.rol = rol;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

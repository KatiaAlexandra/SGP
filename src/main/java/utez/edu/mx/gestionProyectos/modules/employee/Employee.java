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

    @ManyToOne
    @JoinColumn(name="id_rol",nullable = false)
    @JsonIgnore
    private Rol rol;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Project> projects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

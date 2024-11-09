package utez.edu.mx.gestionProyectos.modules.phase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.gestionProyectos.modules.project.Project;
import utez.edu.mx.gestionProyectos.modules.task.Task;

import java.util.List;

@Entity
@Table(name="phase")
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "phases")
    @JsonIgnore
    private List<Project> projects;

    public Phase() {
    }

    public Phase(String name) {
        this.name = name;
    }

    public Phase(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Phase(String name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public Phase(int id, String name, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

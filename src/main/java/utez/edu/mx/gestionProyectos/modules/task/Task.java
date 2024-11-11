package utez.edu.mx.gestionProyectos.modules.task;

import jakarta.persistence.*;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;
import utez.edu.mx.gestionProyectos.modules.project.Project;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name="id_project")
    private Project project;

    @ManyToOne
    @JoinColumn(name="id_phase")
    private Phase phase;

    public Task() {
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public Task(long id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Task(String description, boolean status, Project project, Phase phase) {
        this.description = description;
        this.status = status;
        this.project = project;
        this.phase = phase;
    }

    public Task(long id, String description, boolean status, Project project, Phase phase) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.project = project;
        this.phase = phase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}

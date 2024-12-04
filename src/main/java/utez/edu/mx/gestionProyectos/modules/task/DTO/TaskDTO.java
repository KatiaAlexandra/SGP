package utez.edu.mx.gestionProyectos.modules.task.DTO;

import utez.edu.mx.gestionProyectos.modules.phase.Phase;
import utez.edu.mx.gestionProyectos.modules.project.DTO.ProjectDTO;

public class TaskDTO {
    private long id;
    private String description;
    private boolean status;
    private ProjectDTO project;
    private Phase phase;

    public TaskDTO(long id, String description, boolean status, ProjectDTO project, Phase phase) {
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

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}

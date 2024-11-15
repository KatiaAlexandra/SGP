package utez.edu.mx.gestionProyectos.modules.project.DTO;

import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;

import java.util.List;

public class ProjectDTO {
    private long id_project;
    private String name;
    private String identifier;
    private String startDate;
    private String finishDate;
    private String estimatedDate;
    private boolean status;
    private List<EmployeeDTO> employee; //Cambiar por employeeDTO
    private String currentPhase;

    public ProjectDTO() {
    }

    public ProjectDTO(long id_project, String name, String identifier, String startDate, String finishDate, String estimatedDate, boolean status, List<EmployeeDTO> employee, String currentPhase) {
        this.id_project = id_project;
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.estimatedDate = estimatedDate;
        this.status = status;
        this.employee = employee;
        this.currentPhase = currentPhase;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public String getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(String estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public long getId_project() {
        return id_project;
    }

    public void setId_project(long id_project) {
        this.id_project = id_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<EmployeeDTO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeDTO> employee) {
        this.employee = employee;
    }
}

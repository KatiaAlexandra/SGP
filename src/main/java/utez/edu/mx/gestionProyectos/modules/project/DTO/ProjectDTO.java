package utez.edu.mx.gestionProyectos.modules.project.DTO;

import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;
import utez.edu.mx.gestionProyectos.modules.employee.Employee;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;

import java.util.List;

public class ProjectDTO {
    private long id_project;
    private String name;
    private String identifier;
    private String startDate;
    private String finishDate;
    private int status;
    private List<Employee> employee; //Cambiar por employeeDTO
    private List<Phase> phases;

    public ProjectDTO() {
    }

    public ProjectDTO(long id_project, String name, String identifier, String startDate, String finishDate, int status, List<Employee> employee, List<Phase> phases) {
        this.id_project = id_project;
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.employee = employee;
        this.phases = phases;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }
}

package utez.edu.mx.gestionProyectos.modules.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.gestionProyectos.modules.employee.Employee;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;
import utez.edu.mx.gestionProyectos.modules.task.Task;

import java.util.List;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="name", nullable = false,unique = true)
    private String name;

    @Column(name="identifier", nullable = false,unique = true)
    private String identifier;

    @Column(name="start_date", nullable = false)
    private String startDate;

    @Column(name="estimated_date")
    private String estimatedDate;

    @Column(name="finish_date")
    private String finishDate;

    @Column(name="status", nullable = false)
    private boolean status;

    //project_has_employees
    @ManyToMany
    @JoinTable(
            name="project_has_employees",
            joinColumns = @JoinColumn(name="id_project"),
            inverseJoinColumns = @JoinColumn(name="id_employee")
    )
    private List<Employee> employees;


    //project_has_phases
    @ManyToMany
    @JoinTable(
            name="project_has_phases",
            joinColumns = @JoinColumn(name="id_project"),
            inverseJoinColumns = @JoinColumn(name="id_phase")
    )
    private List<Phase> phases;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;

    public Project() {
    }

    public Project(String name, String identifier, String startDate, String estimatedDate, String finishDate, boolean status) {
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.finishDate = finishDate;
        this.status = status;
    }

    public Project(long id, String name, String identifier, String startDate, String estimatedDate, String finishDate, boolean status) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.finishDate = finishDate;
        this.status = status;
    }

    public Project(String name, String identifier, String startDate, String estimatedDate, String finishDate, boolean status, List<Employee> employees, List<Phase> phases, List<Task> tasks) {
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.finishDate = finishDate;
        this.status = status;
        this.employees = employees;
        this.phases = phases;
        this.tasks = tasks;
    }

    public Project(long id, String name, String identifier, String startDate, String estimatedDate, String finishDate, boolean status, List<Employee> employees, List<Phase> phases, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.finishDate = finishDate;
        this.status = status;
        this.employees = employees;
        this.phases = phases;
        this.tasks = tasks;
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

    public String getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(String estimatedDate) {
        this.estimatedDate = estimatedDate;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

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

    @ManyToOne
    @JoinColumn(name="id_project")
    private Project project;

    @ManyToOne
    @JoinColumn(name="id_phase")
    private Phase phase;
}

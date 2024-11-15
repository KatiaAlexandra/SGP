package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    //Traer todos los proyectos: Sirve para poder mostrarlos en la interfaz
    List<Project> findAll();

    //Traer proyecto por id: Sirve para poder actualizar
    Project findById(long id);

    //Guardar, actualizar proyecto y cambiar estado
    Project save(Project project);

    //Cambiar status de proyecto
    @Modifying
    @Query(value = "UPDATE project SET status=:status, finish_date = CASE WHEN :finishDate IS NOT NULL THEN :finishDate ELSE finish_date END WHERE id=:id_project", nativeQuery = true)
    void finishProject(
            @Param("status") boolean status,
            @Param("finishDate") String finishDate,
            @Param("id_project") long id_project
    );

    @Modifying
    @Query(value="INSERT INTO project_has_phases (id_project, id_phase) values(:idProject,:idPhase)", nativeQuery = true)
    void saveProjectHasPhases(@Param("idProject")long idProject, @Param("idPhase")int idPhase);


    @Query(value="SELECT name FROM phase WHERE id=(SELECT MAX(id_phase) AS id_maximo FROM project_has_phases WHERE id_project=:idProject AND id_phase BETWEEN 1 AND 5);", nativeQuery = true)
    String currentPhase(@Param("idProject")long idProject);
}


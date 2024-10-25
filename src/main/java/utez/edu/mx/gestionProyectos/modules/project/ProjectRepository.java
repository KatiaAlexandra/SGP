package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    //Traer todos los proyectos: Sirve para poder mostrarlos en la interfaz
    List<Project> findAll();

    //List<Project> findAllByEmployees(@Param("employeeId")long employee);

    //Traer proyecto por id: Sirve para poder actualizar
    Project findById(long id);

    //Guardar, actualizar proyecto y cambiar estado
    Project save(Project project);

    //Cambiar status de proyecto
    @Modifying
    @Query(value = "UPDATE project SET status=:status, finish_date = CASE WHEN :finishDate IS NOT NULL THEN :finishDate ELSE finish_date END WHERE id=:id_project", nativeQuery = true)
    void finishProject(
            @Param("status") int status,
            @Param("finishDate") String finishDate,
            @Param("id_project") long id_project
    );
}

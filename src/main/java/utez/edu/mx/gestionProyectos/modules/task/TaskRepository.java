package utez.edu.mx.gestionProyectos.modules.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAll();

    Task findById(long id);

    Task save(Task task);

    @Modifying
    @Query(value="DELETE FROM task WHERE id=:idTask;", nativeQuery = true)
    void deleteById(@Param("idTask")long idTask);


    //Sirve para validar si todas las tareas de una fase están terminadas
    @Query(value = "SELECT \n" +
            "    (SELECT COUNT(*) FROM task WHERE id_project =:idProject AND id_phase =:idPhase) = \n" +
            "    (SELECT COUNT(*) FROM task WHERE id_project =:idProject AND id_phase =:idPhase AND status = 1) AS es_igual;", nativeQuery = true)
    long isPhaseFinished(@Param("idProject")long idProject, @Param("idPhase") int idPhase);

    @Modifying
    @Query(value="UPDATE task SET status=:status WHERE id=:idTask", nativeQuery = true)
    void changeStatus(@Param("status")boolean status, @Param("idTask")long idTask);


}

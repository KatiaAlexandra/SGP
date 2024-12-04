package utez.edu.mx.gestionProyectos.modules.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.project.ProjectRepository;
import utez.edu.mx.gestionProyectos.modules.project.ProjectService;
import utez.edu.mx.gestionProyectos.modules.task.DTO.TaskDTO;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Autowired
    private ProjectService projectService;

    public TaskDTO transformTaskDTO(Task task){
        return new TaskDTO(
                task.getId(),
                task.getDescription(),
                task.isStatus(),
                projectService.transformProjectDTO(task.getProject()),
                task.getPhase()
        );
    }



    @Transactional(readOnly = true)
    public ResponseEntity<?> findByProjectAndPhase(long idProject, int idPhase){
        List<TaskDTO> list = new ArrayList<>();
        String message="";
        if(taskRepository.findTaskByProjectAndPhase(idProject, idPhase).isEmpty()){
            message="Aún no hay registros";
        }else{
            message="Operación exitosa";
            for(Task t : taskRepository.findTaskByProjectAndPhase(idProject, idPhase)){
                list.add(transformTaskDTO(t));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK",200,list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long id){
        TaskDTO dto = null;
        Task found= taskRepository.findById(id);
        String message = "";
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            message= "Operación exitosa";
            dto = transformTaskDTO(found);
        }
        return customResponseEntity.getOkResponse(message, "OK",200,dto);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?>save(Task task){
        task.setStatus(false);
        try{
            taskRepository.save(task);
            return customResponseEntity.getOkResponse("Registro exitoso", "CREATED",201,null);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?>update(Task task){
        Task found= taskRepository.findById(task.getId());
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            task.setStatus(found.isStatus());
            try{
                taskRepository.save(task);
                return customResponseEntity.getOkResponse("Actualización exitosa", "OK",200,null);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }


    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?>deleteById(long id){
        Task found= taskRepository.findById(id);
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                taskRepository.deleteById(id);
                return customResponseEntity.getOkResponse("Eliminación exitosa", "OK",200,null);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> changeStatus(long id) {
        Task found = taskRepository.findById(id);

        // 1. Validar si la tarea existe
        if (found == null) {
            System.out.println("Tarea no encontrada con ID: " + id);
            return customResponseEntity.get404Response();
        }

        try {
            // 2. Cambiar el estado de la tarea si no está completada
            if (!found.isStatus()) {
                taskRepository.changeStatus(true, id);
                System.out.println("Estado de la tarea cambiado a completado. ID tarea: " + id);
            } else {
                System.out.println("La tarea ya estaba completada. ID tarea: " + id);
                return customResponseEntity.get400Response();
            }

            // 3. Verificar si todas las tareas de la fase actual están completadas
            long projectId = found.getProject().getId();
            int currentPhaseId = found.getPhase().getId();
            boolean isCurrentPhaseFinished = taskRepository.isPhaseFinished(projectId, currentPhaseId) == 1;

            System.out.println("¿Fase actual completada?: " + isCurrentPhaseFinished);

            // 4. Si la fase está completada, registrar en `project_has_phases`
            if (isCurrentPhaseFinished) {
                if (!(projectRepository.existsProjectPhase(projectId, currentPhaseId)==1)) {
                    projectRepository.saveProjectHasPhases(projectId, currentPhaseId);
                    System.out.println("Fase actual registrada en project_has_phases. ID fase: " + currentPhaseId);
                } else {
                    System.out.println("La fase actual ya estaba registrada. ID fase: " + currentPhaseId);
                }

                // 5. Intentar registrar la siguiente fase (si tiene tareas)
                int nextPhaseId = currentPhaseId + 1;
                long hasTasksInNextPhase = taskRepository.hasTasks(projectId, nextPhaseId);
                long isNextPhaseRegistered = projectRepository.existsProjectPhase(projectId, nextPhaseId);

                System.out.println("¿Fase siguiente tiene tareas?: " + hasTasksInNextPhase);
                System.out.println("¿Fase siguiente ya registrada?: " + isNextPhaseRegistered);

                if ((hasTasksInNextPhase==1) && !(isNextPhaseRegistered==1)) {
                    projectRepository.saveProjectHasPhases(projectId, nextPhaseId);
                    System.out.println("Siguiente fase registrada en project_has_phases. ID fase: " + nextPhaseId);
                }
            }

            return customResponseEntity.getOkResponse("Actualización exitosa", "OK", 200, null);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error durante la actualización: " + e.getMessage());
            return customResponseEntity.get400Response();
        }
    }




}

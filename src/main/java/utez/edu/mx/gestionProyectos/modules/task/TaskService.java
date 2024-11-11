package utez.edu.mx.gestionProyectos.modules.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.project.ProjectRepository;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.sql.SQLException;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        List<Task> list= taskRepository.findAll();
        String message="";
        if(list.isEmpty()){
            message="Aún no hay registros";
        }else{
            message="Operación exitosa";
        }
        return customResponseEntity.getOkResponse(message, "OK",200,list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long id){
        Task found= taskRepository.findById(id);
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            return customResponseEntity.getOkResponse("Operación exitosa", "OK",200,found);
        }
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

    //Sólo permitir cambiar el estatus una vez
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?>changeStatus(long id){
        Task found= taskRepository.findById(id);
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
                if((found.getPhase().getId()==1 || taskRepository.isPhaseFinished(found.getProject().getId(), (found.getPhase().getId()-1))==1)&&!found.isStatus()){
                    try{
                        taskRepository.changeStatus(!found.isStatus(),id);
                        if(taskRepository.isPhaseFinished(id, found.getPhase().getId())==1){
                            projectRepository.saveProjectHasPhases(found.getProject().getId(), found.getPhase().getId());
                        }
                        return customResponseEntity.getOkResponse("Actualización exitosa", "OK",200,null);
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                        return customResponseEntity.get400Response();
                    }
                }else{
                    return customResponseEntity.get400Response();
                }
            }
        }
}

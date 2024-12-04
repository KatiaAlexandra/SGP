package utez.edu.mx.gestionProyectos.modules.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    //Mostrar tareas por id de proyecto y id de fase
    @PreAuthorize("hasRole('ROLE_RAPE')or hasRole('ROLE_RD')or hasRole('ROLE_AP')")
    @GetMapping("/{idProject}/{idPhase}")
    public ResponseEntity<?>findByProjectAndPhase(@PathVariable("idProject")long idProject, @PathVariable("idPhase")int idPhase){
        return taskService.findByProjectAndPhase(idProject,idPhase);
    }

    //Mostrar las tareas por id de tarea
    @PreAuthorize("hasRole('ROLE_RAPE')or hasRole('ROLE_RD')or hasRole('ROLE_AP')")
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable("id")long id){
        return taskService.findById(id);
    }


    //RD
    @PreAuthorize("hasRole('ROLE_RD')")
    @PostMapping("")
    public ResponseEntity<?>save(@RequestBody Task task){
        return taskService.save(task);
    }

    //RD
    @PreAuthorize("hasRole('ROLE_RD')")
    @PutMapping("")
    public ResponseEntity<?>update(@RequestBody Task task){
        return taskService.update(task);
    }

    //AP- Finalizar las tareas
    @PreAuthorize("hasRole('ROLE_AP')")
    @PutMapping("/{id}")
    public ResponseEntity<?>changeStatus(@PathVariable("id")long id){
        return taskService.changeStatus(id);
    }

    //RD
    @PreAuthorize("hasRole('ROLE_RD')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id")long id){
        return taskService.deleteById(id);
    }
}

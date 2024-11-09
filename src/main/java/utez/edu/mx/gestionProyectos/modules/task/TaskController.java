package utez.edu.mx.gestionProyectos.modules.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<?>findAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable("id")long id){
        return taskService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<?>save(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping("")
    public ResponseEntity<?>update(@RequestBody Task task){
        return taskService.update(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>changeStatus(@PathVariable("id")long id){
        return taskService.changeStatus(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable("id")long id){
        return taskService.deleteById(id);
    }
}

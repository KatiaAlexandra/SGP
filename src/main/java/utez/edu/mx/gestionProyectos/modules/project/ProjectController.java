package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Obtener todos los proyectos
    @GetMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> getAllProjects() {
        return projectService.findAll();
    }

    // Obtener proyecto por ID
    @GetMapping("/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> getProjectById(@PathVariable("id") long id) {
        return projectService.findById(id);
    }

    // Guardar un nuevo proyecto
    @PostMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    // Actualizar un proyecto existente
    @PutMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return projectService.update(project);
    }

    // Finalizar un proyecto
    @PutMapping("/finish/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> finishProject(@PathVariable("id")long id) {
        return projectService.finishProject(id);
    }

}
package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Obtener todos los proyectos
    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return projectService.findAll();
    }

    // Obtener proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") long id) {
        return projectService.findById(id);
    }

    // Guardar un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        project.setId(id);  // Asegurar que el ID del proyecto coincida con el ID de la ruta
        return projectService.update(project);
    }

    // Finalizar un proyecto
    @PutMapping("/finish/{id}")
    public ResponseEntity<?> finishProject(@PathVariable("id")long id) {
        return projectService.finishProject(id);
    }

}
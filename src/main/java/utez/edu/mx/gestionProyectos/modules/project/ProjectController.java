package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = {"*"})
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Obtener todos los proyectos
    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("")
    public ResponseEntity<?> getAllProjects() {
        return projectService.findAll();
    }

    //Traer los proyectos en los que está asignado el empleado
    @PreAuthorize("hasRole('ROLE_RAPE')or hasRole('ROLE_RD')or hasRole('ROLE_AP')")
    @GetMapping("/user/{username}")
    public ResponseEntity<?> findAllByEmployee(@PathVariable("username") String username) {
        return projectService.findAllByEmployee(username);
    }

    // Obtener proyecto por ID
    @PreAuthorize("hasRole('ROLE_MASTER') or hasRole('ROLE_RAPE')or hasRole('ROLE_RD')or hasRole('ROLE_AP')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") long id) {
        return projectService.findById(id);
    }

    // Guardar un nuevo proyecto
    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PostMapping("")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    // Actualizar un proyecto existente
    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping("")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return projectService.update(project);
    }


    //RAPE
    @PreAuthorize("hasRole('ROLE_RAPE')")
    @PutMapping("/finish/{id}")
    public ResponseEntity<?> finishProject(@PathVariable("id")long id) {
        return projectService.finishProject(id);
    }



}
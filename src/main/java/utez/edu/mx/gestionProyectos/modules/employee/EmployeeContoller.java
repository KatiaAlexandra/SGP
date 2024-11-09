package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.gestionProyectos.modules.project.Project;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeContoller {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {return employeeService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {return employeeService.findById(id);}

    // Guardar un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Employee employee) {
        employee.setId(id);  // Asegurar que el ID del empleado coincida con el ID de la ruta
        return employeeService.update(employee);
    }

    // Finalizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable("id") long id, @RequestBody Employee employee) {
        employee.setId(id);  // Asegurar que el ID del empleado coincida con el ID de la ruta
        return employeeService.changeEmployeeStatus(employee);
    }
}

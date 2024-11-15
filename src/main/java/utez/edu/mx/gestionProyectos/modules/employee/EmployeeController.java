package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> getAllEmployees() {return employeeService.findAll();}

    @GetMapping("/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {return employeeService.findById(id);}

    // Guardar un nuevo proyecto
    @PostMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Actualizar un proyecto existente
    @PutMapping("/employee/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Employee employee) {
        employee.setId(id);  // Asegurar que el ID del empleado coincida con el ID de la ruta
        return employeeService.update(employee);
    }

    // Finalizar un proyecto
    @PutMapping("/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable("id") long id, @RequestBody Employee employee) {
        employee.setId(id);  // Asegurar que el ID del empleado coincida con el ID de la ruta
        return employeeService.changeEmployeeStatus(employee);
    }
}

package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;

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

    @GetMapping("/rol/{id}")
    public ResponseEntity<?> getEmployeeByRol(@PathVariable("id") int id) {return employeeService.findEmployeeByRolForCreate(id);}

    // Guardar un nuevo proyecto
    @PostMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Actualizar un proyecto existente
    @PutMapping("")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.update(employee);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_MASTER")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable("id") long id) {
        return employeeService.changeEmployeeStatus(id);
    }
  
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        return employeeService.delete(id);
    }
}

package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {return employeeService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {return employeeService.findById(id);}

    @GetMapping("/rol/{id}")
    public ResponseEntity<?> getEmployeeByRol(@PathVariable("id") int id) {return employeeService.findEmployeeByRol(id);}

    // Guardar un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Actualizar un proyecto existente
    @PutMapping("")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.update(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable("id") long id) {
        return employeeService.changeEmployeeStatus(id);
    }

    //Agregar eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        return employeeService.delete(id);
    }
}

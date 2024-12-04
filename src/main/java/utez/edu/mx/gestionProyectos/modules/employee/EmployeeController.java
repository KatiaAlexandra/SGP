package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = {"*"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("")
    public ResponseEntity<?> getAllEmployees() {return employeeService.findAll();}

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {return employeeService.findById(id);}

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/rol/{id}")
    public ResponseEntity<?> getEmployeeByRol(@PathVariable("id") int id) {return employeeService.findEmployeeByRolForCreate(id);}

    // Guardar un nuevo proyecto
    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PostMapping("")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Actualizar un proyecto existente
    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping("")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.update(employee);
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable("id") long id) {
        return employeeService.changeEmployeeStatus(id);
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        return employeeService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_MASTER') or hasRole('ROLE_AP') or hasRole('ROLE_RAPE') or hasRole('ROLE_RD')")
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody EmployeeDTO employee) {
        return employeeService.updatePassword(employee);
    }

    @PreAuthorize("hasRole('ROLE_MASTER') or hasRole('ROLE_AP') or hasRole('ROLE_RAPE') or hasRole('ROLE_RD')")
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUserInfo(@RequestBody EmployeeDTO employee) {
        return employeeService.updateUserInfo(employee);
    }

    @PreAuthorize("hasRole('ROLE_MASTER') or hasRole('ROLE_AP') or hasRole('ROLE_RAPE') or hasRole('ROLE_RD')")
    @GetMapping("/findEmployee/{username}")
    public ResponseEntity<?> getEmployeeByUsername(@PathVariable("username") String username) {return employeeService.findByUsername(username);}
}

package utez.edu.mx.gestionProyectos.modules.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin("*")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return rolService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findRolByEmployee(@PathVariable("username")String username){
        return rolService.findRolByUsername(username);
    }
}

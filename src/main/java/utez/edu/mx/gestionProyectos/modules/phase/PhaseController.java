package utez.edu.mx.gestionProyectos.modules.phase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phase")
@CrossOrigin(origins = {"*"})
public class PhaseController {
    @Autowired
    private PhaseService phaseService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return phaseService.findAll();
    }
}

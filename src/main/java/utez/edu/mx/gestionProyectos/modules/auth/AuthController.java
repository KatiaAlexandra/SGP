package utez.edu.mx.gestionProyectos.modules.auth;

import utez.edu.mx.gestionProyectos.modules.auth.DTO.AuthLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody AuthLoginDTO authLoginDTO){
        return authService.login(authLoginDTO);
    }
}

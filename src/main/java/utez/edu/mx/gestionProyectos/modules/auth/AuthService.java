package utez.edu.mx.gestionProyectos.modules.auth;

import utez.edu.mx.gestionProyectos.modules.auth.DTO.AuthLoginDTO;
import utez.edu.mx.gestionProyectos.utils.security.UserDetailsImpl;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;
import utez.edu.mx.gestionProyectos.utils.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.employee.Employee;
import utez.edu.mx.gestionProyectos.modules.employee.EmployeeRepository;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Autowired
    private JWTUtil jwtUtil;

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(AuthLoginDTO authLoginDTO) {
        Employee found = employeeRepository.findByPasswordAndEmailOrUsername(
              authLoginDTO.getPassword(),
              authLoginDTO.getUser()
        );
        if (found == null) {
            return customResponseEntity.get404Response();
        } else {
            try{
                UserDetails userDetails = new UserDetailsImpl(found);
                return customResponseEntity.getOkResponse(
                        "Inicio de sesión exitoso",
                        "OK",
                        200,
                        jwtUtil.generateToken(userDetails)
                );
            } catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                return customResponseEntity.get400Response();
            }
        }
    }
}

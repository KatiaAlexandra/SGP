package utez.edu.mx.gestionProyectos.modules.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepositoy rolRepositoy;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        List list = rolRepositoy.findAll();
        String message="";
        if(list.isEmpty()){
            message="Aún no hay registros";
        }else{
            message="Operación exitosa";
        }
        return customResponseEntity.getOkResponse(message, "OK",200,list);
    }
}

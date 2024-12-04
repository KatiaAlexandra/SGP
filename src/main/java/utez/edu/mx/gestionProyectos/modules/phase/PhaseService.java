package utez.edu.mx.gestionProyectos.modules.phase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.project.DTO.ProjectDTO;
import utez.edu.mx.gestionProyectos.modules.project.Project;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhaseService {
    @Autowired
    private PhaseRepository phaseRepository;
    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll(){
        List<Phase> list = phaseRepository.findAll();
        String message = phaseRepository.findAll().isEmpty()? "Aún no hay registros" : "Operación exitosa";
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }


}

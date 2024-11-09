package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;


    public ResponseEntity<?> findById(long id){
        Employee found= employeeRepository.findById(id);
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            return customResponseEntity.getOkResponse("Operación exitosa","OK",200,found);
        }
    }



}
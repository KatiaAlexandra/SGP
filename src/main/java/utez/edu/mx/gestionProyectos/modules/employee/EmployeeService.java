package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    public EmployeeDTO transformEmployeeDTO(Employee e) {
        return new EmployeeDTO(
                e.getId(),
                e.getName(),
                e.getSurname(),
                e.getLastname(),
                e.getEmail(),
                e.isStatus()

        );
    }

    @Transactional(readOnly = true)
    public ResponseEntity findAll() {
        List<EmployeeDTO> list = new ArrayList<>();
        String message = "";
        if (employeeRepository.findAll().isEmpty()) {
            message = "No hay registros";
        } else {
            message = "Registros encontrados";
            for (Employee e: employeeRepository.findAll()) {
                list.add(transformEmployeeDTO(e));
            }
        }
        return customResponseEntity.getOkResponse(list.isEmpty()? "Aún no hay registros":"Operación exitosa", "OK", 200, list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity findById(long id) {
        EmployeeDTO dto = null;
        Employee found = employeeRepository.findById(id);
        String message = "";
        if (found == null) {
            return customResponseEntity.get404Response();
        } else {
            message = "Operación exitosa";
            dto = transformEmployeeDTO(found);
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, dto);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Employee employee) {
        employee.setStatus(true);

        try{
            employeeRepository.save(employee);
            return customResponseEntity.getOkResponse(
                    "Registro exitoso",
                    "CREATED",
                    201,
                    null
            );
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get404Response();
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Employee employee) {
        if(employeeRepository.findById(employee.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                employeeRepository.save(employee);
                return customResponseEntity.getOkResponse(
                        "Actualización exitosa",
                        "OK",
                        200,
                        null
                );
            } catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> changeEmployeeStatus(Employee employee){
        if(employeeRepository.findById(employee.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                employee.setStatus(false);
                employeeRepository.changeEmployeeStatus(
                        employee.isStatus(),
                        employee.getId()
                );
                return customResponseEntity.getOkResponse("Operación exitosa", "OK", 200, null);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

}
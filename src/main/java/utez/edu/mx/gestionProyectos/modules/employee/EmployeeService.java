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

    public List<EmployeeDTO> transformEmployeesDTO(List<Employee> e) {
        List<EmployeeDTO> list= new ArrayList<>();
        for (Employee employee: e){
            list.add(transformEmployeeDTO(employee));
        }
        return list;
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
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity findEmployeeByRol(int idRol) {
        List<EmployeeDTO> list = new ArrayList<>();
        String message = "";
        if (employeeRepository.findEmployeeByRol(idRol).isEmpty()) {
            message = "No hay registros";
        } else {
            message = "Registros encontrados";
            for (Employee e: employeeRepository.findEmployeeByRol(idRol)) {
                list.add(transformEmployeeDTO(e));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
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
        employee.setStatus(false);

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
    public ResponseEntity<?> update(EmployeeDTO emp) {
        if(employeeRepository.findById(emp.getId_Employee())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                employeeRepository.updateEmployee(emp.getName(),emp.getSurname(),emp.getLastname(),emp.getEmail(),emp.getId_Employee());
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
    public ResponseEntity<?> delete(long id) {
        if(employeeRepository.findById(id)==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                employeeRepository.deleteEmployeeProjects(id);
                employeeRepository.delete(id);
                return customResponseEntity.getOkResponse(
                        "Eliminación exitosa",
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
    public ResponseEntity<?> changeEmployeeStatus(long id){
        Employee found = employeeRepository.findById(id);
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                employeeRepository.changeEmployeeStatus(
                        !found.isStatus(),
                        id
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
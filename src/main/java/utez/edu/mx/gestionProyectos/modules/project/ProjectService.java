package utez.edu.mx.gestionProyectos.modules.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.employee.Employee;
import utez.edu.mx.gestionProyectos.modules.employee.EmployeeRepository;
import utez.edu.mx.gestionProyectos.modules.employee.EmployeeService;
import utez.edu.mx.gestionProyectos.modules.phase.Phase;
import utez.edu.mx.gestionProyectos.modules.phase.PhaseRepository;
import utez.edu.mx.gestionProyectos.modules.project.DTO.ProjectDTO;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PhaseRepository phaseRepository;


    //Transformar proyectos a DTO para traer solo los datos necesarios del empleado
    public ProjectDTO transformProjectDTO(Project p){
        return new ProjectDTO(
                p.getId(),
                p.getName(),
                p.getIdentifier(),
                p.getStartDate(),
                p.getFinishDate(),
                p.getEstimatedDate(),
                p.isStatus(),
                employeeService.transformEmployeesDTO(p.getEmployees()),
                projectRepository.currentPhase(p.getId())
        );
    }

    //Traer todos los proyectos
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        List<ProjectDTO> list = new ArrayList<>();
        String message = "";
        if (projectRepository.findAll().isEmpty()) {
            message = "Aún no hay registros";
        } else {
            message = "Operación exitosa";
            for(Project p: projectRepository.findAll()){
                list.add(transformProjectDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }

    //Encontrar proyecto por nombre de usuario
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllByEmployee(String username) {
        List<ProjectDTO> list = new ArrayList<>();
        String message = "";
        if (projectRepository.findProjectByEmployee(username).isEmpty()) {
            message = "Aún no hay registros";
        } else {
            message = "Operación exitosa";
            for(Project p: projectRepository.findProjectByEmployee(username)){
                list.add(transformProjectDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK", 200, list);
    }

    //Encontrar proyecto por ID
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long id) {
        ProjectDTO dto = null;
        Project found = projectRepository.findById(id);
        String message = "";
        if (found == null) {
            return customResponseEntity.get404Response();
        } else {
            message = "Operación exitosa";
            dto = transformProjectDTO(found);
        }
        return customResponseEntity.getOkResponse(message, "OK", 200,dto);
    }

    //Guardar proyecto (Aqui se debe de validar el rol del empleado y si es APs o RD solo puede estar asignado a un proyecto)
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Project project) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es-MX"));
        Date currentDate = new Date();

        if(project.getStartDate()==null){
            project.setStartDate(sdf.format(currentDate));
        }
        project.setStatus(false);

        // Validación para que la fecha estimada no sea menor de 4 meses

        try {
            // Convertir la fecha estimada de String a Date
            Date EstimatedDate = sdf.parse(project.getEstimatedDate());  // Convierte la fecha estimada a Date
            Date startDate = sdf.parse(project.getStartDate());
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.MONTH, 4);

            // Verificamos si la fecha estimada es menor a la fecha actual más 4 meses
            if (EstimatedDate.before(cal.getTime())) {
                return customResponseEntity.get400Response();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return customResponseEntity.get400Response();
        }


        // Crear una lista para almacenar empleados completos
        List<Employee> completeEmployees = new ArrayList<>();

        // Cargar empleados por ID desde el servicio
        for (Employee employee : project.getEmployees()) {
            Employee fullEmployee = employeeRepository.findById(employee.getId());
            if (fullEmployee != null) {
                completeEmployees.add(fullEmployee);
            } else {
                return customResponseEntity.get404Response();
            }
        }

        for (Employee employee : completeEmployees) {
            if ((employee.getRol().getId() == 3 || employee.getRol().getId() == 4) && employee.isStatus()) {
                return customResponseEntity.get400Response();
            } else {
                employee.setStatus(true);
            }
        }
        List<Phase> phases = new ArrayList<>();
        phases.add(phaseRepository.findById(1));

        project.setEmployees(completeEmployees);
        project.setPhases(phases);

        try {
            projectRepository.save(project);
            return customResponseEntity.getOkResponse(
                    "Registro exitoso",
                    "CREATED",
                    201,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Project project) {
        Project found= projectRepository.findById(project.getId());
        if(found==null){
            return customResponseEntity.get404Response();
        }

        project.setStartDate(found.getStartDate());
        project.setStatus(found.isStatus());
        project.setPhases(found.getPhases());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es-MX"));
        try {
            Date startDate= sdf.parse(found.getStartDate());
            Date EstimatedDate = sdf.parse(project.getEstimatedDate());  // Convierte la fecha estimada a Date

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.MONTH, 4);

            if (EstimatedDate.before(cal.getTime())) {
                return customResponseEntity.get400Response();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return customResponseEntity.get400Response();
        }

        for(Employee emp: found.getEmployees()){
            emp.setStatus(false);
        }

        List<Employee> completeEmployees = new ArrayList<>();

        for (Employee employee : project.getEmployees()) {
            Employee fullEmployee = employeeRepository.findById(employee.getId());
            if (fullEmployee != null) {
                completeEmployees.add(fullEmployee);
            } else {
                return customResponseEntity.get404Response();
            }
        }

        for (Employee employee : completeEmployees) {
            if ((employee.getRol().getId() == 3 || employee.getRol().getId() == 4) && employee.isStatus()) {
                return customResponseEntity.get400Response();
            } else {
                employee.setStatus(true);
            }
        }

        project.setEmployees(completeEmployees); // Asigna los empleados completos al proyecto


        try {
            projectRepository.save(project);
            return customResponseEntity.getOkResponse(
                    "Actualización exitosa",
                    "OK",
                    200,
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return customResponseEntity.get400Response();
        }
    }


    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> finishProject(long id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es-MX"));
        Date currentDate = new Date();

        Project found = projectRepository.findById(id);
        if (found == null) {
            return customResponseEntity.get404Response();
        } else {
            boolean faseCierre=false;
            for(Phase p: found.getPhases()){
                if(p.getId()==5){
                    faseCierre=true;
                }
        }

            if(faseCierre){
                try {
                    found.setFinishDate(sdf.format(currentDate));
                    found.setStatus(true); // El proyecto está finalizado, su estatus es true
                    projectRepository.finishProject(
                            found.isStatus(),
                            found.getFinishDate(),
                            found.getId()
                    );

                    // Iterar sobre los empleados del proyecto
                    for (Employee e : found.getEmployees()) {
                        if (e.getId() == 2) {
                            boolean hasActiveProjects = false;

                            // Verificamos si el empleado tiene otros proyectos activos (estatus false)
                            for (Project p : e.getProjects()) {
                                // Si encontramos un proyecto activo (estatus false) diferente al actual
                                if (!p.isStatus() && p.getId() != found.getId()) {
                                    hasActiveProjects = true;
                                    break; // Si tiene un proyecto activo, no cambiamos su estado
                                }
                            }

                            // Si no tiene otros proyectos activos, cambiamos su estado a false
                            if (!hasActiveProjects) {
                                e.setStatus(false);
                            }
                        } else {
                            // Si el empleado no tiene el id 2, cambiamos su estado a false
                            e.setStatus(false);
                        }
                    }

                    return customResponseEntity.getOkResponse("Operación exitosa", "OK", 200, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return customResponseEntity.get400Response();
                }
            }else{
                return customResponseEntity.get400Response();
            }
        }
    }
}

package utez.edu.mx.gestionProyectos.modules.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.gestionProyectos.modules.employee.EmployeeService;
import utez.edu.mx.gestionProyectos.modules.project.DTO.ProjectDTO;
import utez.edu.mx.gestionProyectos.utils.CustomResponseEntity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomResponseEntity customResponseEntity;

    @Autowired
    private EmployeeService employeeService;

    //Transformar proyectos a DTO para traer solo los datos necesarios del empleado
    public ProjectDTO transformProjectDTO(Project p){
        return new ProjectDTO(
                p.getId(),
                p.getName(),
                p.getIdentifier(),
                p.getStartDate(),
                p.getFinishDate(),
                p.getStatus(),
                p.getEmployees(), //Cambiar esta parte por employee DTO
                p.getPhases()
        );
    }

    //Traer todos los proyectos
    @Transactional(readOnly=true)
    public ResponseEntity<?> findAll(){
        List<ProjectDTO> list = new ArrayList<>();
        String message="";
        if(projectRepository.findAll().isEmpty()){
            message="Aún no hay registros";
        }else{
            message="Operación exitosa";
            for(Project p:projectRepository.findAll()){
                list.add(transformProjectDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(list.isEmpty()? "Aún no hay registros":"Operación exitosa", "OK", 200, list);
    }

    /*@Transactional(readOnly = true)
    public ResponseEntity<?> findProjectsByEmployee(long idEmployee){
        List<ProjectDTO> list= new ArrayList<>();
        String message="";
        if(projectRepository.findAllByEmployees(idEmployee).isEmpty()){
            message="Aún no hay registros";
        }else{
            message="Operación exitosa";
            for(Project p: projectRepository.findAllByEmployees(idEmployee)){
                list.add(transformProjectDTO(p));
            }
        }
        return customResponseEntity.getOkResponse(message, "OK",200,list);
    }*/

    //Encontrar Proyecto por Id
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(long id){
        ProjectDTO dto = null;
        Project found = projectRepository.findById(id);
        String message="";
        if(found==null){
            return customResponseEntity.get404Response();
        }else{
            message= "Operación exitosa";
            dto = transformProjectDTO(found);
        }
        return customResponseEntity.getOkResponse(message, "OK",200,dto);
    }

    //Guardar Proyectos
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> save(Project project) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es-MX"));
        Date currentDate = new Date();
        project.setStartDate(sdf.format(currentDate));
        project.setStatus(0);

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

    //Actualizar Proyectos
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> update(Project project){
        if(projectRepository.findById(project.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                projectRepository.save(project);
                return customResponseEntity.getOkResponse(
                        "Actualización exitosa",
                        "OK",
                        200,
                        null);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return customResponseEntity.get400Response();
            }
        }
    }

    //Finalizar Proyecto
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<?> finishProject(Project project){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es-MX"));
        Date currentDate = new Date();
        if(projectRepository.findById(project.getId())==null){
            return customResponseEntity.get404Response();
        }else{
            try{
                project.setFinishDate(sdf.format(currentDate));
                project.setStatus(1);
                projectRepository.finishProject(
                        project.getStatus(),
                        project.getFinishDate(),
                        project.getId()
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

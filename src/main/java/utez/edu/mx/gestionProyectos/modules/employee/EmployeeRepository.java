package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.gestionProyectos.modules.employee.DTO.EmployeeDTO;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Employee findById(long id);

    Employee save(Employee employee);

    @Modifying
    @Query(value = "UPDATE employee SET status =:status WHERE id =:idEmployee", nativeQuery = true)
    void changeEmployeeStatus(
            @Param("status") boolean status,
            @Param("idEmployee") long idEmployee
    );

    @Query(value = "SELECT * FROM employee WHERE password = :password AND (email = :username OR username = :username);", nativeQuery = true)
    Employee findByPasswordAndEmailOrUsername(@Param("password") String password, @Param("username") String username);

    Employee findByUsername(String username);

    @Modifying
    @Query(value="UPDATE employee SET name=:name, surname=:surname, lastname=:lastname, email=:email WHERE id=:idEmployee", nativeQuery = true)
    void updateEmployee(@Param("name")String name, @Param("surname")String surname, @Param("lastname")String lastname,@Param("email")String email, @Param("idEmployee")long idEmployee);

    @Modifying
    @Query(value="DELETE FROM project_has_employees WHERE id_employee=:idEmployee", nativeQuery = true)
    void deleteEmployeeProjects(@Param("idEmployee")long idEmployee);

    @Modifying
    @Query(value="DELETE FROM Employee WHERE id=:idEmployee",nativeQuery = true)
    void delete(@Param("idEmployee")long idEmployee);

    @Query(value="SELECT * FROM employee WHERE id_rol =:idRol AND (id_rol NOT IN (3, 4) OR status = false);",nativeQuery = true)
    List<Employee> findEmployeeByRolForCreate(@Param("idRol")int idRol);
}
package utez.edu.mx.gestionProyectos.modules.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Employee findById(long id);

    Employee save(Employee employee);

    @Modifying
    @Query(value = "UPDATE employee SET status = :status WHERE id = :idEmployee", nativeQuery = true)
    void changeEmployeeStatus(
            @Param("status") boolean status,
            @Param("idEmployee") long idEmployee
    );

    @Query(value = "SELECT * FROM employee WHERE password = :password AND (email = :username OR username = :username);", nativeQuery = true)
    Employee findByPasswordAndEmailOrUsername(@Param("password") String password, @Param("username") String username);

    Employee findByUsername(String username);

}
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

}
package utez.edu.mx.gestionProyectos.modules.phase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.gestionProyectos.modules.employee.Employee;

@Repository
public interface PhaseRepository extends JpaRepository<Employee, Long> {

}
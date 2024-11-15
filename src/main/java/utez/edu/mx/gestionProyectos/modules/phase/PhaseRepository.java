package utez.edu.mx.gestionProyectos.modules.phase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase,Integer> {
    Phase findById(int id);
}

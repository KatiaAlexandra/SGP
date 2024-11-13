package utez.edu.mx.gestionProyectos.modules.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepositoy extends JpaRepository<Rol, Integer> {
    List<Rol> findAll();
}

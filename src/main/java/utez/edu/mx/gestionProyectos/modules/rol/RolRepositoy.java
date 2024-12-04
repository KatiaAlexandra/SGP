package utez.edu.mx.gestionProyectos.modules.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepositoy extends JpaRepository<Rol, Integer> {
    List<Rol> findAll();

    @Query(value = "SELECT id_rol FROM employee WHERE username =:username;", nativeQuery = true)
    int findRolByEmployee(@Param("username") String username);
}

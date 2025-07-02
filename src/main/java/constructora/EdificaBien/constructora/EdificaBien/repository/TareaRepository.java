package constructora.EdificaBien.constructora.EdificaBien.repository;

import constructora.EdificaBien.constructora.EdificaBien.model.Proyecto;
import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByProyectoId(Integer proyectoId);
    List<Tarea> findByEstado(String estado);

}
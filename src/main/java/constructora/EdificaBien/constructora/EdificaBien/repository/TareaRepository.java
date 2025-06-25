package constructora.EdificaBien.constructora.EdificaBien.repository;

import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    Tarea findByEstado(String estado);
    Tarea findByProyecto(Integer proyectoId);

}
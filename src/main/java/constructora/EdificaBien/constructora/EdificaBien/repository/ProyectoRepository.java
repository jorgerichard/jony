package constructora.EdificaBien.constructora.EdificaBien.repository;

import constructora.EdificaBien.constructora.EdificaBien.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    Proyecto findByNombre(String nombre);
    Proyecto findByFechas(Date fecha);

}

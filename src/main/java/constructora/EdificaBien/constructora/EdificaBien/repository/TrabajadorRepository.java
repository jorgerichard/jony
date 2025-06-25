package constructora.EdificaBien.constructora.EdificaBien.repository;

import constructora.EdificaBien.constructora.EdificaBien.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
}

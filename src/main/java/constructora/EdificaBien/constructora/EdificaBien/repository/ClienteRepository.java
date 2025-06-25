package constructora.EdificaBien.constructora.EdificaBien.repository;

import constructora.EdificaBien.constructora.EdificaBien.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

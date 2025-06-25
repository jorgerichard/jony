package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Proyecto;
import constructora.EdificaBien.constructora.EdificaBien.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> findAll(){
        return proyectoRepository.findAll();
    }

    public Proyecto findByNombre(String nombre){
        return proyectoRepository.findByNombre(nombre);
    }

    public Proyecto findByDateRange(Date fecha){
        return proyectoRepository.findByFechas(fecha);
    }

}

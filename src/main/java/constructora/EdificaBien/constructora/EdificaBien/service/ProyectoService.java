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

    public List<Proyecto> findByAll(){
        return proyectoRepository.findAll();
    }
    public Proyecto findById(Integer id){
        return proyectoRepository.findById(id).orElse(null);
    }

    public Proyecto findByNombre(String nombre){
        return proyectoRepository.findByNombre(nombre);
    }

    public List<Proyecto> findProyectosByFechaInicio(Date startDate, Date endDate) {
        return proyectoRepository.findByFechaInicioBetween(startDate, endDate);
    }

    public void deleteProyecto(Integer id) {
        proyectoRepository.deleteById(id);
    }

    public Proyecto guardarProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }
}

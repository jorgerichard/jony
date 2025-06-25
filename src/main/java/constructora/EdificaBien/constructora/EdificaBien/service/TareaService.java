package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import constructora.EdificaBien.constructora.EdificaBien.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findByAll(){
        return tareaRepository.findAll();
    }

    public Tarea findByEstado(String estado){
        return tareaRepository.findByEstado(estado);
    }

    public Tarea findByProyecto(Integer proyectoId){
        return tareaRepository.findByProyecto(proyectoId);
    }

}

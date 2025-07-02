package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Proyecto;
import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import constructora.EdificaBien.constructora.EdificaBien.repository.ProyectoRepository;
import constructora.EdificaBien.constructora.EdificaBien.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Tarea> findByAll(){
        return tareaRepository.findAll();
    }

    public Tarea findById(Integer id){
        return tareaRepository.findById(id).orElse(null);
    }

    public List<Tarea> findByEstado(String estado) {
        return tareaRepository.findByEstado(estado);
    }

    public List<Tarea> findByProyecto(Integer proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public void deleteTarea(Integer id){
        tareaRepository.deleteById(id);
    }

}

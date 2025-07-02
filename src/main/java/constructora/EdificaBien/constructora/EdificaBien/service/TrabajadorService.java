package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Trabajador;
import constructora.EdificaBien.constructora.EdificaBien.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Trabajador buscarTrabajadorPorEspecialidad(String especialidad) {
        return trabajadorRepository.findByEspecialidad(especialidad);
    }

    public Trabajador guardarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public void eliminarTrabajador(Integer id) {
        trabajadorRepository.deleteById(id);
    }

    public Trabajador buscarTrabajadorPorId(Integer id){ return trabajadorRepository.findById(id).orElse(null);}

    public Trabajador buscarPorRut(String rut){return trabajadorRepository.findByRut(rut);}
}
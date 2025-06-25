package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Cliente;
import constructora.EdificaBien.constructora.EdificaBien.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository  clienteRepository;


    // obteniendo todos los cliente
    public List<Cliente> listaCliente() {
        return clienteRepository.findAll();
    }

    public Cliente buscarCliente(Integer id){
        return  clienteRepository.findById(id).orElse(null);
    }

    // borrar un cliente
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    // Guardar o actualizar cliente
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // creando un nuevo cliente
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //Buscar por Rut
    public Cliente buscarClienteRut(String rut){
        return clienteRepository.findByRut(rut);
    }

    //Buscar por Correo
    public Cliente buscarClienteCorreo(String correoElectronico){
        return clienteRepository.findByCorreoElectronico(correoElectronico);
    }

}

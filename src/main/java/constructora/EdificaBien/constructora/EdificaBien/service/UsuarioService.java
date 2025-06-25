package constructora.EdificaBien.constructora.EdificaBien.service;

import constructora.EdificaBien.constructora.EdificaBien.model.Usuario;
import constructora.EdificaBien.constructora.EdificaBien.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository  usuarioRepository;


    // obteniendo todos los asistentes
    public List<Usuario> listaUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Integer id){
        return  usuarioRepository.findById(id).orElse(null);
    }

    // borrar un usuario
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // Guardar o actualizar usuario
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar usuario por ID
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // creando un nuevo usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Buscar por Rut
    public Usuario buscarUsuarioRut(String rut){
        return usuarioRepository.findByRut(rut);
    }

    //Buscar por Correo
    public Usuario buscarUsuarioCorreo(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

}
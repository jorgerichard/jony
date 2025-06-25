package constructora.EdificaBien.constructora.EdificaBien.controller;


import constructora.EdificaBien.constructora.EdificaBien.model.Usuario;
import constructora.EdificaBien.constructora.EdificaBien.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Servicio de Usuarios", description = "Servicio para poder consultar informacion de los usuarios de la plataforma")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/v1/usuario")
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    public ResponseEntity<?> listarUsuario() {
        return ResponseEntity.status(200).body(usuarioService.listaUsuario());
    }

    @DeleteMapping("/api/v1/usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscarUsuario(id);
        if(usuario == null){
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        usuarioService.deleteUsuario(id);
        return ResponseEntity.status(200).body("Usuario eliminado");
    }

    @PutMapping("/api/v1/usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.findById(id);
        if (usuarioExistente == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(200).body(usuarioActualizado);
    }

    @GetMapping("/api/v1/usuario/buscar-rut/{rut}")
    public ResponseEntity<?>buscarUsuarioPorRut(@PathVariable String rut){
        Usuario usuario = usuarioService.buscarUsuarioRut(rut);
        return  ResponseEntity.status(200).body(usuario);
    }


    @PostMapping("/api/v1/usuario")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioService.guardarUsuario(usuario);

        return ResponseEntity.status(201).body(usuarioCreado);
    }

    @GetMapping("/api/v1/usuario/buscar-correo/{correoElectronico}")
    public ResponseEntity<?>buscarAsistentePorCorreo(@PathVariable String correoElectronico){
        Usuario usuario = usuarioService.buscarUsuarioCorreo(correoElectronico);
        return  ResponseEntity.status(200).body(usuario);
    }


}
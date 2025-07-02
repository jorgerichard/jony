package constructora.EdificaBien.constructora.EdificaBien.controller;


import constructora.EdificaBien.constructora.EdificaBien.model.Cliente;
import constructora.EdificaBien.constructora.EdificaBien.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cliente") // <-- ruta base para mis servicios de carreras
@Tag(name = "Servicios de Clientes", description = "Servicios para poder consultar informacion de los clientes en la plataforma")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/api/v1/cliente")
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes")
    public ResponseEntity<?> listarCliente() {
        return ResponseEntity.status(200).body(clienteService.listaCliente());
    }

    @DeleteMapping("/api/v1/cliente/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarCliente(id);
        if(cliente == null){
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        clienteService.deleteCliente(id);
        return ResponseEntity.status(200).body("Cliente eliminado");
    }

    @PutMapping("/api/v1/cliente/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteService.findById(id);
        if (clienteExistente == null) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        cliente.setId(id);
        Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
        return ResponseEntity.status(200).body(clienteActualizado);
    }

    @GetMapping("/api/v1/cliente/buscar-rut/{rut}")
    public ResponseEntity<?>buscarClientePorRut(@PathVariable String rut){
        Cliente cliente = clienteService.buscarClienteRut(rut);
        return  ResponseEntity.status(200).body(cliente);
    }


    @PostMapping("/api/v1/cliente")
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.guardarCliente(cliente);

        return ResponseEntity.status(201).body(clienteCreado);
    }

    @GetMapping("/api/v1/cliente/buscar-correo/{correoElectronico}")
    public ResponseEntity<?>buscarAsistentePorCorreo(@PathVariable String correoElectronico){
        Cliente cliente = clienteService.buscarClienteCorreo(correoElectronico);
        return  ResponseEntity.status(200).body(cliente);
    }
}
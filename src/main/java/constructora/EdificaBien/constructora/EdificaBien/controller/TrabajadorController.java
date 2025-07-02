package constructora.EdificaBien.constructora.EdificaBien.controller;


import constructora.EdificaBien.constructora.EdificaBien.model.Trabajador;
import constructora.EdificaBien.constructora.EdificaBien.service.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/trabajador")
@Tag(name = "Servicios de Trabajadores", description = "Servicios para poder consultar información de los trabajadores en la plataforma")
public class TrabajadorController {


    @Autowired
    private TrabajadorService trabajadorService;


    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores", description = "Obtiene una lista de todos los trabajadores")
    public ResponseEntity<?> listarTrabajadores() {
        return ResponseEntity.status(200).body(trabajadorService.listarTrabajadores());
    }


    @GetMapping("/{especialidad}")
    @Operation(summary = "Obtener un trabajador por su especialidad", description = "Obtiene un trabajador por su especialidad")
    public ResponseEntity<?> obtenerTrabajadorPorEspecialidad(@PathVariable String especialidad) {
        Trabajador trabajador = trabajadorService.buscarTrabajadorPorEspecialidad(especialidad);
        if (trabajador == null) {
            return ResponseEntity.status(404).body("Trabajador no encontrado");
        }
        return ResponseEntity.status(200).body(trabajador);
    }

    @GetMapping("/rut/{rut}")
    @Operation(summary = "Obtener trabajadores por su rut", description = "Obten la información de los trabajadores utilizando su rut.")
    public ResponseEntity<?> obtenerTrabajadorPorRut(@PathVariable String rut){
        Trabajador trabajador = trabajadorService.buscarPorRut(rut);
        if (trabajador==null){
            return ResponseEntity.status(404).body("Trabajador no encontrado");
        }
        return ResponseEntity.status(200).body(trabajador);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo trabajador", description = "Crea un nuevo trabajador")
    public ResponseEntity<?> crearTrabajador(@RequestBody Trabajador trabajador) {
        Trabajador trabajadorCreado = trabajadorService.guardarTrabajador(trabajador);
        return ResponseEntity.status(201).body(trabajadorCreado);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un trabajador", description = "Actualiza un trabajador existente")
    public ResponseEntity<?> actualizarTrabajador(@PathVariable Integer id, @RequestBody Trabajador trabajador) {
        Trabajador trabajadorExistente = trabajadorService.buscarTrabajadorPorId(id);
        if (trabajadorExistente == null) {
            return ResponseEntity.status(404).body("Trabajador no encontrado");
        }
        Trabajador trabajadorActualizado = trabajadorService.guardarTrabajador(trabajador);
        return ResponseEntity.status(200).body(trabajadorActualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un trabajador", description = "Elimina un trabajador existente")
    public ResponseEntity<?> eliminarTrabajador(@PathVariable Integer id) {
        Trabajador trabajador = trabajadorService.buscarTrabajadorPorId(id);
        if (trabajador == null) {
            return ResponseEntity.status(404).body("Trabajador no encontrado");
        }
        trabajadorService.eliminarTrabajador(id);
        return ResponseEntity.status(200).body("Trabajador eliminado");
    }
}

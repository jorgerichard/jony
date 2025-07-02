package constructora.EdificaBien.constructora.EdificaBien.controller;

import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import constructora.EdificaBien.constructora.EdificaBien.model.Trabajador;
import constructora.EdificaBien.constructora.EdificaBien.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@Tag(name = "Servicio de Tareas", description = "Aquí puedes obtener información disponibles de las tareas en éste proyecto.")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    @Operation(summary = "Listar todas las tareas.", description = "Aquí puedes listar todas las tareas disponibles.")
    public ResponseEntity<?> listarTodasLasTareas(){
        return ResponseEntity.status(200).body(tareaService.findByAll());
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar por estado.", description = "Aquí puedes listar las tareas por su estado (EN PROCESO/FINALIZADO).")
    public ResponseEntity<?> listarPorEstado(@PathVariable String estado){
        return ResponseEntity.status(200).body(tareaService.findByEstado(estado));
    }

    @GetMapping("/proyecto/{proyecto_id}")
    @Operation(summary = "Listar tareas por ID de proyecto", description = "Retorna todas las tareas asociadas a un proyecto específico según su ID.")
    public ResponseEntity<List<Tarea>> listarPorProyecto(@PathVariable Integer proyecto_id) {
        List<Tarea> tareas = tareaService.findByProyecto(proyecto_id);
        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Tareas", description = "Aquí puedes actualizar la información de las tareas utilizando el id.")
    public ResponseEntity<?> actualizarTareas(@PathVariable Integer id, @RequestBody Tarea tarea){
        Tarea tareaExistente = tareaService.findById(id);
        if (tareaExistente == null) {
            return ResponseEntity.status(404).body("Tarea no encontrado");
        }
        Tarea tareaActualizada = tareaService.guardarTarea(tarea);
        return ResponseEntity.status(200).body(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tareas", description = "Aquí puedes eliminar tareas por su id.")
    public ResponseEntity<?> borrarTarea(@PathVariable Integer id){
        Tarea tarea = tareaService.findById(id);
        if (tarea == null){
            return ResponseEntity.status(404).body("No se pudo encontrar la tarea.");
        }
        tareaService.deleteTarea(id);
        return ResponseEntity.status(200).body("Se eliminó correctamente la tarea");
    }

    @PostMapping
    @Operation(summary = "Guardar tareas.", description = "Aquí puedes agregar tareas.")
    public ResponseEntity<?> guardarTarea(@RequestBody Tarea tarea){
        tareaService.guardarTarea(tarea);
        return ResponseEntity.status(201).body(tarea);
    }

}

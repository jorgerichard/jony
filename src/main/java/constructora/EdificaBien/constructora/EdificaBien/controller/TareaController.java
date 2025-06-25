package constructora.EdificaBien.constructora.EdificaBien.controller;

import constructora.EdificaBien.constructora.EdificaBien.model.Tarea;
import constructora.EdificaBien.constructora.EdificaBien.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/")
    @Operation(summary = "Listar todas las tareas.", description = "Aquí puedes listar todas las tareas disponibles.")
    public ResponseEntity<?> listarTodasLasTareas(){
        return ResponseEntity.status(200).body(tareaService.findByAll());
    }

    @GetMapping("/{estado}")
    @Operation(summary = "Listar por estado.", description = "Aquí puedes listar las tareas por su estado (EN PROCESO/FINALIZADO).")
    public ResponseEntity<?>listarPorEstado(@PathVariable String estado){
        return ResponseEntity.status(200).body(tareaService.findByEstado(estado));
    }

    @GetMapping("/{proyecto_id}")
    @Operation(summary = "Listar por id de proyecto.", description = "Aquí puedes listar tareas por el id de su proyecto.")
    public ResponseEntity<?>listarPorProyecto(@PathVariable Integer proyecto_id){
        return ResponseEntity.status(200).body(tareaService.findByProyecto(proyecto_id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Eliminar tareas", description = "Aqui puedes eliminar tareas por su id.")
    public ResponseEntity<?>borrarTarea(@PathVariable Integer id){
        Tarea tarea = tareaService.findById(id);
        if (tarea==null){
            return ResponseEntity.status(200).body("No se pudo encontrar la tarea.");
        }
        tareaService.deleteTarea(id);
        return ResponseEntity.status(200).body("Se elimino correctamente la tarea");
    }

    @GetMapping("/")
    @Operation(summary = "Guardar tareas.", description = "Aquí puedes agragar tareas.")
    public ResponseEntity<?>guardarTare(@RequestBody Tarea tarea){
        tareaService.guardarTarea(tarea);
        return ResponseEntity.status(200).body(tarea);
    }

}

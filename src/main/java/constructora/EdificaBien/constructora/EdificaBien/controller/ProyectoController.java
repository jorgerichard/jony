package constructora.EdificaBien.constructora.EdificaBien.controller;

import constructora.EdificaBien.constructora.EdificaBien.model.Proyecto;
import constructora.EdificaBien.constructora.EdificaBien.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/proyectos")
@Tag(name = "Proyectos", description = "Servicios para consultar la información de los proyectos disponibles en la plataforma")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/")
    @Operation(summary = "Listar todos los proyectos", description = "Aquí puedes listar todos los proyectos disponibles.")
    public ResponseEntity<?> listarProyectos(){
        return ResponseEntity.status(200).body(proyectoService.findByAll());
    }

    @GetMapping("/{nombre}")
    @Operation(summary = "Listar proyectos por nombre", description = "Aquí puedes listar los proyectos por su nombre.")
    public ResponseEntity<?> listarProyectosPorNombre(@PathVariable String nombre){
        Proyecto proyecto = proyectoService.findByNombre(nombre);
        if (proyecto == null) {
            return ResponseEntity.status(400).body("No se pudo encontrar el proyecto");
        }
        return ResponseEntity.status(200).body(proyectoService.findByNombre(nombre));
    }

    @GetMapping("/fechas")
    @Operation(summary = "Listar proyectos por un rango de fecha.", description = "Aquí puedes listar proyectos en un rango de fecha.")
    public ResponseEntity<List<Proyecto>> getProyectosByFechaInicio(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Proyecto> proyectos = proyectoService.findProyectosByFechaInicio(startDate, endDate);
        return ResponseEntity.status(200).body(proyectos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Borrar proyectos.", description = "Aquí puedes borrar proyectos con su id.")
    public ResponseEntity<?> deleteProyectos(@PathVariable Integer id){
        Proyecto proyecto = proyectoService.findById(id);
        if (proyecto==null){
            return ResponseEntity.status(400).body("El proyecto no fue encontrado.");
        }
        proyectoService.deleteProyecto(id);
        return ResponseEntity.status(200).body("El proyecto fue correctamente eliminado");
    }

    @GetMapping("/")
    @Operation(summary = "Guardar proyectos.", description = "Aquí podrás agregar proyectos.")
    public ResponseEntity<?> guardarProyectos(@RequestBody Proyecto proyecto){
        proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.status(200).body(proyecto);
    }

}

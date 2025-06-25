package constructora.EdificaBien.constructora.EdificaBien;

import constructora.EdificaBien.constructora.EdificaBien.model.*;
import constructora.EdificaBien.constructora.EdificaBien.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            if (!generarUsuarios(5)){
                throw new Exception("No se puede generar usuarios.");
            }
            if (!generarCliente(10)){
                throw new Exception("No se puede generar clientes.");
            }
            if (!generarTrabajadores(10)){
                throw new Exception("No se puede generar trabajadores.");
            }
            if (!crearProyectos(5)){
                throw new Exception("No se puede crear proyectos.");
            }
            if (!crearTarea(20)){
                throw new Exception("No se puede crear tareas.");
            }
        } catch (Exception e) {
            return;
        }
    }

    private boolean generarUsuarios(int cantidadUsuarios){
        Faker faker = new Faker();

        try {
            for (int i = 0; i < cantidadUsuarios; i++) {
                Usuario usuario = new Usuario();
                usuario.setRut(faker.idNumber().valid());
                usuario.setNombreCompleto(faker.name().fullName());
                usuario.setCorreoElectronico(faker.internet().emailAddress());
                usuarioRepository.save(usuario);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean generarCliente(int cantidadCliente){
        Faker faker = new Faker();
        Random random = new Random();

        List<Usuario> usuarios = usuarioRepository.findAll();

        for (int i = 0; i < cantidadCliente; i++) {
            Cliente cliente = new Cliente();
            cliente.setRut(faker.idNumber().valid());
            cliente.setNombreCompleto(faker.name().fullName());
            cliente.setCorreoElectronico(faker.internet().emailAddress());
            cliente.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            clienteRepository.save(cliente);
        }
        return true;
    }

    private boolean generarTrabajadores(int cantidadTrabajadores){
        Faker faker = new Faker();
        Random random = new Random();

        List<Usuario> usuarios = usuarioRepository.findAll();

        try{
            for (int i = 0; i < cantidadTrabajadores; i++) {
                Trabajador trabajador = new Trabajador();
                trabajador.setRut(faker.idNumber().valid());
                trabajador.setNombreCompleto(faker.name().fullName());
                trabajador.setEspecialidad(faker.job().title());
                trabajador.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
                trabajadorRepository.save(trabajador);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean crearProyectos(int cantidadProyectos){
        Faker faker = new Faker();
        Random random = new Random();

        List<Cliente> clientes = clienteRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();

        try {
            for (int i = 0; i < cantidadProyectos; i++) {
                Proyecto proyecto = new Proyecto();
                proyecto.setNombre(faker.app().name() + "-" + i);
                proyecto.setDescripcion(faker.lorem().sentence());
                proyecto.setFechaInicio(randomDate(-30, 0));
                proyecto.setFechaFin(randomDate(1, 60));
                proyecto.setCliente(clientes.get(random.nextInt(clientes.size())));
                proyecto.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
                proyectoRepository.save(proyecto);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean crearTarea(int cantidadTareas){
        Faker faker = new Faker();
        Random random = new Random();

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        List<Trabajador> trabajadores = trabajadorRepository.findAll();

        try {
            for (int i = 0; i < cantidadTareas; i++) {
                Tarea tarea = new Tarea();
                tarea.setNombre("Tarea " + (i + 1) + ": " + faker.hacker().verb());
                tarea.setEstado(faker.options().option("EN PROCESO", "FINALIZADA"));
                tarea.setProyecto(proyectos.get(random.nextInt(proyectos.size())));
                tarea.setTrabajador(trabajadores.get(random.nextInt(trabajadores.size())));
                tarea.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
                tareaRepository.save(tarea);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Date randomDate(int minDaysFromToday, int maxDaysFromToday) {
        Calendar calendar = Calendar.getInstance();
        int days = new Random().nextInt(maxDaysFromToday - minDaysFromToday + 1) + minDaysFromToday;
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}

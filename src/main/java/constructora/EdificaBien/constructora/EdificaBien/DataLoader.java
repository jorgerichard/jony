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
        Faker faker = new Faker();
        Random random = new Random();

        // Crear usuarios
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setRut(faker.idNumber().valid());
            usuario.setNombreCompleto(faker.name().fullName());
            usuario.setCorreoElectronico(faker.internet().emailAddress());
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();

        // Crear clientes
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setRut(faker.idNumber().valid());
            cliente.setNombreCompleto(faker.name().fullName());
            cliente.setCorreoElectronico(faker.internet().emailAddress());
            cliente.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            clienteRepository.save(cliente);
        }

        // Crear trabajadores
        for (int i = 0; i < 10; i++) {
            Trabajador trabajador = new Trabajador();
            trabajador.setRut(faker.idNumber().valid());
            trabajador.setNombreCompleto(faker.name().fullName());
            trabajador.setEspecialidad(faker.job().title());
            trabajador.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            trabajadorRepository.save(trabajador);
        }

        List<Cliente> clientes = clienteRepository.findAll();
        List<Trabajador> trabajadores = trabajadorRepository.findAll();

        // Crear proyectos
        for (int i = 0; i < 10; i++) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(faker.app().name() + "-" + i);
            proyecto.setDescripcion(faker.lorem().sentence());
            proyecto.setFechaInicio(randomDate(-30, 0)); // Entre hace 30 días y hoy
            proyecto.setFechaFin(randomDate(1, 60));     // Entre mañana y 60 días más
            proyecto.setCliente(clientes.get(random.nextInt(clientes.size())));
            proyecto.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            proyectoRepository.save(proyecto);
        }

        List<Proyecto> proyectos = proyectoRepository.findAll();

        // Crear tareas
        for (int i = 0; i < 20; i++) {
            Tarea tarea = new Tarea();
            tarea.setNombre("Tarea " + (i + 1) + ": " + faker.hacker().verb());
            tarea.setEstado(faker.options().option("pendiente", "en progreso", "completado"));
            tarea.setProyecto(proyectos.get(random.nextInt(proyectos.size())));
            tarea.setTrabajador(trabajadores.get(random.nextInt(trabajadores.size())));
            tarea.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            tareaRepository.save(tarea);
        }
    }

    // Metodo para generar fechas aleatorias
    private Date randomDate(int minDaysFromToday, int maxDaysFromToday) {
        Calendar calendar = Calendar.getInstance();
        int days = new Random().nextInt(maxDaysFromToday - minDaysFromToday + 1) + minDaysFromToday;
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}

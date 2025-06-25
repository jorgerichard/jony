package constructora.EdificaBien.constructora.EdificaBien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column (length = 100, nullable = false)
    private String nombre;

    @Column (length = 30, nullable = false)
    private String estado;

    @Column (length = 100, nullable = false, unique = true)
    private String correoElectronico;

    @ManyToOne
    @JoinColumn (name = "proyecto_id", nullable = false)
    private Proyecto proyecto;


    @ManyToOne
    @JoinColumn (name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    private Usuario usuario;


}

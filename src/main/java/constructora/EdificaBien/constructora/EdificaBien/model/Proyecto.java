package constructora.EdificaBien.constructora.EdificaBien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "proyectos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (length = 100, nullable = false, unique = true, name = "Nombre Proyecto")
    private String nombre;

    @Column (length = 225, nullable = false, name = "Descripcion")
    private String descripcion;

    @Column(nullable = false, name = "Fecha de Inicio")
    private Date fechaInicio;

    @Column(nullable = false, name = "Fecha de termino")
    private Date fechaFin;

    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    private Usuario usuario;
}

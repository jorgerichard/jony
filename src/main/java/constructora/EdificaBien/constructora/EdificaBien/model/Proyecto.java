package constructora.EdificaBien.constructora.EdificaBien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @Column (length = 100, nullable = false, unique = true)
    private String nombre;

    @Column (length = 225, nullable = false)
    private String descripcion;

    private Date fechaInicio;
    private Date fechaFin;

    @Column (length = 100, nullable = false, unique = true)
    private String correoElectronico;

    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    private Usuario usuario;
}

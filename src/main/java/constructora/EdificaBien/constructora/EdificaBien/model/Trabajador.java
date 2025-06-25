package constructora.EdificaBien.constructora.EdificaBien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12, nullable = false)
    private String rut;

    @Column(length = 100, nullable = false)
    private String nombreCompleto;

    @Column (length = 50, nullable = false)
    private String especialidad;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false )
    private Usuario usuario;


}

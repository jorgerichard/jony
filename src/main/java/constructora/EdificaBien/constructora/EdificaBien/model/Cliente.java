package constructora.EdificaBien.constructora.EdificaBien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column (length = 12, nullable = false, name = "Rut")
        private String rut;

        @Column (length = 100, nullable = false, name = "Nombre Cliente")
        private String nombreCompleto;

        @Column (length = 100, nullable = false, unique = true,name = "Correo Electronico")
        private String correoElectronico;

        @ManyToOne
        @JoinColumn (name = "usuario_id", nullable = false)
         private Usuario usuario;
}

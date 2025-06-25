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

public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column (length = 12, nullable = false)
        private String rut;

        @Column (length = 100, nullable = false)
        private String nombreCompleto;

        @Column (length = 100, nullable = false, unique = true)
        private String correoElectronico;


        @ManyToOne
        @JoinColumn (name = "usuario_id", nullable = false)
         private Usuario usuario;




}

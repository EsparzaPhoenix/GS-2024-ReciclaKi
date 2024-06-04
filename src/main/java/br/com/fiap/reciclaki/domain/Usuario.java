package br.com.fiap.reciclaki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gs_usuario")
@SequenceGenerator(name="seq_usuario", sequenceName="seq_gs_usuario", allocationSize=1)
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_usuario")
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nm_usuario", nullable = false, length = 50)
    private String nome;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cd_endereco")
    private Endereco endereco;

}

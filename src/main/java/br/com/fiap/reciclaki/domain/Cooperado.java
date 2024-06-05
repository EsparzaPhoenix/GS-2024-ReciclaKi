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
@Table(name = "gs_cooperado")
@SequenceGenerator(name="seq_cooperado", sequenceName="seq_gs_cooperado", allocationSize=1)
public class Cooperado {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_cooperado")
    @Column(name = "cd_cooperado")
    private Long id;

    @Column(name = "nm_cooperado", nullable = false, length = 50)
    private String nome;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cd_endereco")
    private Endereco endereco;

    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="cd_cooperativa")
    private Cooperativa cooperativa;

}

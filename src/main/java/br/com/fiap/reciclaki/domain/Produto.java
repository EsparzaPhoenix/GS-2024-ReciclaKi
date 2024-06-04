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
@Table(name = "gs_produto")
@SequenceGenerator(name="seq_produto", sequenceName="seq_gs_produto", allocationSize=1)
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_produto")
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nm_produto", nullable = false, length = 50)
    private String nome;
}

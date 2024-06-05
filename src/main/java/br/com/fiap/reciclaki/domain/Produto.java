package br.com.fiap.reciclaki.domain;

import br.com.fiap.reciclaki.domain.enums.TipoMaterial;
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
    @Column(name = "cd_produto")
    private Long id;

    @Column(name = "tp_material",nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoMaterial tipoMaterial;

    @Column(name = "nm_produto", nullable = false, length = 50)
    private String nome;
}

package br.com.fiap.reciclaki.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="gs_cidade")
@SequenceGenerator(name="seq_cidade", sequenceName="seq_gs_cidade", allocationSize=1)
public class Cidade {

    @Id
    @Column(name="cd_cidade")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_cidade")
    private Long id;

    @Column(nullable=false, length = 100, name="nm_cidade")
    private String nome;

    @Column(length=2,nullable=false, name="ds_uf")
    private String uf;

    @Column(name="nr_ddd")
    private Integer ddd;

}

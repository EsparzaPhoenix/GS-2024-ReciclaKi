package br.com.fiap.reciclaki.domain;

import br.com.fiap.reciclaki.dto.cooperativa.AtualizacaoCooperativa;
import br.com.fiap.reciclaki.dto.cooperativa.CadastroCooperativaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="gs_cooperativa")
@SequenceGenerator(name="seq_cooperativa", sequenceName="seq_gs_cooperativa", allocationSize=1)
public class Cooperativa {

    @Id
    @Column(name = "cd_cooperativa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cooperativa")
    private Long id;

    @Column(name = "nm_cooperativa", nullable = false, length = 100)
    private String nome;

    @Column(name = "dsc_cooperativa", nullable = false, length = 100)
    private String descricao;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cd_endereco")
    private Endereco endereco;

    public Cooperativa(CadastroCooperativaDTO dto) {
        nome = dto.nome();
        descricao = dto.descricao();
        endereco = new Endereco(dto.endereco());
    }

    public void atualizar(AtualizacaoCooperativa dto) {
        if(dto.nome() != null)
            nome = dto.nome();
        if (dto.descricao() != null)
            descricao = dto.descricao();
    }
}

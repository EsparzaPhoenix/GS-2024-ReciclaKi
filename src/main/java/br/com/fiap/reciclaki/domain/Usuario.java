package br.com.fiap.reciclaki.domain;

import br.com.fiap.reciclaki.dto.usuario.AtualizacaoUsuarioDTO;
import br.com.fiap.reciclaki.dto.usuario.CadastroUsuarioDTO;
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
    @Column(name = "cd_usuario")
    private Long id;

    @Column(name = "email_usuario", nullable = false, length = 70)
    private String email;

    @Column(name = "nr_telefone", length = 11, nullable = false)
    private String numeroTelefone;

    @Column(name = "nm_usuario", nullable = false, length = 50)
    private String nome;

    @OneToOne
    @JoinColumn(name="cd_endereco")
    private Endereco endereco;

    public Usuario(CadastroUsuarioDTO dto) {
        email = dto.email();
        numeroTelefone = dto.numeroTelefone();
        nome = dto.nome();
        endereco = new Endereco(dto.endereco());
    }

    public void atualizar(AtualizacaoUsuarioDTO dto) {
        if (dto.email() != null)
            email = dto.email();
        if(dto.numeroTelefone() != null)
            numeroTelefone = dto.numeroTelefone();
        if(dto.nome() != null)
            nome = dto.nome();
    }

}

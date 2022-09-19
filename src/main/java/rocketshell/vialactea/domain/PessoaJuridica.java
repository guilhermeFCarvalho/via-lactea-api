package rocketshell.vialactea.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoa_juridica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridica extends Pessoa {
    private String razaoSocial;
    private String telefone;
    private String CNPJ;
    private String inscricaoEstadual;


    
}

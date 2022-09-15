package rocketshell.vialactea.domain;

import lombok.Data;

@Data
public class PessoaJuridica extends Pessoa {
    private String razaoSocial;
    private String telefone;
    private String CNPJ;
    private String inscricaoEstadual;


    
}

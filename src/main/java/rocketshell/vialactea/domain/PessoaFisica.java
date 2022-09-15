package rocketshell.vialactea.domain;

import lombok.Data;

@Data
public class PessoaFisica extends Pessoa{
    private String nome;
    private String sobrenome;
    private String CPF;
     
    
}

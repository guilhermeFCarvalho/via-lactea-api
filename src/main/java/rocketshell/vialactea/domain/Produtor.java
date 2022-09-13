package rocketshell.vialactea.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;


@Entity
@Data
public class Produtor extends Pessoa{

    private String cpf;
    private Date dataDeNascimento;
    private String senha;
    private List<Fazenda> fazendas;
    
    

    
}


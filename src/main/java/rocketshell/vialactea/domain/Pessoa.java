package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.Data;
import rocketshell.vialactea.base.BaseEntity;

@Data
@Entity
public class Pessoa extends BaseEntity {
    private String email;
    private String nome;
    private String sobrenome;
    private String telefone;
    
}

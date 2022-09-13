package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.Data;
import rocketshell.vialactea.base.BaseEntity;

@Data 
@Entity
public class Fazenda extends BaseEntity {
  
  private String nomeDaFazenda;
  private String telefone;
  private String car;
}

package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fazenda extends BaseEntity {
  
  private String nomeDaFazenda;
  private String telefone;
  private String car;
}

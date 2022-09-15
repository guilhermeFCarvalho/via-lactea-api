package rocketshell.vialactea.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Propriedade extends BaseEntity {

    private String car;
    private String telefone;
    private Fazenda fazenda;
    private Produtor produtor;
}

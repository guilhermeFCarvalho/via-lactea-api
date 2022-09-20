package rocketshell.vialactea.domain;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa extends BaseEntity {
    private List<Propriedade> propriedades;
    private String telefone;
}

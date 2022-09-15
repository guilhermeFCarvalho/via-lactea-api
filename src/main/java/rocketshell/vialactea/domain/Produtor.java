package rocketshell.vialactea.domain;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtor extends Pessoa{

    private Pessoa pessoa;
    private Usuario usuario;
    private List<Propriedade> propriedades;
    
    
}


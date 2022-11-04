package rocketshell.vialactea.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registro_coleta_de_leite")
public class RegistroDeColetaDeLeite extends BaseEntity{
    
	@Column(name = "data_da_coleta")
	private Date dataDaColeta;
	
	@ManyToOne
	@JoinColumn(name = "propriedade_id")
	private Propriedade propriedade;
	
	@Column(name = "quantidade_de_leite_coletado")
	private float quantidadeDeLeiteColetado;
	
	@ManyToMany
	@JoinTable(
			name = "registro_coleta_de_leite_animal",
		    joinColumns = @JoinColumn(name = "registro_coleta_de_leite_id"),
		    inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
	private List<Animal> animaisEnvolvidos;
}

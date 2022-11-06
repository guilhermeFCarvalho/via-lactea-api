package rocketshell.vialactea.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "gestao_animal")
public class GestaoAnimal extends BaseEntity{
	
	@Column(name = "quantidade_de_racao_por_dia")
	private float quantidadeDeRacaoPorDia;
	
	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

}

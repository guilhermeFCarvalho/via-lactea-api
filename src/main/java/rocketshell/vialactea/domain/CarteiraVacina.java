package rocketshell.vialactea.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketshell.vialactea.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carteira_vacina")
public class CarteiraVacina extends BaseEntity{
	
	private String lote;
	
	private Date data;
	
	private String fabricante;
	
	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToMany
	@JoinTable(
			name = "carteira_vacina_vacina",
		    joinColumns = @JoinColumn(name = "carteira_vacina_id"),
		    inverseJoinColumns = @JoinColumn(name = "vacina_id")
    )
	private List<Vacina> vacinas;
	

}

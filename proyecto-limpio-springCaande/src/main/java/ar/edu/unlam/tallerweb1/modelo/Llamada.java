package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
public class Llamada {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Double Duracion;
	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	private Central Central;
	//Metodos
	public Double getDuracion() {
		return Duracion;
	}
	public void setDuracion(Double duracion) {
		Duracion = duracion;
	}
	public Central getCentral() {
		return Central;
	}
	public void setCentral(Central central) {
		Central = central;
	}
	public Long getId(){
		return Id;
	}
	
}

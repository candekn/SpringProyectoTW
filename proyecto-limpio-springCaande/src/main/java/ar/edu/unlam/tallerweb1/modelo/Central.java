package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
public class Central {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Nombre;
	@OneToOne
	@Cascade(value = CascadeType.ALL)
	private Sucursal Sucursal;
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Long getId(){
		return Id;
	}
	public Sucursal getSucursal() {
		return Sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		Sucursal = sucursal;
	}
	
}

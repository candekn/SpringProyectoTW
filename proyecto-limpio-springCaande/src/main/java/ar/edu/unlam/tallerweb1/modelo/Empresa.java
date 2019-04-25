package ar.edu.unlam.tallerweb1.modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Empresa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private ArrayList<Sucursal> sucursales;
	
		
	public ArrayList<Sucursal> getSucursales(){
		return this.sucursales;
	}
	public void setSucursales(ArrayList<Sucursal> sucursales){
		this.sucursales = sucursales;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

}

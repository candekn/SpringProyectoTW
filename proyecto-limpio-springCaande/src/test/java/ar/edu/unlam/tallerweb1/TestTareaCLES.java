package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.modelo.Central;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Empresa;
import ar.edu.unlam.tallerweb1.modelo.Llamada;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class TestTareaCLES extends SpringTest {

	@Test
	@Transactional
	@Rollback(true)
	public void IngresoCentralLlamadaVerificacionOneToOne() {
		Central c1 = new Central();
		c1.setNombre("Central uno");
		Llamada l1 = new Llamada();
		l1.setDuracion(1.6);
		l1.setCentral(c1);
		getSession().save(c1);
		getSession().save(l1);
		Llamada l2 = getSession().get(Llamada.class, l1.getId());
		assertThat(l2.getCentral().getId()).isEqualTo(c1.getId());

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testCentralSucursalDeleteEnCascada() {
		Central c1 = new Central();
		c1.setNombre("Nemooow");
		Sucursal s1 = new Sucursal();
		s1.setCalle("Wallaby");
		s1.setNumero(42);
		c1.setSucursal(s1);
		getSession().save(c1);
		getSession().save(s1);
		getSession().delete(c1);
		Central s2 = getSession().get(Central.class, s1.getId());
		assertThat(s2).isNull();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testCreaRegistroDeEmpresaAutomaticamenteCascade() {
		Empresa e1 = new Empresa();
		e1.setNombre("Empresa1");
		Sucursal s1 = new Sucursal();
		s1.setCalle("Cabildo");
		s1.setNumero(500);
		s1.setEmpresa(e1);
		getSession().save(s1);
		Empresa e2 = getSession().get(Empresa.class, e1.getId());
		assertThat(e2).isNotNull();
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testUpdate() {
		Central c1 = new Central();
		c1.setNombre("Central Caterpie");
		getSession().save(c1);
		getSession().clear();
		Central c2 = getSession().get(Central.class, c1.getId());
		c2.setNombre("Central Metapod");
		getSession().update(c2);

		getSession().clear();
		Central c3 = getSession().get(Central.class, c1.getId());
		c3.setNombre("Central Butterfree"); // Se updatea solo con el SET aunque
											// no deberia
		assertThat(c3.getNombre()).isNotEqualTo(c1.getNombre());
		System.out.print("\nc2: " + c2);
		System.out.print("\nc1: " + c1);
		System.out.print("\nc3: " + c3);

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testEjemploCriteria() {
		Empresa e1 = new Empresa();
		e1.setNombre("Telefonica");
		Sucursal s1 = new Sucursal();
		s1.setCalle("Villegas");
		s1.setNumero(2589);
		Empresa e2 = new Empresa();
		e2.setNombre("AA");
		s1.setEmpresa(e1);
		getSession().save(s1);
		getSession().save(e2);

		List<Empresa> empTel = getSession().createCriteria(Empresa.class)
							  .add(Restrictions.like("nombre", "Telefonica"))
							  .list();

		assertThat(empTel).isNotEmpty();
	}
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void testEjemploCriteria2Juli() {
//		List <Empresa> empresa2 = getSession().createCriteria(Empresa.class)
//				.add(Restrictions.like("Nombre", "Telefonica"))
//				.list();
//	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testEjemploCriteria3Juli() {
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Pepe");
		getSession().save(cliente1);

		List<Cliente> clientelista = getSession().createCriteria(Cliente.class)
						.add(Restrictions.like("nombre", "Pepe"))
						.list();
		
		assertThat(clientelista.size()).isEqualTo(1);
		
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testEjemploCriteria4Juli() {
		Sucursal s1 = new Sucursal();
		Sucursal s2 = new Sucursal();
		Sucursal s3 = new Sucursal();
		
		Empresa e1 = new Empresa();
		e1.setNombre("Telecom");
		Empresa e2 = new Empresa ();
		e2.setNombre("Telefonica");
		s1.setEmpresa(e2);
		s2.setEmpresa(e1);
		s3.setEmpresa(e2);
		getSession().save(s1);
		getSession().save(s2);
		getSession().save(s3);
		
		List <Sucursal> SucursalLista = getSession().createCriteria(Sucursal.class)
										.add(Restrictions.like("empresa", e2)).list();
		assertThat(SucursalLista.size()).isEqualTo(2);
		
		
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testEjemploCriteria5Juli() {
		Sucursal s1 = new Sucursal();
		Sucursal s2 = new Sucursal();
		Sucursal s3 = new Sucursal();
		
		Empresa e1 = new Empresa();
		e1.setNombre("Telecom");
		Empresa e2 = new Empresa ();
		e2.setNombre("Telefonica");
		s1.setEmpresa(e2);
		s2.setEmpresa(e1);
		s3.setEmpresa(e2);
		getSession().save(s1);
		getSession().save(s2);
		getSession().save(s3);
		
		List <Sucursal> SucursalLista = getSession().createCriteria(Sucursal.class)
										.createAlias("empresa", "EmpresaBuscada")
										.add(Restrictions.like("EmpresaBuscada.nombre", "T%")).list();
		//								.add(Restrictions.like("nombre","j%") 
		assertThat(SucursalLista.size()).isEqualTo(3);
		
		
	}
	//TAREA: AGREGAR DIRECCION EN CENTRAL 
	//TODAS LAS LLAMADAS MAYOR A 2 QUE SEAN DE LA CENTRAL de la calle "	FLORENCIO VARELA" Y DE EMPRESA CON NOMBRE TELEFONICA
	//TODO CON CRITERIA Y CREATEALIAS
}

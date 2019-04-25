package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Cliente;


public class TestCliente extends SpringTest{

	@Test
	@Transactional
	@Rollback (true)
	public void testIngreso() {
		Cliente c1 = new Cliente();
		c1.setNombre("Carlito");
		getSession().save(c1);
		Cliente c2 = getSession().get(Cliente.class, c1.getId());
		assertThat(c1.getNombre()).isEqualTo(c2.getNombre());
	}
	@Test
	@Transactional
	@Rollback (true)
	public void testUpdate(){
		Cliente c1 = new Cliente();
		c1.setNombre("Pikachu");
		getSession().save(c1);
		c1.setNombre("Raichu");
		getSession().update(c1);
		Cliente c2 = getSession().get(Cliente.class, c1.getId());
		assertThat(c1.getNombre()).isEqualTo(c2.getNombre());
	}
	@Test
	@Transactional
	@Rollback (true)
	public void testDelete(){
		Cliente c1 = new Cliente();
		c1.setNombre("Bran-ojala en esta temporada-");
		getSession().save(c1);
		getSession().delete(c1);
		Cliente c2 = getSession().get(Cliente.class, c1.getId());
		assertThat(c2).isNull();
	}
}

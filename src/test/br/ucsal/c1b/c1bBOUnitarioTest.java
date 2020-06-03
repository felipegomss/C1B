package br.ucsal.c1b;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.ucsal.c1b.dao.UsuarioDAO;
import br.ucsal.c1b.vo.Usuario;


@RunWith(PowerMockRunner.class)
@PrepareForTest({UsuarioDAO.class})
public class c1bBOUnitarioTest {
	
	@Test
	public void testarPrecoCorte() {
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario user = dao.userInfo(2);
		
		Double resultEsp = 15D;
		String resultTest = user.getValorServico();
		
		resultTest = resultTest.replace(",", ".");
		
		Double resultTestD = Double.parseDouble(resultTest); 
		
		Assertions.assertEquals(resultEsp, resultTestD);
		
		}
	
	public void userTest() {
		
	}

}

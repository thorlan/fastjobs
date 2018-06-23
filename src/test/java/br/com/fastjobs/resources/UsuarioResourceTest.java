package br.com.fastjobs.resources;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.fastjobs.model.Usuario;
import io.restassured.path.json.JsonPath;
//TODO: TESTES PARA TODOS OS RESOURCES. TODOS OS METODOS DELES.
//TODO: TESTAR OS ENDPOINTS COM RESTASSURED!
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { UsuarioResource.class })
public class UsuarioResourceTest {

/*  @Test
  public void deveRetornarUmUsuario() {
	  
	  JsonPath path = given().
				header("Accept","application/json")
				.contentType("application/json")
				.expect().statusCode(200).when()
				.get("http://localhost:8080/usuarios/1")
				.andReturn().jsonPath();
	  
		Usuario usuario = path.getObject("usuario", Usuario.class);
		
		assertTrue(usuario.getNome().contains("Riciele"));
  }*/
}


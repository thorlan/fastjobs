package br.com.fastjobs.resources;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.fastjobs.model.Usuario;
import io.restassured.path.json.JsonPath;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { UsuarioResource.class })
public class UsuarioResourceTest {

  @Test
  public void deveRetornarUmUsuario() {
	  
	  JsonPath path = given().
				header("Accept","application/json")
				.contentType("application/json")
				.expect().statusCode(200).when()
				.get("http://localhost:8080/usuarios/1")
				.andReturn().jsonPath();
	  
		Usuario usuario = path.getObject("", Usuario.class);//TODO: ADD "USUARIO" NO RETORNO DO GET
		
		assertTrue(usuario.getNome().contains("Riciele"));
  }
}

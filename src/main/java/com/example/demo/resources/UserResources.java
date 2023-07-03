package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@RestController // define métodos para lidar com solicitações HTTP.
//um controlador REST é uma classe responsável por receber as requisições HTTP de um cliente e fornecer uma resposta adequada.
//Ele atua como uma ponte entre as requisições feitas pelo cliente e a lógica de negócio da aplicação.

@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		List<User> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		User obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) { // essa annotation é pra dizer q meu obj chegara no modo
																// json e será deserealizado para um objeto user
		obj = service.insert(obj);

//quando se insere um recurso o mais adequado é retornar um 201 que é especifico do http que criei um novo recurso!!
//return ResponseEntity.ok().body(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);// o created espera um obj do tipo URI (um cabecalho contendo o
														// endereço do novo recurso!!!!
	}

	@DeleteMapping(value = "/{id}")

//SOLUCAO IMPROV(SOLUCAO DO NELIO N DEU )	
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
	    User user = service.findById(id);
	    if (user == null) {
	        throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
	    }
	    service.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	

//	 void pois a resposta nao retornará nenhum corpo !
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		service.deleteById(id);
//		return ResponseEntity.noContent().build();// noContent() quando a resposta é vazia!! (204)
//		 O build() após noContent(), está construindo efetivame x'nte a resposta HTTP
//	vazia e retornando-a.

//	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);

	}

}

package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.services.OrderService;

//SOBRE A RESOURCE: Responsável por interceptar as requisições vindas do cliente, 
//seja ele front end ou via postman, chama a Service para passar essas requisições.


@RestController // define métodos para lidar com solicitações HTTP.
@RequestMapping(value = "/orders")

public class OrderResources {

@Autowired
private OrderService service ;

	
@GetMapping	
//a classe ResponseEntity representa uma resposta HTTP 
public ResponseEntity<List<Order>> findAll(){

//Aqui é demonstrado É CHAMADA a service !! 
List<Order> list  = service.findAll();


return ResponseEntity.ok().body(list);
//A expressão ResponseEntity.ok() cria um objeto ResponseEntity com um código de status HTTP 200 (OK), indicando que a solicitação foi bem-sucedida.
//Em seguida, o método body(list) é chamado para definir o corpo da resposta como a lista de pedidos retornada pelo serviço.

}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
	
	Order obj = service.findById(id);
	
	return ResponseEntity.ok().body(obj);		
	}

}

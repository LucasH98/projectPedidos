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

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;


@RestController
@RequestMapping(value  = "/products")
public class ProductResources {
	
@Autowired	
private ProductService ProductService ;

@GetMapping
public ResponseEntity<List<Product>> findAll() {	
	
List<Product> list = ProductService.findAll();
return ResponseEntity.ok().body(list);

}

@GetMapping(value = "/{id}")
public ResponseEntity<Product> findById(@PathVariable Long id) {
Product obj = ProductService.findById(id);
return ResponseEntity.ok().body(obj);
	
}
@PostMapping
public ResponseEntity<Product> insert(@RequestBody Product obj){
obj = ProductService.insert(obj);

//o uri é pra criar um location ,  um cabecalho contendo o endereço do novo recurso! ,retornando o codigo correto(201)
URI uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri() ;
return ResponseEntity.created(uri).body(obj);//o created espera um obj do tipo URI (um cabecalho contendo o endereço do novo recurso!!!!

}

@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> deleteById(@PathVariable Long id){
ProductService.deleteById(id);
return ResponseEntity.noContent().build();
}

@PutMapping(value = "/{id}")
public ResponseEntity<Product> update(@RequestBody Product obj,@PathVariable Long id){
obj = ProductService.update(obj, id);
return ResponseEntity.ok().body(obj);
}

}

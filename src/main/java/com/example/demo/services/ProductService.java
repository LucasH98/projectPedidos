package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

@Autowired	
private ProductRepository ProductRepository;


public List<Product> findAll(){
	
return ProductRepository.findAll();

}

public Product findById(Long id) {

 Optional<Product> obj = ProductRepository.findById(id);
	
 return obj.get();
}

public Product insert(Product obj) {
	
return ProductRepository.save(obj);

}
public void deleteById(Long id) {
ProductRepository.deleteById(id);	
}

public Product update(Product obj , Long id) {
Product entity = ProductRepository.getReferenceById(id);

UpdateData(entity,obj); //atualizar minha entity com base nos dados do obj!!

return ProductRepository.save(obj);

}

private void UpdateData(Product entity, Product obj) {
entity.setName(obj.getName());
entity.setPrice(obj.getPrice());
entity.setDescription(obj.getDescription());
}

}

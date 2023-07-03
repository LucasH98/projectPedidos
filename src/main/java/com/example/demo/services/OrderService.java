package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Order;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderService {
	
@Autowired
private OrderRepository orderRepository ;
//declaro a repository pois é a partir dela que serao acessadas e  realizadas as operacoes no banco .

public List<Order> findAll(){

return orderRepository.findAll();	
	
}

public Order findById(Long id) {
	
	
Optional<Order> obj = orderRepository.findById(id);
return obj.get();
}

}

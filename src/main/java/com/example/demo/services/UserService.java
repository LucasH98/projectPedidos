package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.DataBaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

@Autowired	
private UserRepository userRepository;


public List<User> findAll(){
	
return userRepository.findAll();

}

public User findById(Long id) {

 Optional<User> obj = userRepository.findById(id);
	
 return obj.orElseThrow(()->new ResourceNotFoundException(id));
}

public User insert(User obj) {
	
return 	userRepository.save(obj);

}
public void deleteById(Long id) {
	
try {	
userRepository.deleteById(id);
}
catch(DataIntegrityViolationException e ) {
	throw new DataBaseException(e.getMessage());
}
	}
		
public User update(Long id, User obj) {
	
try {	
User entity = userRepository.getReferenceById(id);	//instancia um user mas apenas o deixa monitorado  sem ir no banco de dados!!
//e somente em seguida realizar uma operacao no bd
//obs :o findById Vai necessariamente no BD e tras o objeto. O getReferenceById apenas prepara o objeto monitorado.

//entao primeiro preciso atualizar os dados manualmente ultilizando uma função :

updateData(entity,obj); //atualizar o entity com base nos dados que chegaram do obj

return userRepository.save(entity);
}

catch(EntityNotFoundException e ) {
throw new ResourceNotFoundException(id);
}

}


private void updateData(User entity, User obj) {
	
entity.setName(obj.getName());	
entity.setEmail(obj.getEmail());	
entity.setPhone(obj.getPhone());	

}

}

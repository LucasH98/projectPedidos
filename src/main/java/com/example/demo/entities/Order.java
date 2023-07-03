package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id ;
private Instant moment ;
private Integer orderstatus ;


@ManyToOne
@JoinColumn(name = "client_Id")
private User client;

//relacionamento especifico com OrderItem:


@OneToMany(mappedBy = "id.order")
private Set<OrderItem> items = new HashSet<>();

@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
private Payment payment ;

//cascade = CascadeType.ALL:

//Esse atributo define o comportamento de cascata das operações de persistência. Nesse caso, o CascadeType.ALL indica que todas as operações de persistência
//(inserção, atualização, exclusão) realizadas na entidade atual também devem ser aplicadas à entidade Payment.
//Ou seja, quando você realizar alguma operação na entidade atual, como salvar (persist) ou remover (remove),
//a operação correspondente também será aplicada à entidade Payment.

public Order() {

	
}


public Order(Long id, Instant moment, OrderStatus orderstatus, User client) {
	this.id = id;
	this.moment = moment;
	setOrderstatus(orderstatus);
	this.client = client;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Instant getMoment() {
	return moment;
}


public void setMoment(Instant moment) {
	this.moment = moment;
}

public OrderStatus getOrderstatus() {
	return OrderStatus.ValueOf(orderstatus);
}


public void setOrderstatus(OrderStatus orderstatus) {
	if(orderstatus!=null) {
	this.orderstatus = orderstatus.getCode();
	}
}

public User getClient() {
	return client;
}


public void setClient(User client) {
	this.client = client;
}

public Set<OrderItem> getItems(){
return 	items;
}


public Payment getPayment() {
	return payment;
}


public void setPayment(Payment payment) {
	this.payment = payment;
}


public Double getTotal() {
Double total = 0.0 ;
for(OrderItem x : items) {	
total+=x.getSubTotal();

}

return total ;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Order other = (Order) obj;
	return Objects.equals(id, other.id);
}


}

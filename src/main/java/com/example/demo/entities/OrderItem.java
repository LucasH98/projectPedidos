package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;

import com.example.demo.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
private static final long serialVersionUID = 1L;
	
@EmbeddedId
private OrderItemPK id  = new OrderItemPK(); //sempre que se cria uma classe auxiliar que é um pkComposto ,DEVE SER INSTANCIADO 	
private Integer quantity;
private Double Price;


public OrderItem() {
		
}


public OrderItem(Order order , Product product ,Integer quantity, Double price) {
	
	id.setOrder(order);
	id.setProduct(product);
	this.quantity = quantity;
	Price = price;
	
}

@JsonIgnore
public Order getOrder() {
return id.getOrder();
}

public void setOrder(Order order) {
	id.setOrder(order);
}

public Product getProduct() {
	return id.getProduct();
}

public void setProduct(Product product) {
id.setProduct(product);	
}


public Integer getQuantity() {
	return quantity;
}


public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}


public Double getPrice() {
	return Price;
}


public void setPrice(Double price) {
	Price = price;
}


public Double getSubTotal() {
	
return Price *quantity;	
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
	OrderItem other = (OrderItem) obj;
	return Objects.equals(id, other.id);
}



}

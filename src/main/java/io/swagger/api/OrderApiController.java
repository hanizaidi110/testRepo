package io.swagger.api;

import io.swagger.model.Order;
import io.swagger.model.OrderItem;
import io.swagger.model.Pizza;
import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T23:44:21.138Z")

@Controller
public class OrderApiController implements OrderApi {

	List<Order> listOrder = new ArrayList<Order>();
	List<OrderItem> orderItemList = new ArrayList<OrderItem>();
	AtomicInteger orderID = new AtomicInteger(1);
	 
	
    public ResponseEntity<Void> createOrder(@ApiParam(value = "order of pizzas" ,required=true ) @RequestBody Order body) {
 
    if(body.getRecipient() != null) {	
    	Order order = new Order();
    	long id = orderID.getAndIncrement();

    	order.setId(id);
    	order.setOrderItems(body.getOrderItems());
    	order.setRecipient(body.getRecipient());
    	order.setTotalPrice(order.getTotalPrice());
    	listOrder.add(order);
    	return new ResponseEntity<Void>(HttpStatus.CREATED);
    }else {
       	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	}
    }

    public ResponseEntity<Void> deleteOrder(@ApiParam(value = "ID of the order to be deleted",required=true ) @PathVariable("orderId") Long orderId) {
    
    	for(Order o:listOrder) {
        	if(o.getId() == orderId) {
        	listOrder.remove(o);
        		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        	}
    	}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
    	}
    

    public ResponseEntity<Order> getOrderById(@ApiParam(value = "ID of order to be returned",required=true ) @PathVariable("orderId") Long orderId) {
    	for(Order o:listOrder) {
        	if(o.getId() == orderId) {
        		return new ResponseEntity<Order>(o,HttpStatus.NO_CONTENT);
        	}
    	}
		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		
    	}
    

    public ResponseEntity<List<Integer>> getOrders() {

    	List<Integer> ms = new ArrayList<Integer>();
    	for(Order b:listOrder) {
        	ms.add(b.getId().intValue());
    	}
    	if(ms.isEmpty()) {
        	return new ResponseEntity<List<Integer>>(ms,HttpStatus.NOT_FOUND);
    	}else {
    	   	return new ResponseEntity<List<Integer>>(ms,HttpStatus.OK);
    	}
    }
}

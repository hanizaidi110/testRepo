	package io.swagger.api;

import io.swagger.model.Pizza;
import io.swagger.model.Topping;

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

import ch.qos.logback.core.net.SyslogOutputStream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T23:44:21.138Z")

@Controller
public class PizzaApiController implements PizzaApi {

	List<Pizza> pis = new ArrayList<Pizza>();
 	List<Topping> tops = new ArrayList<Topping>();
 	AtomicInteger pizzaID = new AtomicInteger(1);
 	AtomicInteger toppingID = new AtomicInteger(1);
 	
    public ResponseEntity<Void> addPizza(@ApiParam(value = "Pizza that should be added to the menu" ,required=true ) @RequestBody Pizza body) {
     	Pizza  p = new 	Pizza();

     	if(body.getName() != null || body.getSize() !=null){
     		long id = pizzaID.getAndIncrement();
     		p.setId(id);
     		p.setName(body.getName());
        	p.setSize(body.getSize());
        	p.setPrice(p.getPrice());
        	pis.add(p);
        	
        	return new ResponseEntity<Void>(HttpStatus.CREATED);	
        		
     	}else {	
     		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
     	}
     	
    }
    
    public ResponseEntity<Pizza> getPizzaById(@ApiParam(value = "ID of pizzas",required=true ) @PathVariable("pizzaId") Long pizzaId) {

    	for(Pizza p:pis) {
    	if(p.getId() == pizzaId) {
    		return new ResponseEntity<Pizza>(p,HttpStatus.OK);
     	 }
    	}
		return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    		}

    public ResponseEntity<List<Integer>> getPizzas() {
    	
    	List<Integer> ir = new ArrayList<Integer>();
    	for(Pizza p:pis) {	
        	ir.add(p.getId().intValue());
        	}
    	if(ir.isEmpty()) {
    		return new ResponseEntity<List<Integer>>(ir,HttpStatus.NOT_FOUND);	
    	}else {
    		return new ResponseEntity<List<Integer>>(ir,HttpStatus.OK);
    	}
    	
    }

    public ResponseEntity<Void> deletePizza(@ApiParam(value = "Id of pizza to delete.",required=true ) @PathVariable("pizzaId") Long pizzaId) {
        
    	for(Pizza p:pis) {
        	if(p.getId() == pizzaId) {
        	pis.remove(p);
        		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        	}
    	}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
    }

    public ResponseEntity<Void> updatePizza(@ApiParam(value = "Pizza that should be modified" ,required=true ) @RequestBody Pizza body,
        @ApiParam(value = "ID of pizzas",required=true ) @PathVariable("pizzaId") Long pizzaId) {
    	
    	for (Pizza s: pis) {
    		if(s.getId() == pizzaId) {

    	    	Pizza  p = new 	Pizza();

    	    	p.setId((long)1);
    	    	p.setName(body.getName());
    	    	p.setSize(body.getSize());
    	    	p.setPrice(body.getPrice());
    	    	pis.add(p);
    	    	
    	    return new ResponseEntity<Void>(HttpStatus.CREATED);

    			}
    		}return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	
    	}

    
    public ResponseEntity<Void> createTopping(@ApiParam(value = "ID of pizza to update",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "Topping to be added to the pizza" ,required=true ) @RequestBody Topping body) {
        
    	if(body.getName() != null && body.getPrice() !=null){
         	
    	for(Pizza p:pis) {
        	if(p.getId() == pizzaId) {
             	
        		long tid = toppingID.getAndIncrement();
        		Topping  t = new Topping(); 	
            	p.getToppings().add(tid);
        		t.setId(tid);
            	t.setName(body.getName());
            	t.setPrice(body.getPrice());
            	
            	tops.add(t);
            	return new ResponseEntity<Void>(HttpStatus.CREATED);
        		}
        	
        	}
    	}
    	
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteToppingById(@ApiParam(value = "ID of the pizza.",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "ID of the topping.",required=true ) @PathVariable("toppingId") Long toppingId) {
       
    	if(pizzaId != null && toppingId!= null) {
    	for(Pizza p: pis) {
    		if(pizzaId == p.getId()) {
    			for(Long z: p.getToppings()) {
    				if(z == toppingId) {
    					p.getToppings().remove(toppingId);
    					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    			  }
    			}
    		}
    		
    	  }
    	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); 
    }
    
    public ResponseEntity<Topping> getToppingById(@ApiParam(value = "ID of the pizza.",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "ID of the topping.",required=true ) @PathVariable("toppingId") Long toppingId) {

    	for(Pizza p:pis) {
        	if(p.getId() == pizzaId) {
        		for(Topping ts: tops) {
        			if(ts.getId() == toppingId) {
             			return new ResponseEntity<Topping>(ts,HttpStatus.OK);	 
        			}
        		}
        		
         	  }
        	}
    		  return new ResponseEntity<Topping>(HttpStatus.NOT_FOUND);
    		}

    public ResponseEntity<List<Integer>> listToppings(@ApiParam(value = "ID of pizza",required=true ) @PathVariable("pizzaId") Long pizzaId) {

    	for(Pizza ps: pis) {
    		if(ps.getId() == pizzaId) {
    			List<Integer> ls = new ArrayList<Integer>();
    			for(Long x: ps.getToppings()) {
    				ls.add(x.intValue());
    			}
    			return new ResponseEntity<List<Integer>>(ls,HttpStatus.NOT_FOUND);
    		}
    	}
    	return new ResponseEntity<List<Integer>>(HttpStatus.NOT_FOUND);
    }

}

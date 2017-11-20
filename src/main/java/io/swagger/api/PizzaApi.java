package io.swagger.api;

import io.swagger.model.Pizza;
import io.swagger.model.Topping;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-14T23:44:21.138Z")

@Api(value = "pizza", description = "the pizza API")
public interface PizzaApi {

    @ApiOperation(value = "Add a new Pizza to the menu.", notes = "", response = Void.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created new pizza.", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Void.class) })
    @RequestMapping(value = "/pizza",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addPizza(@ApiParam(value = "Pizza that should be added to the menu" ,required=true ) @RequestBody Pizza body);


    @ApiOperation(value = "Creates a new topping.", notes = "", response = Void.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created new Topping for pizza.", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Void.class),
        @ApiResponse(code = 404, message = "No toppings found.", response = Void.class) })
    @RequestMapping(value = "/pizza/{pizzaId}/topping",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createTopping(@ApiParam(value = "ID of pizza to update",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "Topping to be added to the pizza" ,required=true ) @RequestBody Topping body);


    @ApiOperation(value = "Deletes a Pizza", notes = "", response = Void.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "deleted", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 404, message = "Pizza not found", response = Void.class) })
    @RequestMapping(value = "/pizza/{pizzaId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePizza(@ApiParam(value = "Id of pizza to delete.",required=true ) @PathVariable("pizzaId") Long pizzaId);


    @ApiOperation(value = "Delete topping by ID", notes = "Deletes the topping with the given ID.", response = Void.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "deleted", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid IDs.", response = Void.class),
        @ApiResponse(code = 404, message = "Pizza or Topping not found.", response = Void.class) })
    @RequestMapping(value = "/pizza/{pizzaId}/topping/{toppingId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteToppingById(@ApiParam(value = "ID of the pizza.",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "ID of the topping.",required=true ) @PathVariable("toppingId") Long toppingId);

    @ApiOperation(value = "Get pizza by ID", notes = "Returns a single Pizza", response = Pizza.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "found pizza", response = Pizza.class),
        @ApiResponse(code = 404, message = "Pizza could not be found", response = Pizza.class) })
    @RequestMapping(value = "/pizza/{pizzaId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> getPizzaById(@ApiParam(value = "ID of pizzas",required=true ) @PathVariable("pizzaId") Long pizzaId);


    @ApiOperation(value = "Returns all pizzas (their ids) on the menu.", notes = "", response = Integer.class, responseContainer = "List", tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Integer.class),
        @ApiResponse(code = 404, message = "No pizzas exist", response = Integer.class) })
    @RequestMapping(value = "/pizza",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Integer>> getPizzas();


    @ApiOperation(value = "Find topping by ID", notes = "Returns the topping with the given ID.", response = Topping.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "found topping", response = Topping.class),
        @ApiResponse(code = 400, message = "Invalid ID(s) supplied.", response = Topping.class),
        @ApiResponse(code = 404, message = "Pizza or Topping not found.", response = Topping.class) })
    @RequestMapping(value = "/pizza/{pizzaId}/topping/{toppingId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Topping> getToppingById(@ApiParam(value = "ID of the pizza.",required=true ) @PathVariable("pizzaId") Long pizzaId,
        @ApiParam(value = "ID of the topping.",required=true ) @PathVariable("toppingId") Long toppingId);


    @ApiOperation(value = "Get the list of toppings for this pizza.", notes = "", response = Integer.class, responseContainer = "List", tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Found toppings for this pizza. Returned are ids.", response = Integer.class),
        @ApiResponse(code = 400, message = "No toppings found.", response = Integer.class),
        @ApiResponse(code = 404, message = "Specified pizza id not found.", response = Integer.class) })
    @RequestMapping(value = "/pizza/{pizzaId}/topping",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Integer>> listToppings(@ApiParam(value = "ID of pizza",required=true ) @PathVariable("pizzaId") Long pizzaId);

    @ApiOperation(value = "Update an existing pizza", notes = "", response = Void.class, tags={ "pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Update okay", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid pizza supplied", response = Void.class),
        @ApiResponse(code = 404, message = "Pizza not found", response = Void.class) })
    @RequestMapping(value = "/pizza/{pizzaId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updatePizza(@ApiParam(value = "Pizza that should be modified" ,required=true ) @RequestBody Pizza body,
        @ApiParam(value = "ID of pizzas",required=true ) @PathVariable("pizzaId") Long pizzaId);

}

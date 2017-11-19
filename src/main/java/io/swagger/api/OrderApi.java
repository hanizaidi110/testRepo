package io.swagger.api;

import io.swagger.model.Order;

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

@Api(value = "order", description = "the order API")
public interface OrderApi {

    @ApiOperation(value = "Place an order.", notes = "", response = Void.class, tags={ "order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created new order successfully.", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid Order", response = Void.class) })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createOrder(@ApiParam(value = "order of pizzas" ,required=true ) @RequestBody Order body);


    @ApiOperation(value = "Delete purchase order by ID", notes = "", response = Void.class, tags={ "order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Deletion successfull", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 404, message = "Order not found", response = Void.class) })
    @RequestMapping(value = "/order/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteOrder(@ApiParam(value = "ID of the order to be deleted",required=true ) @PathVariable("orderId") Long orderId);


    @ApiOperation(value = "Find purchase order by ID", notes = "", response = Order.class, tags={ "order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Order.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Order.class),
        @ApiResponse(code = 404, message = "Order not found", response = Order.class) })
    @RequestMapping(value = "/order/{orderId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Order> getOrderById(@ApiParam(value = "ID of order to be returned",required=true ) @PathVariable("orderId") Long orderId);


    @ApiOperation(value = "Returns a list of orders.", notes = "", response = Integer.class, responseContainer = "List", tags={ "order", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "found orders. returned are ids.", response = Integer.class),
        @ApiResponse(code = 404, message = "No orders found.", response = Integer.class) })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Integer>> getOrders();

}

package com.example.eazshop.backend.controller;


import com.example.eazshop.backend.model.Orders;
import com.example.eazshop.backend.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ordersAPI")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> ordersList = ordersService.getAllOrders();
        if(ordersList==null){
            return new ResponseEntity<List<Orders>>(ordersList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<List<Orders>>(ordersList,HttpStatus.OK);
        }
    }

    @GetMapping("/getOrderDetailsByOrderId/{orderId}")
    public ResponseEntity<Orders> getOrderDetailsById(@PathVariable("orderId") String orderId){
        Orders orderDetailsById = ordersService.getOrderDetails(orderId);
        if(orderDetailsById==null){
            return new ResponseEntity<Orders>(orderDetailsById,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<Orders>(orderDetailsById,HttpStatus.OK);
        }
    }

    @GetMapping("/getOrderDetailsByCustomerId/{customerId}")
    public ResponseEntity<List<Orders>> getOrderDetailsByCustomerId(@PathVariable("customerId") String customerId){
        List<Orders> orderDetailsById = ordersService.getOrderDetailsByCustomerId(customerId);
        if(orderDetailsById==null){
            return new ResponseEntity<List<Orders>>(orderDetailsById,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<List<Orders>>(orderDetailsById,HttpStatus.OK);
        }
    }

    @GetMapping("/getOrderDetailsByDate/{date}")
    public ResponseEntity<List<Orders>> getOrderDetailsByDate(@PathVariable("date") String date){
        List<Orders> orderDetailsById = ordersService.getOrderDetailsByDate(date);
        if(orderDetailsById==null){
            return new ResponseEntity<List<Orders>>(orderDetailsById,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<List<Orders>>(orderDetailsById,HttpStatus.OK);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Orders orders){
        if(ordersService.createOrder(orders)){
            return new ResponseEntity<String>("Order placed successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error occurred while placing order",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modifyOrder")
    public ResponseEntity<String> modifyOrder(@RequestBody Orders orders){
        if(ordersService.modifyOrder(orders)){
            return new ResponseEntity<String>("Order modified successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error occurred while modifying order",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteOrderByOrderId/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") String orderId){
        if(ordersService.deleteOrder(orderId)){
            return new ResponseEntity<String>("Order deleted by orderId successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error occurred while deleting order by orderId",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteOrderByCustomerId/{customerId}")
    public ResponseEntity<String> deleteOrderByCustomerId(@PathVariable("customerId") String customerId){
        if(ordersService.deleteOrderByCustomerId(customerId)){
            return new ResponseEntity<String>("Order deleted by customerId successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Error occurred while deleting order by customerId",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.BackendProject.controllers;

import com.example.BackendProject.models.OrderedItem;
import com.example.BackendProject.services.OrderService;
import com.example.BackendProject.services.OrderedItemService;
import com.example.BackendProject.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ordered-items")
public class OrderedItemController {
    @Autowired
    OrderedItemService orderedItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity<List<OrderedItem>> getAllOrderedItems(){
        return new ResponseEntity<>(orderedItemService.findAllOrderedItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderedItem> getOrderedItem(@PathVariable Long id){
        Optional<OrderedItem> orderedItemFound = orderedItemService.findOrderedItem(id);
        if (orderedItemFound.isPresent()) {
            return new ResponseEntity<>(orderedItemFound.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
   // @PostMapping
    //public ResponseEntity<OrderedItem> postOrderedItem(@RequestBody)

    //@PatchMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteOrderedItem(@PathVariable Long id){
        orderedItemService.deleteOrderedItem(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}

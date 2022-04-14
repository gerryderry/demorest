package com.example.rest.demo.controller;

import com.example.rest.demo.model.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController(){
        coffees.addAll(Arrays.asList(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ));
    }

    @GetMapping(value = "/coffees")
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee c : coffees) {
            if (c.getId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffees/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}


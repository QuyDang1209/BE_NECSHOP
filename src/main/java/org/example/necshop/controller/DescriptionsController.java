package org.example.necshop.controller;

import org.example.necshop.model.Descriptions;
import org.example.necshop.service.IDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/des")
public class DescriptionsController {
    @Autowired
    private IDescriptionService descriptionService;
    @GetMapping("")
    private ResponseEntity<?> getDescription(@RequestParam Long productID,
                                             @RequestParam String size,
                                             @RequestParam Long colorId){
        Descriptions descriptions = descriptionService.findByIdProductAndColorAndSize(productID,colorId,size);
        if(descriptions == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(descriptions, HttpStatus.FOUND);
    }
}

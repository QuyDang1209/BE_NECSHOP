package org.example.necshop.controller;

import org.example.necshop.model.Products;
import org.example.necshop.model.request.ProductReqDTO;
import org.example.necshop.model.response.ProductsResDTO;
import org.example.necshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping()
    public ResponseEntity<?> getAllProductsDTO(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize) {
        Page<ProductsResDTO> productsRequestDTOPage = productService.findAllProductsDTO(page,pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("items", productsRequestDTOPage.getContent());
        response.put("totalPages", productsRequestDTOPage.getTotalPages());
        return new ResponseEntity<>(productsRequestDTOPage, HttpStatus.OK);

    }
    // sắp xếp sản phẩm theo giá từ cao đến thấp
    @GetMapping("/sort-by-price")
    public ResponseEntity<?> getAllProductsDTOSortByPrice(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize) {
        Page<ProductsResDTO> productsRequestDTOPage = productService.findAllProductsDTOSortedByPrice(page,pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("items", productsRequestDTOPage.getContent());
        response.put("totalPages", productsRequestDTOPage.getTotalPages());
        return new ResponseEntity<>(productsRequestDTOPage, HttpStatus.OK);

    }
    //sắp xếp sản phẩm theo giá từ thấp đến cao
    @GetMapping("/sort-by-price-ascending")
    public ResponseEntity<?> getAllProductsDTOSortByPriceAcsending(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize) {
        Page<ProductsResDTO> productsRequestDTOPage = productService.findAllProductsDTOSortedByPriceAscending(page,pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("items", productsRequestDTOPage.getContent());
        response.put("totalPages", productsRequestDTOPage.getTotalPages());
        return new ResponseEntity<>(productsRequestDTOPage, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findProductsById(@PathVariable Long id){
        Optional<Products> products = productService.findById(id);
        if(!products.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(products.get(),HttpStatus.OK);
        }
    }
    @GetMapping("fillter")
    private ResponseEntity<?> getAllFillterProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer color,
            @RequestParam(required = false) Integer style,
            @RequestParam(required = false) Integer category
    ){
        Page<ProductsResDTO> productsResDTOS = productService.findAllFillterProductsDTO(page,pageSize,minPrice,maxPrice,size,color,style,category);
        if(productsResDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productsResDTOS,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity saveProducts(@RequestBody ProductReqDTO reqDTO){
        productService.saveOrUpdate(reqDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    private ResponseEntity<?> updateProducts(@RequestBody ProductReqDTO reqDTO){
        Products products = productService.findById(reqDTO.getId()).get();
        if(products == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.saveOrUpdate(reqDTO),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    private ResponseEntity<?> deleteProductByid(@PathVariable Long id){
        Products products = productService.findById(id).get();
        if(products == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

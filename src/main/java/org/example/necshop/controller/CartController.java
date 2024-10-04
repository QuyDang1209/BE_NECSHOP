package org.example.necshop.controller;

import org.example.necshop.model.Cart;
import org.example.necshop.model.Descriptions;
import org.example.necshop.model.request.CartItemRequest;
import org.example.necshop.service.impl.CartService;
import org.example.necshop.service.impl.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private DescriptionService descriptionService;

    // API để thêm sản phẩm vào giỏ hàng
    @PostMapping("/{cartId}/add")
    public ResponseEntity<?> addProductToCart(@PathVariable Long cartId,
                                                 @RequestBody CartItemRequest request) {
        Descriptions descriptions = descriptionService.findByIdProductAndColorAndSize(request.getProductId(), request.getColorId(), request.getSize());
        Cart updatedCart = cartService.addProductToCart(cartId, descriptions, request.getQuantity());
        return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
    }

    // API để xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeProductFromCart(cartId, cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API để lấy thông tin giỏ hàng
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        if(cart == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.FOUND);
    }

    // API để cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{cartId}/update/{cartItemId}")
    public ResponseEntity<Cart> updateCartItemQuantity(@PathVariable Long cartId,
                                                       @PathVariable Long cartItemId,
                                                       @RequestParam Integer quantity) {
        Cart updatedCart = cartService.updateCartItemQuantity(cartId, cartItemId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
}


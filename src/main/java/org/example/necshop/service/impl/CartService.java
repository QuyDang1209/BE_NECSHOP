package org.example.necshop.service.impl;

import org.example.necshop.model.Cart;
import org.example.necshop.model.CartItem;
import org.example.necshop.model.Descriptions;
import org.example.necshop.repository.ICartItemRepository;
import org.example.necshop.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICartItemRepository cartItemRepository;
    // Thêm sản phẩm vào giỏ hàng
    public Cart addProductToCart(Long cartId, Descriptions description, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                .filter(item -> item.getDescriptions().getId().equals(description.getId()))
                .findFirst();

        CartItem cartItem;
        if (optionalCartItem.isPresent()) {
            cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setDescriptions(description);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        cartItem.calculateTotalPrice();
        cart.calculateTotalPrice();

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
        return cart;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeProductFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().removeIf(item -> item.getId() == cartItemId);
        cartItemRepository.deleteById(cartItemId);
//        if(cart.getCartItems().isEmpty()){
//            cartRepository.deleteById(cartId);
//        }
        cart.calculateTotalPrice();
        cartRepository.save(cart);
    }

    // Lấy giỏ hàng theo ID
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public Cart updateCartItemQuantity(Long cartId, Long cartItemId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(quantity);
        cartItem.calculateTotalPrice();
        cart.calculateTotalPrice();
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }
}

package com.internship.bookstore.api.controller;


import com.internship.bookstore.api.dto.WishListRequestDto;
import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.api.exchange.Response;

import com.internship.bookstore.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wishlists")
@RequiredArgsConstructor
public class WishListRestController {

    private final WishListService wishListService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Response<WishListResponseDto>> getWishListById(@PathVariable Long id) {
        return ResponseEntity.ok(Response.build(wishListService.getWishListById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<WishListResponseDto>>> getAllWishLists() {
        return ResponseEntity.ok(Response.build(wishListService.getAllWishLists()));
    }

    @DeleteMapping("/id/{id}")
    public void deleteWishListById(@PathVariable Long id) {
        wishListService.deleteWishList(id);
    }


    @PostMapping
    public ResponseEntity<Response<WishListResponseDto>> createWishList(
            @RequestBody @Valid WishListRequestDto wishListRequestDto, Errors validationErrors) {

        if (validationErrors.hasErrors()) {
            throw new ValidationException(Objects.requireNonNull(validationErrors.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(Response.build(wishListService.save(wishListRequestDto)));
    }


    @PutMapping("/update")
    public ResponseEntity<Response<WishListResponseDto>> updateWishList(@RequestBody @Valid WishListRequestDto wishListRequestDto, Errors validationErrors) {

        if (validationErrors.hasErrors()) {
            throw new ValidationException(Objects.requireNonNull(validationErrors.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(Response.build(wishListService.update(wishListRequestDto)));
    }
}
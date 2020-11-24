package com.internship.bookstore.utils.mappers;

import com.internship.bookstore.api.dto.WishListRequestDto;
import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.model.WishList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WishListMapper {

    public static final Function<WishList, WishListResponseDto> mapWishListToWishListResponseDto =
            wishList -> WishListResponseDto.builder()
             .id(wishList.getId())
            .bookTitle(wishList.getBook().getTitle())
            .userEmail(wishList.getUser().getEmail())
            .build();

    public static final Function<WishListRequestDto,WishList> mapWishListRequestDtoToWishList =
            wishListRequestDto -> WishList.builder()
            .id(wishListRequestDto.getId())
            .build();

}

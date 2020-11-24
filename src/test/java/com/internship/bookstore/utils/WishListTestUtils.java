package com.internship.bookstore.utils;

import com.internship.bookstore.api.dto.WishListRequestDto;
import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.model.WishList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

import static com.internship.TestConstants.*;
import static com.internship.bookstore.utils.BookTestUtils.BOOK_ONE;
import static com.internship.bookstore.utils.BookTestUtils.BOOK_TWO;
import static com.internship.bookstore.utils.UserTestUtils.USER_ONE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WishListTestUtils {

        public static final WishList WISH_LIST_ONE = WishList.builder()
                .id(ID_ONE)
                .user(USER_ONE)
                .book(BOOK_ONE)
                .build();

        public static final WishList WISH_LIST_ONE_UPDATED = WishList.builder()
                .id(ID_ONE)
                .user(USER_ONE)
                .book(BOOK_TWO)
                .build();

        public static final WishListRequestDto WISH_LIST_REQUEST_DTO = WishListRequestDto.builder()
                .id(ID_ONE)
                .bookId(ID_ONE)
                .userEmail(USER_ONE.getEmail())
                .build();

        public static final WishListRequestDto WISH_LIST_REQUEST_DTO_TO_UPDATE = WishListRequestDto.builder()
                .id(ID_ONE)
                .bookId(ID_TWO)
                .userEmail(USER_ONE.getEmail())
                .build();


        public static final WishListResponseDto WISH_LIST_RESPONSE_DTO = WishListResponseDto.builder()
                .userEmail(USER_ONE.getEmail())
                .bookTitle(BOOK_TITLE_ONE)
                .id(ID_ONE)
                .build();



         public static final WishList WISH_LIST_TWO = WishList.builder()
                .id(ID_TWO)
                .user(USER_ONE)
                .book(BOOK_ONE)
                .build();


        public static final WishListResponseDto WISH_LIST_RESPONSE_DTO_TWO = WishListResponseDto.builder()
                .userEmail(USER_ONE.getEmail())
                .bookTitle(BOOK_TITLE_ONE)
                .id(ID_TWO)
                .build();


          public static final WishListResponseDto WISH_LIST_RESPONSE_DTO_UPDATED = WishListResponseDto.builder()
                .userEmail(USER_ONE.getEmail())
                .bookTitle(BOOK_TITLE_TWO)
                .id(ID_ONE)
                .build();




           public static final Supplier<WishListResponseDto> WISH_LIST_RESPONSE_DTO_ONE =
            () -> WishListResponseDto.builder()
                    .id(ID_ONE)
                    .userEmail(USER_ONE.getEmail())
                    .bookTitle(BOOK_TITLE_ONE)
                    .build();

             public static final WishListRequestDto WISH_LIST_REQUEST_DTO_VALID = WishListRequestDto.builder()
                .bookId(ID_ONE)
                .userEmail(USER_ONE.getEmail())
                .build();

}

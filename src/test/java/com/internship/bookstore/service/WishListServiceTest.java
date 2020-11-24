package com.internship.bookstore.service;


import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.model.WishList;
import com.internship.bookstore.repository.BookRepository;
import com.internship.bookstore.repository.UserRepository;
import com.internship.bookstore.repository.WishListRepository;
import com.internship.bookstore.utils.mappers.WishListMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.internship.TestConstants.ID_ONE;
import static com.internship.TestConstants.ID_TWO;
import static com.internship.bookstore.utils.BookTestUtils.BOOK_ONE;
import static com.internship.bookstore.utils.BookTestUtils.BOOK_TWO;
import static com.internship.bookstore.utils.UserTestUtils.USER_ONE;
import static com.internship.bookstore.utils.WishListTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private ValidateWishListService validateWishListService;

    @InjectMocks
    private WishListService wishListService;

    @AfterEach()
    void tearDown() {verifyNoMoreInteractions(bookRepository,userRepository,wishListRepository);}

    @Test
    public void shouldSaveWishListAndReturnResponseDto(){

        final WishListResponseDto expectedWishListResponseDto = WISH_LIST_RESPONSE_DTO;

        when(bookRepository.findBookById(ID_ONE)).thenReturn(Optional.of(BOOK_ONE));
        when(userRepository.findByEmail(USER_ONE.getEmail())).thenReturn(Optional.of(USER_ONE));

        when(wishListRepository.save(any(WishList.class))).thenReturn(WISH_LIST_ONE);

        final WishListResponseDto actualResponseDto = wishListService.save(WISH_LIST_REQUEST_DTO);

        assertEquals(expectedWishListResponseDto.getBookTitle(),actualResponseDto.getBookTitle());
        assertEquals(expectedWishListResponseDto.getUserEmail(),actualResponseDto.getUserEmail());
    }


    @Test
    public void shouldReturnAllWishListsAndMapToResponseDto(){
        List<WishList> wishLists = Arrays.asList(WISH_LIST_ONE,WISH_LIST_TWO);
        when(wishListRepository.findAll()).thenReturn(wishLists);

        final WishListResponseDto expectedFirstResponseDto = WISH_LIST_RESPONSE_DTO;
        final WishListResponseDto expectedSecondResponseDto = WISH_LIST_RESPONSE_DTO_TWO;

        final List<WishListResponseDto> actualAllDtos = wishListService.getAllWishLists();

        assertAll(
                () -> assertEquals(expectedFirstResponseDto,actualAllDtos.get(0)),
                () -> assertEquals(expectedSecondResponseDto,actualAllDtos.get(1))
        );
    }

    @Test
    public void shouldReturnWishListByIdAndAssertEquals(){
        when(wishListRepository.findWishListById(ID_ONE)).thenReturn(Optional.of(WISH_LIST_ONE));

        final WishListResponseDto expectedWishListResponseDto = WISH_LIST_RESPONSE_DTO;
        final WishListResponseDto actualWishListResponseDto = wishListService.getWishListById(ID_ONE);

        assertEquals(expectedWishListResponseDto,actualWishListResponseDto);

    }


    @Test
    public void shouldUpdateWishListAndReturnResponseDto(){

        final WishListResponseDto expectedWishListResponseDto = WISH_LIST_RESPONSE_DTO_UPDATED;

        when(bookRepository.findBookById(ID_TWO)).thenReturn(Optional.of(BOOK_TWO));
        when(userRepository.findByEmail(USER_ONE.getEmail())).thenReturn(Optional.of(USER_ONE));
        when(wishListRepository.save(any(WishList.class))).thenReturn(WISH_LIST_ONE_UPDATED);

        final WishListResponseDto actualResponseDto = wishListService.update(WISH_LIST_REQUEST_DTO_TO_UPDATE);

        assertEquals(expectedWishListResponseDto.getBookTitle(),actualResponseDto.getBookTitle());
        assertEquals(expectedWishListResponseDto.getUserEmail(),actualResponseDto.getUserEmail());
    }


}

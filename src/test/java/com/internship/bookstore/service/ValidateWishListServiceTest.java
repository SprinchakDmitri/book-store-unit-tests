package com.internship.bookstore.service;


import com.internship.bookstore.model.WishList;
import com.internship.bookstore.repository.WishListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.internship.bookstore.utils.UserTestUtils.USER_ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static com.internship.bookstore.utils.WishListTestUtils.*;

@ExtendWith(MockitoExtension.class)
public class ValidateWishListServiceTest {


    @Mock
    WishListRepository wishListRepository;

    @InjectMocks
    private ValidateWishListService validateWishListService;

    @Test
    public void shouldReturnTrueIfUserDontPassWishListLimitBeforeSaving(){

        when(wishListRepository.findWishListByUser(USER_ONE)).thenReturn(Collections.emptyList());
        assertTrue(validateWishListService.validateWishListUserForSave(USER_ONE));
    }

      @Test
    public void shouldReturnFalseIfUserHaveOneWishListBeforeSaving(){
        List<WishList> wishLists = Collections.singletonList(WISH_LIST_ONE);
        when(wishListRepository.findWishListByUser(USER_ONE)).thenReturn(wishLists);
        assertFalse(validateWishListService.validateWishListUserForSave(USER_ONE));
    }

    @Test
    public void shouldReturnTrueIfUserHasBookToUpdate(){
        List<WishList> wishLists = Collections.singletonList(WISH_LIST_ONE);
        when(wishListRepository.findWishListByUser(USER_ONE)).thenReturn(wishLists);
        assertTrue(validateWishListService.validateWishListUserForUpdate(USER_ONE));
    }

      @Test
    public void shouldReturnFalseIfUserHaveNotBookToUpdate(){
        when(wishListRepository.findWishListByUser(USER_ONE)).thenReturn(Collections.emptyList());
        assertFalse(validateWishListService.validateWishListUserForUpdate(USER_ONE));
    }
}

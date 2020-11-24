package com.internship.bookstore.service;

import com.internship.bookstore.model.User;
import com.internship.bookstore.model.WishList;
import com.internship.bookstore.repository.BookRepository;
import com.internship.bookstore.repository.UserRepository;
import com.internship.bookstore.repository.WishListRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ValidateWishListService {

    WishListRepository wishListRepository;

    private static int WISH_LISTS_LIMIT = 1;

    public boolean validateWishListUserForSave(User user){

       List<WishList> wishLists = wishListRepository.findWishListByUser(user);
        return wishLists.size() < WISH_LISTS_LIMIT;
    }

      public boolean validateWishListUserForUpdate(User user){

       List<WishList> wishLists = wishListRepository.findWishListByUser(user);

          return !wishLists.isEmpty();
      }
}

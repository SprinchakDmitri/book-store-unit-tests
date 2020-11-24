package com.internship.bookstore.service;

import com.internship.bookstore.api.dto.WishListRequestDto;
import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.model.Book;
import com.internship.bookstore.model.User;
import com.internship.bookstore.model.WishList;
import com.internship.bookstore.repository.BookRepository;
import com.internship.bookstore.repository.UserRepository;
import com.internship.bookstore.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.internship.bookstore.utils.mappers.WishListMapper.mapWishListRequestDtoToWishList;
import static com.internship.bookstore.utils.mappers.WishListMapper.mapWishListToWishListResponseDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {

    UserRepository userRepository;
    WishListRepository wishListRepository;
    BookRepository bookRepository;

    ValidateWishListService validateWishListService;

    public WishListResponseDto save(WishListRequestDto wishListRequestDto) {
        log.info("Saving the wish list with userEmail [{}] and bookId [{}]", wishListRequestDto.getUserEmail(),
                wishListRequestDto.getBookId());

        User user = userRepository.findByEmail(wishListRequestDto.getUserEmail()).orElseThrow(
                () -> {
                    log.warn("User with email [{}] wasn't found in the database",
                            wishListRequestDto.getUserEmail());
                    return new IllegalArgumentException();
                });

        if(validateWishListService.validateWishListUserForSave(user))
            throw new IllegalArgumentException();


        Book book = bookRepository.findBookById(wishListRequestDto.getBookId()).orElseThrow(
                () -> {
                    log.warn("Book with id [{}] wasn't found in the database",
                            wishListRequestDto.getUserEmail());
                    return new IllegalArgumentException();
                });


        WishList wishList = mapWishListRequestDtoToWishList.apply(wishListRequestDto);
        wishList.setId(null);
        wishList.setBook(book);
        wishList.setUser(user);

        wishList = wishListRepository.save(wishList);

        return mapWishListToWishListResponseDto.apply(wishList);
    }


    public WishListResponseDto update(WishListRequestDto wishListRequestDto) {
        log.info("Updating the wish list with userId [{}] and bookId [{}]", wishListRequestDto.getUserEmail(),
                wishListRequestDto.getBookId());

        User user = userRepository.findByEmail(wishListRequestDto.getUserEmail()).orElseThrow(
                () -> {
                    log.warn("User with id [{}] wasn't found in the database",
                            wishListRequestDto.getUserEmail());
                    return new IllegalArgumentException();
                });

        if(validateWishListService.validateWishListUserForUpdate(user))
            throw new IllegalArgumentException();


        Book book = bookRepository.findBookById(wishListRequestDto.getBookId()).orElseThrow(
                () -> {
                    log.warn("Book with id [{}] wasn't found in the database",
                            wishListRequestDto.getUserEmail());
                    return new IllegalArgumentException();
                });


        WishList wishList = mapWishListRequestDtoToWishList.apply(wishListRequestDto);
        //wishList.setId(wishListRequestDto.getId());
        wishList.setBook(book);
        wishList.setUser(user);

        wishList = wishListRepository.save(wishList);

        return mapWishListToWishListResponseDto.apply(wishList);
    }


    public WishListResponseDto getWishListById(Long id){
        WishList wishList = wishListRepository.findWishListById(id).get();

        return mapWishListToWishListResponseDto.apply(wishList);
    }

    public void deleteWishList(Long id){
        wishListRepository.deleteById(id);
    }


    public List<WishListResponseDto> getAllWishLists() {
        List<WishList> wishLists = wishListRepository.findAll();
        List<WishListResponseDto> responseDtos = new ArrayList<>();

        for (WishList wishList: wishLists) {
            responseDtos.add(mapWishListToWishListResponseDto.apply(wishList));
        }

        return responseDtos;
    }
}

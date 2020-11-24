package com.internship.bookstore.repository;

import com.internship.bookstore.model.Book;
import com.internship.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.internship.bookstore.model.WishList;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {


    Optional<WishList> findWishListById(Long id);

    List<WishList> findWishListByUser(User user);

    List<WishList> findWishListByBook(Book book);

}

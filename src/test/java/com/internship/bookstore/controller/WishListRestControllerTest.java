package com.internship.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.bookstore.api.controller.WishListRestController;
import com.internship.bookstore.api.dto.WishListRequestDto;
import com.internship.bookstore.api.dto.WishListResponseDto;
import com.internship.bookstore.model.WishList;
import com.internship.bookstore.service.BookService;
import com.internship.bookstore.service.UserService;
import com.internship.bookstore.service.ValidateWishListService;
import com.internship.bookstore.service.WishListService;
import com.internship.it.controller.BaseController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.internship.TestConstants.ID_ONE;
import static com.internship.bookstore.utils.WishListTestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WishListRestController.class)
public class WishListRestControllerTest extends BaseController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishListService wishListService;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private ValidateWishListService validateWishListService;


    @Test
    @WithMockUser
    public void shouldReturnAllWishLists() throws Exception{
      when(wishListService.getAllWishLists()).thenReturn(Collections.singletonList(WISH_LIST_RESPONSE_DTO_ONE.get()));

        mockMvc.perform(get("/wishlists/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createExpectedBody(Collections.singletonList(WISH_LIST_RESPONSE_DTO))));

        verify(wishListService).getAllWishLists();
    }

    @Test
    @WithMockUser
    public void shouldReturnWishListById() throws Exception{
        when(wishListService.getWishListById(any(Long.class))).thenReturn(WISH_LIST_RESPONSE_DTO);

         mockMvc.perform(get("/wishlists/id/{id}",ID_ONE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createExpectedBody(WISH_LIST_RESPONSE_DTO)));

        verify(wishListService).getWishListById(ID_ONE);
    }


    @Test
    @WithMockUser
    public void shouldSaveWishList() throws Exception {
        when(wishListService.save(any(WishListRequestDto.class))).thenReturn(WISH_LIST_RESPONSE_DTO);

        mockMvc.perform(post("/wishlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(WISH_LIST_REQUEST_DTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createExpectedBody(WISH_LIST_RESPONSE_DTO)));

        verify(wishListService).save(any(WishListRequestDto.class));
    }

    @Test
    @WithMockUser
    void shouldUpdateWishList() throws Exception{
        when(wishListService.update(any(WishListRequestDto.class))).thenReturn(WISH_LIST_RESPONSE_DTO_UPDATED);

        mockMvc.perform(put("/wishlists/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(WISH_LIST_REQUEST_DTO_TO_UPDATE)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createExpectedBody(WISH_LIST_RESPONSE_DTO_UPDATED)));

}}

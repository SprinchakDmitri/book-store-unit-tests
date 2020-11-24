package com.internship.bookstore.api.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WishListRequestDto {

    @NotNull
    Long id;

    @NotNull
    Long bookId;

    @NotNull
    String userEmail;

}

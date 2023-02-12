package com.example.E29Product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDto {
    private UserDto userDto;
    private ProductDto productDto;
}

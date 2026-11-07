package com.example.demo.DTO;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {

}

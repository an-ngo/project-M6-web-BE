package com.example.loverbe.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpForm {
//    @Size(min = 3, max = 60)
//    private String name;
//    @UniqueUsername
//    @Pattern(regexp = Regex.USER_REGEX, message = Regex.USERNAME_MESSAGE)
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;
//    @UniqueEmail
//    @Pattern(regexp = Regex.EMAIL_REGEX, message = Regex.EMAIL_MESSAGE)
//    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 15)
    @Column(nullable = false)
    private String password;
    private String avatar;
    private String phone;

}

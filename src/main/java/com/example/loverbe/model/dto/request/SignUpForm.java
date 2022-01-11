package com.example.loverbe.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpForm {
//    @Size(min = 3, max = 60)
//    private String name;
//    @UniqueUsername
//    @NotEmpty
//    @Size(min = 3, max = 60)
//    @Pattern(regexp = Regex.USER_REGEX, message = Regex.USERNAME_MESSAGE)
    private String username;
//    @UniqueEmail
//    @NotEmpty
//    @Pattern(regexp = Regex.EMAIL_REGEX, message = Regex.EMAIL_MESSAGE)
    private String email;
//    @NotEmpty
//    @Size(min = 3, max = 60)
//    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Regex.PASSWORD_MESSAGE)
//    @JsonIgnore
    private String password;
//    @NotEmpt
//    @Size(min = 4, max = 20)
//    @Pattern(regexp = Regex.PASSWORD_REGEX, message = Regex.RE_PASSWORD_MESSAGE)
//    @JsonIgnore
//    private String rePassword;
    private String avatar;




}

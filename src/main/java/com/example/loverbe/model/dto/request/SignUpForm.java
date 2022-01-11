package com.example.loverbe.model.dto.request;

import lombok.Data;

import java.util.Set;

@Data
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
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public SignUpForm() {
    }

    public SignUpForm(String username, String email, String password,Set<String> roles) {
//        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
//        this.rePassword = rePassword;
        this.roles = roles;
    }

    public SignUpForm(String username, String email, String password) {
//        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
//        this.rePassword = rePassword;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    //    public String getRePassword() {
//        return rePassword;
//    }
//
//    public void setRePassword(String rePassword) {
//        this.rePassword = rePassword;
//    }
}

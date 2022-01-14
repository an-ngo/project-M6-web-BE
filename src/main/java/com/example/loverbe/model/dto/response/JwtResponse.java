package com.example.loverbe.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String name;
    private String username;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;
    private String phone;

    public JwtResponse(String token, String name,String username,  String avatar, Collection<? extends GrantedAuthority> roles,String phone) {
        this.token = token;
//        this.type = type;
        this.name = name;
        this.username = username;
        this.avatar = avatar;
        this.roles = roles;
        this.phone = phone;
    }


//    public JwtResponse(String token, String name, String avatar, Collection<? extends GrantedAuthority> authorities) {
//        this.token = token;
//        this.name = name;
//        this.avatar = avatar;
//        this.roles = authorities;
//    }


}

package com.example.loverbe.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEditForm {
    private String name;
    private String phone;
    private String gender;
    @Column(columnDefinition = "varchar(255) default 'default-avatar.png'")
    private String avatar;
}

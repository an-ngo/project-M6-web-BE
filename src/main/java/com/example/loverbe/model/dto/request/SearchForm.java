package com.example.loverbe.model.dto.request;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchForm {
    private String gender;
    private String beforeYear;
    private String afterYear;
    private Hobby hobby;
    private String country;
    private String city;
}

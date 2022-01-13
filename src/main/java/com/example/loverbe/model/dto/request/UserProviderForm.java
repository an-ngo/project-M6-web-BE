package com.example.loverbe.model.dto.request;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserProviderForm {
    private String yearOfBirth;

    private String city;

    private String country;

    private List<String> serviceByProviderList;

    private List<String> imageList;

    private String height;

    private String weight;

    private List<Hobby> hobbyList;

    private String description;

    private String conditions;

    private String link_facebook;

    private String isStatusProvider;
}

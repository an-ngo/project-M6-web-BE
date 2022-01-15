package com.example.loverbe.model.dto.request;

import com.example.loverbe.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderForm {

    private User userProvider;

    private String place;

    private Long duration;

    private String date;

    private List<String> serviceByProviderList;
}

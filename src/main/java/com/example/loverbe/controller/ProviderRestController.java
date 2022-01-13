package com.example.loverbe.controller;

import com.example.loverbe.model.dto.request.UserProviderForm;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.service.IImageService;
import com.example.loverbe.service.IServiceByProviderService;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/user-provider")
public class ProviderRestController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IImageService iImageService;
    @Autowired
    private IServiceByProviderService providerService;

    @PutMapping
    public ResponseEntity<User> editUserProvider(@RequestBody UserProviderForm userProviderForm){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = currentUser.get();
        user.setYearOfBirth(userProviderForm.getYearOfBirth());
        user.setCity(userProviderForm.getCity());
        user.setCountry(userProviderForm.getCountry());
        List<String> serviceByProvider = userProviderForm.getServiceByProviderList();
        for (int i = 0; i < serviceByProvider.size(); i++){
            ServiceByProvider service = providerService.save(new ServiceByProvider(serviceByProvider.get(i), 0D, user));
            user.getServiceByProviderList().add(service);
        }
        user.setHeight(userProviderForm.getHeight());
        user.setWeight(userProviderForm.getWeight());
        user.setHobbyList(userProviderForm.getHobbyList());
        user.setDescription(userProviderForm.getDescription());
        user.setConditions(userProviderForm.getConditions());
        user.setLink_facebook(userProviderForm.getLink_facebook());
        if (user.getJoinDate() == null){
            user.setJoinDate(new Date(userProviderForm.getJoinDate()));
        }
        user.setIsStatusProvider(userProviderForm.getIsStatusProvider());
        List<String> images = userProviderForm.getImageList();
        for (int i = 0; i < images.size(); i++){
            Image image = iImageService.save(new Image(images.get(i), user));
            user.getImageList().add(image);
        }
        if (user.getViewCount() == null){
            user.setViewCount(0L);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.ACCEPTED);
    }
}

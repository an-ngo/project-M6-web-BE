package com.example.loverbe.controller;

import com.example.loverbe.enums.EnumRoleName;
import com.example.loverbe.enums.EnumStatusProvider;
import com.example.loverbe.model.dto.request.UserProviderForm;
import com.example.loverbe.model.dto.response.ResponseMessage;
import com.example.loverbe.model.entity.user.Role;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.service.IImageService;
import com.example.loverbe.service.IRoleService;
import com.example.loverbe.service.IServiceByProviderService;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @Autowired
    private IRoleService roleService;

    @PutMapping
    public ResponseEntity<?> editUserProvider(@RequestBody UserProviderForm userProviderForm){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
            boolean check = true;
            for (ServiceByProvider service: user.getServiceByProviderList()){
                if (service.getName().equals(serviceByProvider.get(i))){
                    check = false;
                }
            }
            if (check){
                ServiceByProvider service = providerService.save(new ServiceByProvider(serviceByProvider.get(i), 0D, user));
                user.getServiceByProviderList().add(service);
            }
        }
        user.setHeight(userProviderForm.getHeight());
        user.setWeight(userProviderForm.getWeight());
        user.setHobbyList(userProviderForm.getHobbyList());
        user.setDescription(userProviderForm.getDescription());
        user.setConditions(userProviderForm.getConditions());
        user.setLink_facebook(userProviderForm.getLink_facebook());
        if (user.getJoinDate() == null){
            user.setJoinDate(LocalDate.now());
        }
        user.setIsStatusProvider(EnumStatusProvider.ACTIVE);
        List<String> images = userProviderForm.getImageList();
        for (int i = 0; i < images.size(); i++){
            Image image = iImageService.save(new Image(images.get(i), user));
            user.getImageList().add(image);
        }
        if (user.getViewCount() == null){
            user.setViewCount(0L);
        }
        Optional<Role> role = roleService.findByName(EnumRoleName.ROLE_PROVIDER);
        if (!role.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean checkRole = true;
        for (Role currentRole : user.getRoles()){
            if (currentRole.equals(role.get())){
                checkRole = false;
                break;
            }
        }
        if (checkRole){
            user.getRoles().add(role.get());
        }
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Create success!"), HttpStatus.OK);

    }
    @PutMapping("/{status}")
    public ResponseEntity<User> changeStatusProvider(@PathVariable String status){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        switch (status){
            case "active": currentUser.get().setIsStatusProvider(EnumStatusProvider.ACTIVE); break;
            case "busy": currentUser.get().setIsStatusProvider(EnumStatusProvider.BUSY); break;
            case "disable": currentUser.get().setIsStatusProvider(EnumStatusProvider.DISABLE); break;
            default: return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.save(currentUser.get()), HttpStatus.ACCEPTED);
    }
}

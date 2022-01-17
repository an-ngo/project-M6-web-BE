package com.example.loverbe.controller;

import com.example.loverbe.model.dto.request.SearchForm;
import com.example.loverbe.model.dto.request.UserEditForm;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<Page<User>> findAllServiceProvider(@PageableDefault(value = 12) Pageable pageable){
        return new ResponseEntity<>(userService.findAllServiceProvider(pageable), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<User>> search(@RequestBody SearchForm searchForm, @PageableDefault(value = 5) Pageable pageable){
        if (searchForm.getGender() == null){
            searchForm.setGender("%");
        }
        if (searchForm.getBeforeYear() == null){
            searchForm.setBeforeYear(1980L);
        }
        if (searchForm.getAfterYear() == null){
            searchForm.setAfterYear(2021L);
        }
        if (searchForm.getCountry() == null || searchForm.getCountry().equals("")){
            searchForm.setCountry("%");
        }
        if (searchForm.getCity() == null || searchForm.getCity().equals("")){
            searchForm.setCity("%");
        }
        return new ResponseEntity<>(userService.searchUserProvider("%"+searchForm.getGender()+"%", searchForm.getBeforeYear(), searchForm.getAfterYear(), "%"+searchForm.getCountry()+"%", "%"+searchForm.getCity()+"%", pageable), HttpStatus.OK);
    }
    @GetMapping("/info")
    public ResponseEntity<User> showInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.get().setViewCount(user.get().getViewCount() + 1);
        return new ResponseEntity<>(userService.save(user.get()), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<User> editUser(@RequestBody UserEditForm userEditForm){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = currentUser.get();
        user.setName(userEditForm.getName());
        user.setPhone(userEditForm.getPhone());
        user.setGender(userEditForm.getGender());
        user.setAvatar(userEditForm.getAvatar());
        return new ResponseEntity<>(userService.save(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(currentUser.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/top-6-service")
    public ResponseEntity<Iterable<User>> getTop6Service(){
        return new ResponseEntity<>(userService.findTop6HotService(), HttpStatus.OK);
    }
    @GetMapping("/top-6-provider-hot")
    public ResponseEntity<Iterable<User>> getTop6ProviderHot(){
        return new ResponseEntity<>(userService.top6ProviderHot(), HttpStatus.OK);
    }
    @GetMapping("/find-top12-counttime")
    public ResponseEntity<List<User>> getTop12ByCountTime(){
        List<User> manUsers = (List<User>) userService.find4MenUserTopCountTime();
        List<User> maleUsers = (List<User>) userService.find8MaleUserTopCountTime();
        for (User user: manUsers){
            maleUsers.add(user);
        }
        return new ResponseEntity<>(maleUsers, HttpStatus.OK);
    }
    @GetMapping("/find-top12-joindate")
    public ResponseEntity<Iterable<User>> getTop12JoinDate(){
        return new ResponseEntity<>(userService.find12TopJoinDate(), HttpStatus.OK);
    }
    @GetMapping("/find-top6-viewpage")
    public ResponseEntity<Iterable<User>> getTop6ViewPage(){
        return new ResponseEntity<>(userService.find6TopViewPage(), HttpStatus.OK);
    }
}

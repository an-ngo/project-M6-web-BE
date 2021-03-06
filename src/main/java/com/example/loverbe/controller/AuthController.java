package com.example.loverbe.controller;

import com.example.loverbe.model.dto.request.ChangeAvatar;
import com.example.loverbe.model.dto.request.SignInForm;
import com.example.loverbe.model.dto.request.SignUpForm;
import com.example.loverbe.model.dto.response.JwtResponse;
import com.example.loverbe.model.dto.response.ResponseMessage;
import com.example.loverbe.model.email.MailObject;
import com.example.loverbe.model.entity.user.Role;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.security.jwt.JwtProvider;
import com.example.loverbe.security.userprincal.UserDetailSevices;
import com.example.loverbe.security.userprincal.UserPrincipal;
import com.example.loverbe.service.IEmailService;
import com.example.loverbe.service.IRoleService;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class AuthController {
    @Autowired
    IUserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IRoleService roleService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailSevices userDetailSevices;
    @Autowired
    public IEmailService emailService;

    private static final Map<String, Map<String, String>> labels;

    static {
        labels = new HashMap<>();
        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);
    }

    @GetMapping("/listRole")
    public ResponseEntity<?> listRole() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) throws Exception {

//        }
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
        }
        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/lover-4a315.appspot.com/o/kisspng-computer-icons-avatar-social-media-blog-font-aweso-avatar-icon-5b2e99c4409623.4643918115297806762646.png?alt=media&token=8418fb57-0c41-4e1c-9c79-b28cc82e36ea");
        }

        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), signUpForm.getPhone(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getAvatar());
        user.getRoles().add(new Role(2L));
        emailService.sendSimpleMessage(new MailObject());

        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
        Optional<User> currentUser = userService.findByUsername(signInForm.getUsername());
        currentUser.get().setOnline(true);
        userService.save(currentUser.get());
        return ResponseEntity.ok(new JwtResponse(token,currentUser.get().getName(), currentUser.get().getUsername(), userPrinciple.getAvatar(), userPrinciple.getAuthorities(),userPrinciple.getPhone()));
    }

    @GetMapping("/logout/logout")
    public ResponseEntity<?> logout(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if(currentUser.isPresent()){
            currentUser.get().setOnline(false);
            userService.save(currentUser.get());
            return ResponseEntity.ok("logout success");
        }else{
         return new ResponseEntity<>("not found user",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> updateAvatar(@RequestBody ChangeAvatar changeAvatar) {
        User user = userDetailSevices.getCurrentUser();
        if (user.getUsername().equals("Anonymous")) {
            return new ResponseEntity<>(new ResponseMessage("Please login!"), HttpStatus.OK);
        }
        user.setAvatar(changeAvatar.getAvatar());
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
}

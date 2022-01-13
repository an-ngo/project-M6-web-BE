package com.example.loverbe.controller;

import com.example.loverbe.model.dto.request.UpFiles;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.security.userprincal.UserDetailSevices;
import com.example.loverbe.service.IImageService;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/up-files")
public class UploadFilesController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired private IImageService imageService;
    @Autowired private UserDetailSevices userDetailSevices;
    @Autowired private IUserService userService;

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody UpFiles upFiles) {
     Image image = new Image();
     User user = userDetailSevices.getCurrentUser();
     image.setUser(user);
     imageService.save(image);
     List<String> images = upFiles.getLink_image();
     User image1 = imageService.findByUserId(user.getId());
     images.forEach((image) -> {
         Image
     });
    }
}

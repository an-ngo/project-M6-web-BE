package com.example.loverbe.model.entity.user;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.enums.EnumStatusNCCDV;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class NCCDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String yearOfBirth;

    private String city;

    private String country;

    @ManyToMany(targetEntity = ServiceByNCCDV.class)
    private List<ServiceByNCCDV> serviceByNCCDVList;

    @OneToMany(targetEntity = Image.class)
    private List<Image> imageList;

    private String height;

    private String weight;

    @ManyToMany(targetEntity = Hobby.class)
    private List<Hobby> hobbyList;

    private String description;

    private String condition;

    private String link_facebook;

    private String joinDate;

    private Long countTime = 0L;

    private EnumStatusNCCDV status;

    private Long viewCount;

}

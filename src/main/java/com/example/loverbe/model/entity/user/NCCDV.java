package com.example.loverbe.model.entity.user;

import com.example.loverbe.enums.EnumStatusNCCDV;
import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    @OneToOne(targetEntity = User.class)
    private User user;

    private String yearOfBirth;

    private String city;

    private String country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "nccdv_servicebynccdv",
            joinColumns = {@JoinColumn(name = "nccdv_id")}, inverseJoinColumns = {@JoinColumn(name = "service_bynccdv_id")})
    private List<ServiceByNCCDV> serviceByNCCDVList;

    @OneToMany(mappedBy = "nccdv")
    private List<Image> imageList;

    private String height;

    private String weight;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "nccdv_hobby",
            joinColumns = {@JoinColumn(name = "nccdv_id")}, inverseJoinColumns = {@JoinColumn(name = "hobby_id")})
    private List<Hobby> hobbyList;

    private String description;

    private String conditions;

    private String link_facebook;

    private String joinDate;

    private Long countTime = 0L;

    private EnumStatusNCCDV status;

    private Long viewCount;
}

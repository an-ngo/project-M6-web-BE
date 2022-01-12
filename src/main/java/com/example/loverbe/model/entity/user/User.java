package com.example.loverbe.model.entity.user;

import com.example.loverbe.enums.EnumStatusNCCDV;
import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String username;

    @NaturalId
    private String email;

    @JsonIgnore
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean status;

    private String phone;

    private String gender;

    @Lob
    @Column(columnDefinition = "varchar(255) default 'default-avatar.png'")
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    private String yearOfBirth;

    private String city;

    private String country;

    @ManyToMany(targetEntity = ServiceByNCCDV.class, mappedBy = "users", cascade = CascadeType.REMOVE)
//    @JoinTable(name = "nccdv_servicebynccdv",
//            joinColumns = {@JoinColumn(name = "nccdv_id")}, inverseJoinColumns = {@JoinColumn(name = "service_bynccdv_id")})
    private List<ServiceByNCCDV> serviceByNCCDVList;

    @OneToMany(targetEntity = Image.class,mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Image> imageList;

    private String height;

    private String weight;

    @ManyToMany(targetEntity = Hobby.class, mappedBy = "users", cascade = CascadeType.REMOVE)
//    @JoinTable(name = "nccdv_hobby",
//            joinColumns = {@JoinColumn(name = "nccdv_id")}, inverseJoinColumns = {@JoinColumn(name = "hobby_id")})
    private List<Hobby> hobbyList;

    private String description;

    private String conditions;

    private String link_facebook;

    private String joinDate;

    private Long countTime = 0L;

    private EnumStatusNCCDV statusNCCDV;

    private Long viewCount;


    public User(String username, String email, String encode, String avatar) {
        this.username = username;
        this.email = email;
        this.password  = encode;
        this.avatar = avatar;
    }
}

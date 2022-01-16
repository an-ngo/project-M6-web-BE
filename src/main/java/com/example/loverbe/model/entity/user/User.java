package com.example.loverbe.model.entity.user;


import com.example.loverbe.enums.EnumStatusProvider;
import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
    private boolean isOnline;

    private String phone;

    private String gender;

    @Lob
    @Column(columnDefinition = "varchar(255) default 'default-avatar.png'")
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    private Long yearOfBirth;

    private String city;

    private String country;

    @OneToMany(mappedBy = "user")
    private List<ServiceByProvider> serviceByProviderList;

    @OneToMany(targetEntity = Image.class,mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Image> imageList;

    private String height;

    private String weight;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_hobby",
            joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "hobby_id")})
    private List<Hobby> hobbyList;

    private String description;

    private String conditions;

    private String link_facebook;

    private LocalDate joinDate;

    private Long countTime = 0L;

    private EnumStatusProvider isStatusProvider;

    private Long viewCount;


    public User(String username, String email, String encode, String avatar, String phone) {
        this.username = username;
        this.email = email;
        this.password  = encode;
        this.avatar = avatar;
        this.phone = phone;
        this.setRoles(new HashSet<>());
    }

    public User(String name, String username, String email, String phone, String encode, String avatar) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password  = encode;
        this.avatar = avatar;
        this.setRoles(new HashSet<>());
    }
}

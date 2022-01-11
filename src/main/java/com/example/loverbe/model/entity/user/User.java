package com.example.loverbe.model.entity.user;

import com.example.loverbe.model.entity.room.Room;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "users_rooms",
            joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> roomList;


    public User(String username, String email, String encode, String avatar) {
        this.username = username;
        this.email = email;
        this.password  = encode;
        this.avatar = avatar;
    }
}

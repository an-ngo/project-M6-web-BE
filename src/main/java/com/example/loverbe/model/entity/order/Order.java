package com.example.loverbe.model.entity.order;
import com.example.loverbe.model.entity.Role;
import com.example.loverbe.model.entity.ServiceByUser;
import com.example.loverbe.model.entity.enums.EnumOrder;
import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private NCCDV nccdv;

    private String place;

    private String duration;

    private String startTime;

    private String date;

    private Long money;

    @ManyToMany(targetEntity = ServiceByUser.class)
    private List<ServiceByUser> serviceByUserList;

    private EnumOrder enumOrder;

    private String comment;
}

package com.example.loverbe.model.entity.order;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = NCCDV.class)
    private NCCDV nccdv;

    private String place;

    private String duration;

    private String startTime;

    private String date;

    private Long money;

    @ManyToMany(targetEntity = ServiceByNCCDV.class)
    private List<ServiceByNCCDV> serviceByNCCDVList;

    private EnumOrder enumOrder;

    private String comment;
}

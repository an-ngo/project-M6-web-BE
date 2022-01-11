package com.example.loverbe.model.entity.order;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = NCCDV.class)
    private NCCDV NCCDV;

    private String place;

    private String duration;

    private LocalDate startTime;

    private Date date;

    private Long money;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_service_bynccdvlist",
            joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "service_bynccdv_list")})
    private List<ServiceByNCCDV> serviceByNCCDVList;

    private EnumOrder enumOrder;

    private String comment;
}

package com.example.loverbe.model.entity.orders;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.user.User;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private User userProvider;

    @ManyToOne(targetEntity = User.class)
    private User user;

    private String place;

    private String duration;

    private LocalDate startTime;

    private Date date;

    private Long money;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "order_service_by_provider",
            joinColumns = {@JoinColumn(name = "orders_id")}, inverseJoinColumns = {@JoinColumn(name = "service_by_provider_id")})
    private List<ServiceByProvider> serviceByProviderList;

    private EnumOrder enumOrder;

    private String comment;
}

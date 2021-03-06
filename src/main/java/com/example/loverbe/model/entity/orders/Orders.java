package com.example.loverbe.model.entity.orders;
import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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

    private Long duration;

    private LocalDate startTime;

    private LocalDate date;

    private Double money;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "order_service_by_provider",
            joinColumns = {@JoinColumn(name = "orders_id")}, inverseJoinColumns = {@JoinColumn(name = "service_by_provider_id")})
    private List<ServiceByProvider> serviceByProviderList;

    private EnumOrder statusOrder;
    @OneToOne(targetEntity = Room.class)
    private Room room;
    private String comment;
}

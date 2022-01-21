package com.example.loverbe.model.entity.user;

import com.example.loverbe.enums.EnumRoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 50)
    private EnumRoleName name;

    public Role(long l) {
        this.id=l;
    }
}

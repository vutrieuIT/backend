package vn.id.vuductrieu.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import vn.id.vuductrieu.backend.constants.TypeAddress;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    private Long id;

    private String address;

    private String province;

    private String district;

    private String ward;

    @Enumerated(EnumType.STRING)
    private TypeAddress type;

    @ManyToOne(optional = false)
    private User user;
}

package co.thairong.mini_pos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String customerLocalName;

    @Column(nullable = false, length = 100)
    private String customerEngName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false, length = 20)
    private String customerPhone;

    @Column(nullable = false, length = 100)
    private String customerAddress;

    private boolean isDeleted = false;
}

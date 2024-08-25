package co.thairong.mini_pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String supplierLocalName;

    @Column(nullable = false, length = 100)
    private String supplierEngName;

    @Column(nullable = false, length = 100)
    private String supplierEmail;

    @Column(nullable = false, length = 20)
    private String supplierPhone;

    @Column(nullable = false, length = 100)
    private String supplierAddress;

    @Column(nullable = false, length = 100)
    private String vatNumber;

    private boolean isDeleted;
}

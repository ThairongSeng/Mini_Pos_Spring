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

    private String supplierLocalName;
    private String supplierEngName;
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    private String vatNumber;

    private boolean isDeleted;
}

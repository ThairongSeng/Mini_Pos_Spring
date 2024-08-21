package co.thairong.mini_pos.entity;

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
    private String customerLocalName;
    private String customerEngName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;

    private boolean isDeleted;
}

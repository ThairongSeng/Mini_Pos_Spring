package co.thairong.mini_pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String companyLocalName;
    @Column(nullable = false, length = 100)
    private String companyEngName;
    @Column(nullable = false)
    private String companyEmail;
    @Column(nullable = false, length = 20)
    private String companyPhone;
    @Column(nullable = false, length = 100)
    private String companyAddress;
    @Column(nullable = false, length = 100)
    private String vatNumber;
    private String imagePath;
    private String image;

    private boolean isDeleted;
}

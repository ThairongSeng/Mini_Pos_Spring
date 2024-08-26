package co.thairong.mini_pos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "general_setting")
public class GeneralSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String siteTitle;

    private String siteLogo;

    @Column(nullable = false)
    private String sitePhone;
    @Column(nullable = false)
    private String siteAddress;

    private boolean isDeleted = false;
}

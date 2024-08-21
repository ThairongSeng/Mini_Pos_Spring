package co.thairong.mini_pos.entity;


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

    private String siteTitle;
    private String siteLogo;
    private String sitePhone;
    private String siteAddress;

    private boolean isDeleted;
}

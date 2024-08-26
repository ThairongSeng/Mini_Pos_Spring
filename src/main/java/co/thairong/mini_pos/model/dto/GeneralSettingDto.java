package co.thairong.mini_pos.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeneralSettingDto(
        @NotBlank(message = "Site Title is required.")
         String siteTitle,

        @NotNull(message = "Site Logo is required.")
         String siteLogo,

        @NotBlank(message = "Site Phone is required.")
         String sitePhone,

        @NotBlank(message = "Site Address is required.")
         String siteAddress
) {
}

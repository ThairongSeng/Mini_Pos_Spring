package co.thairong.mini_pos.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BrandDto(@NotBlank(message = "Name is required.!") String name) {

}

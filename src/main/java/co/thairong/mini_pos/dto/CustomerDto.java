package co.thairong.mini_pos.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerDto(
        @NotBlank(message = "Customer Local Name is required.")
         String customerLocalName,
        @NotBlank(message = "Customer English Name is required.")
         String customerEngName,
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is invalid..!")
         String customerEmail,
        @NotBlank(message = "Customer Phone Number is required.")
         String customerPhone,
        @NotBlank(message = "Customer Address is required.")
         String customerAddress
) {
}

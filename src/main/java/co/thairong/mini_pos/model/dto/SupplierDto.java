package co.thairong.mini_pos.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SupplierDto(
        @NotBlank(message = "Supplier Local Name is required")
         String supplierLocalName,
        @NotBlank(message = "Supplier English Name is required")
        String supplierEngName,
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is invalid..!")
        String supplierEmail,
        @NotBlank(message = "Supplier Phone Number is required")
        String supplierPhone,
        @NotBlank(message = "Supplier Address is required")
        String supplierAddress,
        @NotBlank(message = "Vat Number is required")
        String vatNumber
) {
}

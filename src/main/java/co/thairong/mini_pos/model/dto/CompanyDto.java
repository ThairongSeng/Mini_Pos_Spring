package co.thairong.mini_pos.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CompanyDto(
                        @NotBlank(message = "Company Local Name is required..!")
                        String companyLocalName,
                        @NotBlank(message = "Company English Name is required..!")
                        String companyEngName,
                        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is invalid..!")
                        String companyEmail,
                        @NotBlank(message = "Company Phone Number is required..!")
                        String companyPhone,
                        @NotBlank(message = "Company Address is required..!")
                        String companyAddress,
                        @NotBlank(message = "Company English Name is required..!")
                        String vatNumber
                    ) {
}

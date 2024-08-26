package co.thairong.mini_pos.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ExchangeRateDto(
        @NotNull(message = "Exchange Rate is required!")
        @DecimalMin(value = "0.0", inclusive = true, message = "Exchange Rate must be at least 0.0")
        Double exchangeRate
) {
}


package co.thairong.mini_pos.mapper;

import co.thairong.mini_pos.model.dto.ExchangeRateDto;
import co.thairong.mini_pos.model.entity.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRate exchangeRateDtoToExchangeRate(ExchangeRateDto exchangeRateDto);

    ExchangeRateDto exchangeRateToExchangeRateDto(ExchangeRate exchangeRate);
}


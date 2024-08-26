package co.thairong.mini_pos.service;

import co.thairong.mini_pos.model.dto.ExchangeRateDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ExchangeRateService {

    ExchangeRateDto createExchangeRate(ExchangeRateDto exchangeRateDto);

    ExchangeRateDto getById(Long id);

    void deleteById(Long id);

    ExchangeRateDto updateExchangeRate(Long id, ExchangeRateDto exchangeRateDto);

    Page<ExchangeRateDto> getAllExchangeRates(Map<String, String> params);

}

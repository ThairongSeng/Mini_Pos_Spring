package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.model.dto.ExchangeRateDto;
import co.thairong.mini_pos.model.dto.PageDto;
import co.thairong.mini_pos.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/ap/v1/exchange-rate")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PostMapping
    public BaseRest<?> createExchangeRate(@Valid @RequestBody ExchangeRateDto exchangeRateDto) {

        ExchangeRateDto rateDto = exchangeRateService.createExchangeRate(exchangeRateDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Exchange rate has been created successfully")
                .data(rateDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("{id}")
    public BaseRest<?> getById(@PathVariable Long id) {

        ExchangeRateDto exchangeRateDto = exchangeRateService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange rate has been found successfully")
                .data(exchangeRateDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        exchangeRateService.deleteById(id);
    }


    @PutMapping("{id}")
    public BaseRest<?> updateExchangeRate(@PathVariable Long id, @Valid @RequestBody ExchangeRateDto exchangeRateDto) {

        ExchangeRateDto rateDto = exchangeRateService.updateExchangeRate(id, exchangeRateDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Exchange rate has been updated successfully")
                .data(rateDto)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("/page")
    public BaseRest<?> getExchangeRatePages(@RequestParam Map<String, String> params) {

        Page<ExchangeRateDto> exchangeRateDtoPage = exchangeRateService.getAllExchangeRates(params);
        PageDto pageDto = new PageDto(exchangeRateDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Customers have been found successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

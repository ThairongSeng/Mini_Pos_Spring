package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.mapper.ExchangeRateMapper;
import co.thairong.mini_pos.model.dto.CustomerDto;
import co.thairong.mini_pos.model.dto.ExchangeRateDto;
import co.thairong.mini_pos.model.dto.GeneralSettingDto;
import co.thairong.mini_pos.model.entity.Customer;
import co.thairong.mini_pos.model.entity.ExchangeRate;
import co.thairong.mini_pos.model.entity.GeneralSetting;
import co.thairong.mini_pos.repository.ExchangeRateRepository;
import co.thairong.mini_pos.service.ExchangeRateService;
import co.thairong.mini_pos.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    @Override
    public ExchangeRateDto createExchangeRate(ExchangeRateDto exchangeRateDto) {

        ExchangeRate exchangeRate = exchangeRateMapper.exchangeRateDtoToExchangeRate(exchangeRateDto);
        exchangeRateRepository.save(exchangeRate);

        return exchangeRateMapper.exchangeRateToExchangeRateDto(exchangeRate);
    }

    @Override
    public ExchangeRateDto getById(Long id) {

        ExchangeRate exchangeRate = exchangeRateRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Exchange rate with id %d not found", id)
        ));

        return exchangeRateMapper.exchangeRateToExchangeRateDto(exchangeRate);
    }

    @Override
    public void deleteById(Long id) {
        ExchangeRateDto exchangeRateDto = getById(id);
        ExchangeRate exchangeRate = exchangeRateMapper.exchangeRateDtoToExchangeRate(exchangeRateDto);

        exchangeRate.setId(id);
        exchangeRate.setDeleted(true);

        exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public ExchangeRateDto updateExchangeRate(Long id, ExchangeRateDto exchangeRateDto) {
        ExchangeRateDto rateDto = getById(id);
        ExchangeRate exchangeRate = exchangeRateMapper.exchangeRateDtoToExchangeRate(rateDto);

        exchangeRate.setId(id);
        exchangeRate.setExchangeRate(exchangeRateDto.exchangeRate());

        exchangeRateRepository.save(exchangeRate);

        return exchangeRateMapper.exchangeRateToExchangeRateDto(exchangeRate);
    }

    @Override
    public Page<ExchangeRateDto> getAllExchangeRates(Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        String searchName = PageUtil.SEARCH_NAME;

        // Extract pagination parameters
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        if (params.containsKey(PageUtil.PAGE_NUM)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUM));
        }

        // Create Pageable object
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        // Fetch paginated result
        Page<ExchangeRate> settingPage = exchangeRateRepository.findByIsDeletedFalseOrderByIdDesc(pageable);

        // Convert to DTOs
        Page<ExchangeRateDto> exchangeRateDtoPage = settingPage.map(exchangeRateMapper::exchangeRateToExchangeRateDto);

        return exchangeRateDtoPage;
    }
}

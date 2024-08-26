package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.model.dto.GeneralSettingDto;
import co.thairong.mini_pos.model.entity.GeneralSetting;
import co.thairong.mini_pos.mapper.GeneralSettingMapper;
import co.thairong.mini_pos.repository.GeneralSettingRepository;
import co.thairong.mini_pos.service.GeneralSettingService;
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
public class GeneralSettingServiceImpl implements GeneralSettingService {

    private final GeneralSettingRepository generalSettingRepository;
    private final GeneralSettingMapper generalSettingMapper;

    @Override
    public GeneralSettingDto createGeneralSetting(GeneralSettingDto generalSettingDto) {

        GeneralSetting generalSetting = generalSettingMapper.generalSettingDtoToGeneralSetting(generalSettingDto);
        generalSettingRepository.save(generalSetting);

        return generalSettingMapper.generalSettingToGeneralSettingDto(generalSetting);
    }

    @Override
    public GeneralSettingDto getById(Long id) {

        GeneralSetting generalSetting = generalSettingRepository.findCustomerByIdAndIsDeletedFalse(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("General Setting with id %d is not found", id)
                ));

        return generalSettingMapper.generalSettingToGeneralSettingDto(generalSetting);
    }

    @Override
    public void deleteGeneralSetting(Long id) {
        GeneralSettingDto settingDto = getById(id);
        GeneralSetting generalSetting = generalSettingMapper.generalSettingDtoToGeneralSetting(settingDto);

        generalSetting.setId(id);
        generalSetting.setDeleted(true);

        generalSettingRepository.save(generalSetting);
    }

    @Override
    public GeneralSettingDto updateGeneralSetting(Long id, GeneralSettingDto generalSettingDto) {
        GeneralSettingDto settingDto = getById(id);
        GeneralSetting generalSetting = generalSettingMapper.generalSettingDtoToGeneralSetting(settingDto);

        generalSetting.setId(id);
        generalSetting.setSiteTitle(generalSettingDto.siteTitle());
        generalSetting.setSiteLogo(generalSettingDto.siteLogo());
        generalSetting.setSitePhone(generalSettingDto.sitePhone());
        generalSetting.setSiteAddress(generalSettingDto.siteAddress());

        generalSettingRepository.save(generalSetting);

        return generalSettingMapper.generalSettingToGeneralSettingDto(generalSetting);
    }

    @Override
    public Page<GeneralSettingDto> getGeneralSettingList(Map<String, String> params) {
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
        Page<GeneralSetting> settingPage;
        if (params.containsKey(searchName)) {
            String siteTitle = params.get(searchName);
            settingPage = generalSettingRepository.findBySiteTitleContainingIgnoreCaseAndIsDeletedFalse(siteTitle, pageable);
        } else {
            settingPage = generalSettingRepository.findByIsDeletedFalseOrderByIdDesc(pageable);
        }

        // Convert to DTOs
        Page<GeneralSettingDto> generalSettingDtoPage = settingPage.map(generalSettingMapper::generalSettingToGeneralSettingDto);

        return generalSettingDtoPage;
    }
}

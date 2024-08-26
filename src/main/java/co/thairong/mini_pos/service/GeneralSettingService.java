package co.thairong.mini_pos.service;

import co.thairong.mini_pos.model.dto.GeneralSettingDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface GeneralSettingService {


    GeneralSettingDto createGeneralSetting(GeneralSettingDto generalSettingDto);

    GeneralSettingDto getById(Long id);

    void deleteGeneralSetting(Long id);

    GeneralSettingDto updateGeneralSetting(Long id, GeneralSettingDto generalSettingDto);

    Page<GeneralSettingDto> getGeneralSettingList(Map<String, String> params);
}

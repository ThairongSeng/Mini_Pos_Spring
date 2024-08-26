package co.thairong.mini_pos.mapper;


import co.thairong.mini_pos.model.dto.GeneralSettingDto;
import co.thairong.mini_pos.model.entity.GeneralSetting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralSettingMapper {

    GeneralSetting generalSettingDtoToGeneralSetting(GeneralSettingDto generalSettingDto);

    GeneralSettingDto generalSettingToGeneralSettingDto(GeneralSetting generalSetting);


}

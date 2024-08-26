package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.model.dto.GeneralSettingDto;
import co.thairong.mini_pos.model.dto.PageDto;
import co.thairong.mini_pos.service.GeneralSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/general-setting")
@RequiredArgsConstructor
public class GeneralSettingController {

    private final GeneralSettingService generalSettingService;

    @PostMapping
    public BaseRest<?> createGeneralSetting(@Valid @RequestBody GeneralSettingDto generalSettingDto) {

        GeneralSettingDto settingDto = generalSettingService.createGeneralSetting(generalSettingDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("General Setting has been created successfully")
                .data(settingDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("{id}")
    public BaseRest<?> getById(@PathVariable Long id) {

        GeneralSettingDto settingDto = generalSettingService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General Setting has been found successfully")
                .data(settingDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        generalSettingService.deleteGeneralSetting(id);
    }

    @PutMapping("{id}")
    public BaseRest<?> updateGeneralSetting(@PathVariable Long id, @Valid @RequestBody GeneralSettingDto generalSettingDto) {

        GeneralSettingDto settingDto = generalSettingService.updateGeneralSetting(id, generalSettingDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General Setting has been updated successfully")
                .data(settingDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/page")
    public BaseRest<?> getGeneralSettingsList(@RequestParam Map<String, String> params){

        Page<GeneralSettingDto> generalSettingDtoPage = generalSettingService.getGeneralSettingList(params);
        PageDto pageDto = new PageDto(generalSettingDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("General Settings have been found successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

}

package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.model.dto.BrandDto;
import co.thairong.mini_pos.model.dto.PageDto;
import co.thairong.mini_pos.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public BaseRest<?> createBrand(@Valid @RequestBody BrandDto brandDto) {

        BrandDto brandDto1 = brandService.saveData(brandDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Brand has been created successfully")
                .data(brandDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("{id}")
    public BaseRest<?> getBrandById(@PathVariable Long id) {

        BrandDto brandDto = brandService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Brand has been found successfully")
                .data(brandDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("{id}")
    public BaseRest<?> updateById(@PathVariable Long id, @Valid @RequestBody BrandDto brandDto) {

        BrandDto brandDto1 = brandService.updateData(id, brandDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Brand has been updated successfully")
                .data(brandDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping
    public BaseRest<?> getAllBrands() {
        List<BrandDto> brandDtoList = brandService.getAllData();

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Brands have been found successfully")
                .data(brandDtoList)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        brandService.deleteData(id);
    }


    @GetMapping("/page")
    public BaseRest<?> getWithPagination(@RequestParam(required = false) Map<String, String> params) {

        Page<BrandDto> brandDtoPage = brandService.getWithPagination(params);
        PageDto pageDto = new PageDto(brandDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Brands have been found successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }


}
